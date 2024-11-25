document.addEventListener("DOMContentLoaded", function () {
    const REST_API_KEY = kakaoRestKey; // KakaoMobility REST API Key를 상수로 저장

    // 지도 설정
    var mapContainer = document.getElementById('map'); // 지도 컨테이너를 가져옴
    var mapOption = {
        center: new kakao.maps.LatLng(37.566826, 126.9786567), // 지도 초기 중심 (서울)
        level: 3 // 지도 줌 레벨
    };

    // 지도 객체 생성
    var map = new kakao.maps.Map(mapContainer, mapOption);
    var ps = new kakao.maps.services.Places(); // 장소 검색 객체 (카카오 API)
    var geocoder = new kakao.maps.services.Geocoder(); // 주소 변환을 위한 Geocoder 객체
    var cafeMarkers = []; // 카페 마커들을 저장할 배열
    var placeMarkers = []; // 데이터베이스에서 가져온 장소 마커 배열
    var infoWindows = []; // 열린 인포윈도우 객체들을 저장할 배열
    var routePolyline = null; // 경로를 그리기 위한 폴리라인 객체
    var startCoords = null; // 출발지 좌표
    var endCoords = null; // 도착지 좌표
    var openInfoWindowMarker = null; // 현재 열려 있는 인포윈도우의 마커

    // 마커 이미지 경로
    const cafeMarkerImageSrc = '/img/green.png'; // 카페 마커 이미지
    const placeMarkerImageSrc = '/img/yellow.png'; // 데이터베이스에 저장된 장소 마커 이미지
    const cafeImageSize = new kakao.maps.Size(40, 40); // 카페 마커 크기
    const placeImageSize = new kakao.maps.Size(40, 40); // 데이터베이스 Place 마커 크기
    const imageOption = { offset: new kakao.maps.Point(25, 25) }; // 마커 기준점 설정

    const keyword = "카페"; // 초기 검색 키워드 (카페)

    /**
     * 사용자의 현재 위치를 기반으로 지도 위치 설정
     */
    if (navigator.geolocation) {
        navigator.geolocation.getCurrentPosition(function (position) {
            const userLatLng = new kakao.maps.LatLng(position.coords.latitude, position.coords.longitude);

            map.setCenter(userLatLng); // 현재 위치를 지도 중심으로 설정
            searchPlaces(userLatLng); // 현재 위치 기준으로 카페 검색
            displayDatabasePlaces(); // 데이터베이스에 저장된 장소 마커 표시
            startCoords = userLatLng; // 출발지 초기 설정
            document.getElementById('start-location').value = "현재 위치"; // 출발지 텍스트 필드에 값 설정
        }, function () {
            alert("현재 위치를 가져올 수 없습니다."); // 위치 오류 시 경고
        });
    } else {
        alert("Geolocation을 지원하지 않는 브라우저입니다."); // Geolocation 미지원 브라우저 경고
    }

    /**
     * 데이터베이스에서 장소를 가져와 지도에 마커를 표시하는 함수
     */
    function displayDatabasePlaces() {
        fetch('/places') // 데이터베이스에서 장소 정보 가져오는 API
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
                    placeMarkers.push(marker); // 마커 배열에 추가

                    // 마커 클릭 시 인포윈도우 표시
                    kakao.maps.event.addListener(marker, 'click', function () {
                        if (openInfoWindowMarker === marker) {
                            closeAllInfoWindows(); // 이미 열린 인포윈도우가 있으면 닫기
                            openInfoWindowMarker = null;
                            return;
                        }
                        closeAllInfoWindows(); // 기존 열린 인포윈도우 모두 닫기

                        const content = `
                            <div style="padding:5px;">
                                <p>${place.name}</p>
                                <p>${place.address}</p>
                                <button onclick="setStart('${place.name}', ${place.latitude}, ${place.longitude})">출발지 설정</button>
                                <button onclick="setEnd('${place.name}', ${place.latitude}, ${place.longitude})">도착지 설정</button>
                            </div>`;
                        const infowindow = new kakao.maps.InfoWindow({
                            content: content
                        });
                        infowindow.open(map, marker); // 인포윈도우 열기
                        infoWindows.push(infowindow); // 열린 인포윈도우 배열에 추가
                        openInfoWindowMarker = marker; // 현재 열려있는 인포윈도우의 마커 저장
                    });
                });
            })
            .catch(error => console.error('Error fetching places:', error)); // 오류 처리
    }

    /**
     * 카카오 장소 API를 사용하여 장소 검색
     */
    function searchPlaces(location) {
        ps.keywordSearch(keyword, function (data, status) {
            if (status === kakao.maps.services.Status.OK) {
                displayPlaces(data); // 검색된 장소들을 지도에 표시
            } else {
                console.error("검색 결과가 없습니다."); // 검색 결과가 없을 때 로그
            }
        }, {
            location: location,
            radius: 5000 // 검색 반경 5km
        });
    }

    /**
     * 카카오 API로 검색된 장소들을 지도에 표시하는 함수
     */
    function displayPlaces(places) {
        // 기존 카페 마커들 지우기
        cafeMarkers.forEach(marker => marker.setMap(null));
        cafeMarkers = []; // 배열 초기화

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
            cafeMarkers.push(marker); // 카페 마커 배열에 추가

            // 마커 클릭 시 인포윈도우 표시
            kakao.maps.event.addListener(marker, 'click', function () {
                if (openInfoWindowMarker === marker) {
                    closeAllInfoWindows(); // 이미 열린 인포윈도우가 있으면 닫기
                    openInfoWindowMarker = null;
                    return;
                }
                closeAllInfoWindows(); // 기존 열린 인포윈도우 닫기

                const content = `
                    <div style="padding:5px;">
                        <p>${place.place_name}</p>
                        <p>${place.road_address_name || place.address_name}</p>
                        <button onclick="setStart('${place.place_name}', ${place.y}, ${place.x})">출발지 설정</button>
                        <button onclick="setEnd('${place.place_name}', ${place.y}, ${place.x})">도착지 설정</button>
                    </div>`;
                const infowindow = new kakao.maps.InfoWindow({
                    content: content
                });
                infowindow.open(map, marker); // 인포윈도우 열기
                infoWindows.push(infowindow); // 열린 인포윈도우 배열에 추가
                openInfoWindowMarker = marker; // 현재 열려있는 인포윈도우의 마커 저장
            });
        });
    }

    /**
     * 열린 모든 인포윈도우를 닫는 함수
     */
    function closeAllInfoWindows() {
        infoWindows.forEach(infoWindow => infoWindow.close()); // 각 인포윈도우 닫기
        infoWindows = []; // 배열 초기화
    }

    // 출발지 및 도착지 텍스트 입력 필드 이벤트 리스너 추가
    document.getElementById('start-location').addEventListener('change', () => handleAddressInput('start-location', true));
    document.getElementById('end-location').addEventListener('change', () => handleAddressInput('end-location', false));

    /**
     * 주소 입력값을 처리하여 좌표로 변환하는 함수
     * @param {string} inputId - 입력 필드 ID ('start-location' 또는 'end-location')
     * @param {boolean} isStart - 출발지 여부 (true: 출발지, false: 도착지)
     */
    function handleAddressInput(inputId, isStart) {
        const address = document.getElementById(inputId).value.trim();
        if (!address) {
            alert("주소를 입력해주세요.");
            return;
        }

        geocoder.addressSearch(address, function (result, status) {
            if (status === kakao.maps.services.Status.OK) {
                const coords = new kakao.maps.LatLng(result[0].y, result[0].x);

                if (isStart) {
                    startCoords = coords;
                    alert("출발지가 설정되었습니다.");
                } else {
                    endCoords = coords;
                    alert("도착지가 설정되었습니다.");
                }

                // 지도 중심 이동 및 마커 표시
                map.setCenter(coords);
                new kakao.maps.Marker({
                    map: map,
                    position: coords
                });
            } else {
                alert("주소 검색 결과가 없습니다. 올바른 주소를 입력해주세요.");
            }
        });
    }

    /**
     * 출발지로 설정하는 함수
     */
    window.setStart = function (name, lat, lng) {
        startCoords = new kakao.maps.LatLng(lat, lng); // 출발지 좌표 설정
        document.getElementById('start-location').value = name; // 출발지 이름 텍스트 필드에 설정
    };

    /**
     * 도착지로 설정하는 함수
     */
    window.setEnd = function (name, lat, lng) {
        endCoords = new kakao.maps.LatLng(lat, lng); // 도착지 좌표 설정
        document.getElementById('end-location').value = name; // 도착지 이름 텍스트 필드에 설정
    };

    /**
     * 출발지와 도착지를 기준으로 경로를 계산하고 표시하는 함수
     */
    window.calculateRoute = async function () {
        if (!startCoords || !endCoords) {
            alert("출발지와 도착지를 모두 설정하세요."); // 출발지와 도착지가 모두 설정되어야 경로 계산
            return;
        }

        const origin = `${startCoords.getLng()},${startCoords.getLat()}`; // 출발지 좌표
        const destination = `${endCoords.getLng()},${endCoords.getLat()}`; // 도착지 좌표

        const url = 'https://apis-navi.kakaomobility.com/v1/directions'; // 카카오 네비 API URL
        const queryParams = new URLSearchParams({ origin, destination }); // 쿼리 파라미터 설정
        const headers = {
            Authorization: `KakaoAK ${REST_API_KEY}`,
            'Content-Type': 'application/json'
        };

        try {
            const response = await fetch(`${url}?${queryParams}`, {
                method: 'GET',
                headers: headers
            });

            if (!response.ok) throw new Error(`HTTP error! Status: ${response.status}`); // HTTP 오류 처리

            const data = await response.json();
            drawRoute(data.routes[0].sections); // 경로 그리기
        } catch (error) {
            console.error('Error:', error);
            alert("경로를 탐색하는 중 오류가 발생했습니다."); // 경로 계산 중 오류 처리
        }
    };

    /**
     * 경로를 지도에 폴리라인으로 그리는 함수
     */
    function drawRoute(sections) {
        if (routePolyline) routePolyline.setMap(null); // 기존 경로가 있으면 제거

        const path = [];
        sections.forEach(section => {
            section.roads.forEach(road => {
                for (let i = 0; i < road.vertexes.length; i += 2) {
                    const lat = road.vertexes[i + 1];
                    const lng = road.vertexes[i];
                    path.push(new kakao.maps.LatLng(lat, lng)); // 경로 좌표 추가
                }
            });
        });

        routePolyline = new kakao.maps.Polyline({
            map: map,
            path: path,
            strokeWeight: 5, // 경로 두께
            strokeColor: '#FF0000', // 경로 색상
            strokeOpacity: 0.7, // 경로 투명도
            strokeStyle: 'solid' // 경로 스타일
        });

        const bounds = new kakao.maps.LatLngBounds();
        path.forEach(point => bounds.extend(point)); // 경로가 포함된 영역 설정
        map.setBounds(bounds); // 지도의 경계 설정
    }

    /**
     * 지도 이동 시 새로운 위치에서 장소를 검색
     */
    kakao.maps.event.addListener(map, 'center_changed', function () {
        const center = map.getCenter();
        searchPlaces(center);
    });

    /**
     * 지도 초기화 함수
     */
    window.resetMap = function () {
        closeAllInfoWindows(); // 열린 인포윈도우 닫기
        if (routePolyline) {
            routePolyline.setMap(null); // 경로 폴리라인 제거
            routePolyline = null; // 폴리라인 객체 초기화
        }
        document.getElementById('start-location').value = ""; // 출발지 텍스트 필드 초기화
        document.getElementById('end-location').value = ""; // 도착지 텍스트 필드 초기화
    };
});
