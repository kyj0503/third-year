document.addEventListener("DOMContentLoaded", function () {
    /**
     * KakaoMobility REST API Key
     * 서버에서 전달된 키를 사용한다.
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
     * @constant {Object} geocoder - Kakao 주소-좌표 변환 서비스 객체
     * @constant {Object} infowindow - Kakao 인포윈도우 객체
     * @constant {Array} markers - 지도에 표시된 마커 배열
     * @constant {Object|null} routePolyline - 지도에 그려진 경로 객체
     * @constant {Object|null} openInfoWindow - 현재 열려 있는 인포윈도우 객체
     */
    var map = new kakao.maps.Map(mapContainer, mapOption);
    var ps = new kakao.maps.services.Places();
    var geocoder = new kakao.maps.services.Geocoder();
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
            data.forEach((place) => {
                displayMarker(place);
            });
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

    /**
     * 좌표를 상세 주소로 변환한다.
     * @param {Object} coords - 좌표 객체
     * @param {Function} callback - 변환 결과를 처리할 콜백 함수
     */
    function searchDetailAddrFromCoords(coords, callback) {
        geocoder.coord2Address(coords.getLng(), coords.getLat(), callback);
    }

    /**
     * 장소 데이터를 서버에 저장하고 리뷰 작성 페이지로 이동한다.
     * @param {string} name - 장소 이름
     * @param {string} address - 장소 주소
     * @param {number} longitude - 경도
     * @param {number} latitude - 위도
     */
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
                return response.json();
            })
            .then(data => {
                const redirectUrl = data.redirectUrl;
                alert('장소가 저장되었습니다. 리뷰 작성 페이지로 이동합니다.');
                window.location.href = redirectUrl;
            })
            .catch(error => console.error('Error:', error));
    };

    /**
     * KakaoMobility API를 사용하여 자동차 경로를 탐색한다.
     * @param {number} lat - 목적지의 위도
     * @param {number} lng - 목적지의 경도
     */
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

    /**
     * 경로 데이터를 지도에 표시한다.
     * @param {Array} sections - 경로 섹션 데이터
     */
    function drawRoute(sections) {
        if (routePolyline) routePolyline.setMap(null);

        const path = [];

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
     * 사용자 위치를 기반으로 지도 중심 좌표를 설정한다.
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

    // 초기 설정 - 현재 위치로 지도 중심 설정
    setMapCenterByUserLocation();
});
