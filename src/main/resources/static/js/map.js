document.addEventListener("DOMContentLoaded", function () {
    const REST_API_KEY = kakaoRestKey;

    var keyword = document.getElementById('keyword-data').value;
    var mapContainer = document.getElementById('map');
    var mapOption = {
        center: new kakao.maps.LatLng(37.566826, 126.9786567), // 서울 중심 좌표
        level: 3 // 확대/축소 레벨
    };

    var map = new kakao.maps.Map(mapContainer, mapOption);
    var ps = new kakao.maps.services.Places();
    var geocoder = new kakao.maps.services.Geocoder();
    var infowindow = new kakao.maps.InfoWindow({ zIndex: 1 });
    var markers = [];
    var routePolyline = null;
    var openInfoWindow = null;

    kakao.maps.event.addListener(map, 'idle', function () {
        var center = map.getCenter();
        searchPlaces(center);
    });

    function searchPlaces(location) {
        markers.forEach(marker => marker.setMap(null)); // 기존 마커 제거
        markers = []; // 배열 초기화

        ps.keywordSearch(keyword, placesSearchCB, { location: location, radius: 5000 });
    }

    function placesSearchCB(data, status) {
        if (status === kakao.maps.services.Status.OK) {
            data.forEach((place) => {
                displayMarker(place);
            });
        } else {
            console.error("검색 결과가 없습니다.");
        }
    }

    function displayMarker(place) {
        var marker = new kakao.maps.Marker({
            map: map,
            position: new kakao.maps.LatLng(place.y, place.x)
        });
        markers.push(marker);

        kakao.maps.event.addListener(marker, 'click', function () {
            searchDetailAddrFromCoords(marker.getPosition(), function (result, status) {
                if (status === kakao.maps.services.Status.OK) {
                    const address = result[0].road_address
                        ? result[0].road_address.address_name
                        : result[0].address.address_name;

                    const content = `
                        <div style="padding:5px;font-size:12px;">
                            <p>${place.place_name}</p>
                            <p>${address}</p>
                            <button onclick="savePlaceToDatabase('${place.place_name}', '${address}', ${place.x}, ${place.y})">리뷰 남기기</button>
                            <button onclick="getCarDirection(${place.y}, ${place.x})">경로 탐색</button>
                        </div>`;
                    infowindow.setContent(content);
                    infowindow.open(map, marker);
                }
            });
        });
    }

    function searchDetailAddrFromCoords(coords, callback) {
        geocoder.coord2Address(coords.getLng(), coords.getLat(), callback);
    }

    window.savePlaceToDatabase = function (name, address, longitude, latitude) {
        if (!isLoggedIn) {
            alert("로그인이 필요합니다. 로그인 페이지로 이동합니다.");
            window.location.href = '/login';
            return;
        }

        const placeData = { name, address, longitude, latitude };

        fetch('/reviews/create', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(placeData),
        })
            .then(response => {
                if (!response.ok) throw new Error('Failed to save place');
                return response.json(); // JSON 응답을 파싱
            })
            .then(data => {
                const redirectUrl = data.redirectUrl;
                alert('장소가 저장되었습니다. 리뷰 작성 페이지로 이동합니다.');
                window.location.href = redirectUrl; // JSON에서 추출한 URL로 이동
            })
            .catch(error => console.error('Error:', error));
    };



    window.getCarDirection = async function (lat, lng) {
        const url = 'https://apis-navi.kakaomobility.com/v1/directions';

        if (!navigator.geolocation) {
            alert("Geolocation을 지원하지 않는 브라우저입니다.");
            return;
        }

        navigator.geolocation.getCurrentPosition(async function (position) {
            const origin = `${position.coords.longitude},${position.coords.latitude}`;
            const destination = `${lng},${lat}`;

            const headers = {
                Authorization: `KakaoAK ${REST_API_KEY}`,
                'Content-Type': 'application/json'
            };
            const queryParams = new URLSearchParams({ origin, destination });
            const requestUrl = `${url}?${queryParams}`;

            try {
                const response = await fetch(requestUrl, {
                    method: 'GET',
                    headers: headers
                });

                if (!response.ok) throw new Error(`HTTP error! Status: ${response.status}`);
                const data = await response.json();
                drawRoute(data.routes[0].sections);
            } catch (error) {
                console.error('Error:', error);
            }
        }, function () {
            alert("현재 위치를 가져올 수 없습니다.");
        });
    };

    function drawRoute(sections) {
        if (routePolyline) routePolyline.setMap(null);

        const path = []; // 경로 배열 생성

        sections.forEach(section => {
            section.roads.forEach(road => {
                road.vertexes.forEach((vertex, index) => {
                    if (index % 2 === 0) {
                        const lng = vertex;
                        const lat = road.vertexes[index + 1];
                        path.push(new kakao.maps.LatLng(lat, lng));
                    }
                });
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

    function setMapCenterByUserLocation() {
        if (navigator.geolocation) {
            navigator.geolocation.getCurrentPosition(function (position) {
                var userLocation = new kakao.maps.LatLng(position.coords.latitude, position.coords.longitude);
                map.setCenter(userLocation);
            }, function () {
                alert("위치 정보를 가져올 수 없습니다.");
            });
        } else {
            alert("Geolocation을 지원하지 않는 브라우저입니다.");
        }
    }

    window.displayMapCenter = function () {
        var center = map.getCenter();
        var homeDisplay = document.getElementById("home-display");
        homeDisplay.innerText = `현재 지도 중심 좌표:\n위도: ${center.getLat().toFixed(6)}, 경도: ${center.getLng().toFixed(6)}`;
    };

    setMapCenterByUserLocation();
});
