document.addEventListener("DOMContentLoaded", function () {
    const REST_API_KEY = kakaoRestKey; // KakaoMobility REST API Key

    var mapContainer = document.getElementById('map');
    var mapOption = {
        center: new kakao.maps.LatLng(37.566826, 126.9786567), // Default Seoul
        level: 3 // Zoom level
    };

    var map = new kakao.maps.Map(mapContainer, mapOption);
    var ps = new kakao.maps.services.Places(); // Kakao 장소 검색 객체
    var geocoder = new kakao.maps.services.Geocoder();
    var cafeMarkers = []; // 카페 마커
    var placeMarkers = []; // Place 마커
    var infoWindows = [];
    var routePolyline = null;
    var startCoords = null;
    var endCoords = null;

    // 이미지 경로
    const cafeMarkerImageSrc = '/img/green.png'; // 카페 마커 이미지
    const placeMarkerImageSrc = '/img/yellow.png'; // 데이터베이스 Place 마커 이미지
    const cafeImageSize = new kakao.maps.Size(40, 40); // 카페 마커 크기
    const placeImageSize = new kakao.maps.Size(40, 40); // Place 마커 크기
    const imageOption = { offset: new kakao.maps.Point(25, 25) }; // 마커 이미지 기준점 설정

    // 초기 검색 키워드 (카페)
    const keyword = "카페";

    // 사용자 위치를 초기화하고 출발지로 설정
    if (navigator.geolocation) {
        navigator.geolocation.getCurrentPosition(function (position) {
            const userLatLng = new kakao.maps.LatLng(position.coords.latitude, position.coords.longitude);

            map.setCenter(userLatLng); // 지도 중심 설정
            searchPlaces(userLatLng); // 현재 위치를 기준으로 카페 검색
            displayDatabasePlaces(); // 데이터베이스에 저장된 장소 표시
            startCoords = userLatLng; // 출발지 초기값 설정
            document.getElementById('start-location').value = "현재 위치";
        }, function () {
            alert("현재 위치를 가져올 수 없습니다.");
        });
    } else {
        alert("Geolocation을 지원하지 않는 브라우저입니다.");
    }

    // 데이터베이스에서 Place 데이터를 가져와 지도에 표시
    function displayDatabasePlaces() {
        fetch('/places') // Place 데이터를 가져오는 API
            .then(response => response.json())
            .then(places => {
                places.forEach(place => {
                    const position = new kakao.maps.LatLng(place.latitude, place.longitude);

                    const markerImage = new kakao.maps.MarkerImage(
                        placeMarkerImageSrc, placeImageSize, imageOption
                    );

                    const marker = new kakao.maps.Marker({
                        map: map,
                        position: position,
                        image: markerImage
                    });
                    placeMarkers.push(marker);

                    // 마커 클릭 이벤트
                    kakao.maps.event.addListener(marker, 'click', function () {
                        const content = `
                            <div style="padding:5px;">
                                <p>${place.name}</p>
                                <p>${place.address}</p>
                                <button onclick="setStart('${place.name}', ${place.latitude}, ${place.longitude})">출발지 설정</button>
                                <button onclick="setEnd('${place.name}', ${place.latitude}, ${place.longitude})">도착지 설정</button>
                            </div>`;
                        const infowindow = new kakao.maps.InfoWindow({
                            content: content,
                            removable: true
                        });
                        infowindow.open(map, marker);
                        infoWindows.push(infowindow);
                    });
                });
            })
            .catch(error => console.error('Error fetching places:', error));
    }

    // 주변 카페 검색
    function searchPlaces(location) {
        ps.keywordSearch(keyword, function (data, status) {
            if (status === kakao.maps.services.Status.OK) {
                displayPlaces(data); // 검색된 장소를 지도에 표시
            } else {
                console.error("검색 결과가 없습니다.");
            }
        }, {
            location: location,
            radius: 5000 // 반경 5km
        });
    }

    // 검색된 장소를 지도에 표시
    function displayPlaces(places) {
        places.forEach((place) => {
            const position = new kakao.maps.LatLng(place.y, place.x);

            const markerImage = new kakao.maps.MarkerImage(
                cafeMarkerImageSrc, cafeImageSize, imageOption
            );

            const marker = new kakao.maps.Marker({
                map: map,
                position: position,
                image: markerImage
            });
            cafeMarkers.push(marker);

            // 마커 클릭 이벤트
            kakao.maps.event.addListener(marker, 'click', function () {
                const content = `
                    <div style="padding:5px;">
                        <p>${place.place_name}</p>
                        <p>${place.road_address_name || place.address_name}</p>
                        <button onclick="setStart('${place.place_name}', ${place.y}, ${place.x})">출발지 설정</button>
                        <button onclick="setEnd('${place.place_name}', ${place.y}, ${place.x})">도착지 설정</button>
                    </div>`;
                const infowindow = new kakao.maps.InfoWindow({
                    content: content,
                    removable: true
                });
                infowindow.open(map, marker);
                infoWindows.push(infowindow);
            });
        });
    }

    // 인포윈도우 닫기
    function closeAllInfoWindows() {
        infoWindows.forEach(infoWindow => infoWindow.close());
        infoWindows = []; // 배열 초기화
    }

    // 출발지 설정
    window.setStart = function (name, lat, lng) {
        startCoords = new kakao.maps.LatLng(lat, lng);
        document.getElementById('start-location').value = name;
    };

    // 도착지 설정
    window.setEnd = function (name, lat, lng) {
        endCoords = new kakao.maps.LatLng(lat, lng);
        document.getElementById('end-location').value = name;
    };

    // 경로 계산
    window.calculateRoute = async function () {
        if (!startCoords || !endCoords) {
            alert("출발지와 도착지를 모두 설정하세요.");
            return;
        }

        const origin = `${startCoords.getLng()},${startCoords.getLat()}`;
        const destination = `${endCoords.getLng()},${endCoords.getLat()}`;

        const url = 'https://apis-navi.kakaomobility.com/v1/directions';
        const queryParams = new URLSearchParams({ origin, destination });
        const headers = {
            Authorization: `KakaoAK ${REST_API_KEY}`,
            'Content-Type': 'application/json'
        };

        try {
            const response = await fetch(`${url}?${queryParams}`, {
                method: 'GET',
                headers: headers
            });

            if (!response.ok) throw new Error(`HTTP error! Status: ${response.status}`);

            const data = await response.json();
            drawRoute(data.routes[0].sections);
        } catch (error) {
            console.error('Error:', error);
            alert("경로를 탐색하는 중 오류가 발생했습니다.");
        }
    };

    // 경로 그리기
    function drawRoute(sections) {
        if (routePolyline) routePolyline.setMap(null);

        const path = [];
        sections.forEach(section => {
            section.roads.forEach(road => {
                for (let i = 0; i < road.vertexes.length; i += 2) {
                    const lat = road.vertexes[i + 1];
                    const lng = road.vertexes[i];
                    path.push(new kakao.maps.LatLng(lat, lng));
                }
            });
        });

        routePolyline = new kakao.maps.Polyline({
            map: map,
            path: path,
            strokeWeight: 5,
            strokeColor: '#FF0000',
            strokeOpacity: 0.7,
            strokeStyle: 'solid'
        });

        const bounds = new kakao.maps.LatLngBounds();
        path.forEach(point => bounds.extend(point));
        map.setBounds(bounds);
    }

    // 초기화 버튼 기능
    window.resetMap = function () {
        closeAllInfoWindows(); // 인포윈도우 닫기
        if (routePolyline) {
            routePolyline.setMap(null); // 경로 제거
            routePolyline = null;
        }
        document.getElementById('start-location').value = "";
        document.getElementById('end-location').value = "";
    };
});
