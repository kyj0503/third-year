document.addEventListener("DOMContentLoaded", function () {
    /**
     * KakaoMobility REST API Key
     * Mustache 템플릿에서 전달된 키를 사용한다.
     * @constant {string} REST_API_KEY
     */
    const REST_API_KEY = kakaoRestKey;

    /**
     * 검색 키워드, 지도 컨테이너 및 지도 옵션 초기화
     * @constant {string} keyword - 검색 키워드
     * @constant {HTMLElement} mapContainer - 지도 컨테이너 요소
     * @constant {Object} mapOption - 지도 초기화 옵션
     */
    var keyword = document.getElementById('keyword-data').value;
    var mapContainer = document.getElementById('map');
    var mapOption = {
        center: new kakao.maps.LatLng(37.566826, 126.9786567), // 서울 중심 좌표
        level: 3 // 확대/축소 레벨
    };

    /**
     * Kakao 지도 및 관련 객체 초기화
     * @constant {Object} map - Kakao 지도 객체
     * @constant {Object} ps - Kakao 장소 검색 서비스 객체
     * @constant {Object} infowindow - Kakao 인포윈도우 객체
     * @constant {Array} markers - 지도에 표시된 마커 배열
     * @constant {Object|null} routePolyline - 지도에 그려진 경로 객체
     * @constant {Object|null} openInfoWindow - 현재 열려 있는 인포윈도우 객체
     */
    var map = new kakao.maps.Map(mapContainer, mapOption);
    var ps = new kakao.maps.services.Places();
    var infowindow = new kakao.maps.InfoWindow({ zIndex: 1 });
    var markers = [];
    var routePolyline = null;
    var openInfoWindow = null;

    /**
     * 지도 이동 완료 후 중심 좌표를 기준으로 장소를 검색한다.
     */
    kakao.maps.event.addListener(map, 'idle', function () {
        var center = map.getCenter();
        searchPlaces(center);
    });

    /**
     * 중심 좌표를 기준으로 장소를 검색한다.
     * 기존 마커를 제거하고 새로운 장소를 검색하여 마커를 추가한다.
     * @param {Object} location - 중심 좌표 (LatLng 객체)
     */
    function searchPlaces(location) {
        markers.forEach(marker => marker.setMap(null)); // 기존 마커 제거
        markers = []; // 배열 초기화

        ps.keywordSearch(keyword, placesSearchCB, { location: location, radius: 5000 });
    }

    /**
     * 장소 검색 결과를 처리하고 마커를 표시한다.
     * @param {Array} data - 검색된 장소 데이터
     * @param {string} status - 검색 상태 코드
     */
    function placesSearchCB(data, status) {
        if (status === kakao.maps.services.Status.OK) {
            data.forEach(displayMarker);
        } else {
            console.error("검색 결과가 없습니다.");
        }
    }

    /**
     * 지도에 마커를 표시하고 인포윈도우를 설정한다.
     * @param {Object} place - 장소 정보 객체
     */
    function displayMarker(place) {
        var marker = new kakao.maps.Marker({
            map: map,
            position: new kakao.maps.LatLng(place.y, place.x)
        });
        markers.push(marker);

        var infowindow = new kakao.maps.InfoWindow({
            content: `<div style="padding:5px;font-size:12px;">
                        ${place.place_name}
                        <button onclick="showReviewForm('${place.id}')">리뷰 남기기</button>
                        <button onclick="getCarDirection(${place.y}, ${place.x})">경로 탐색</button>
                      </div>`
        });

        kakao.maps.event.addListener(marker, 'click', function () {
            if (openInfoWindow) openInfoWindow.close();
            if (openInfoWindow === infowindow) openInfoWindow = null;
            else {
                infowindow.open(map, marker);
                openInfoWindow = infowindow;
            }
        });
    }

    /**
     * KakaoMobility 경로 탐색 API를 호출하여 경로를 탐색한다.
     * @param {number} lat - 목적지의 위도
     * @param {number} lng - 목적지의 경도
     */
    window.getCarDirection = async function(lat, lng) {
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

    /**
     * API에서 반환된 경로 데이터를 기반으로 지도에 경로를 표시한다.
     * @param {Array} sections - 경로 섹션 데이터
     */
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

    /**
     * 현재 위치를 기반으로 지도 중심 좌표를 설정한다.
     */
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

    /**
     * 현재 지도 중심 좌표를 표시한다.
     */
    window.displayMapCenter = function () {
        var center = map.getCenter();
        var homeDisplay = document.getElementById("home-display");
        homeDisplay.innerText = `현재 지도 중심 좌표:\n위도: ${center.getLat().toFixed(6)}, 경도: ${center.getLng().toFixed(6)}`;
    };

    /**
     * 초기 설정 - 현재 위치로 지도 중심 설정
     */
    setMapCenterByUserLocation();
});
