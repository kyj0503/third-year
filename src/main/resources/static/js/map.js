document.addEventListener("DOMContentLoaded", function () {
    const REST_API_KEY = kakaoRestKey; // KakaoMobility REST API Key

    var keyword = document.getElementById('keyword-data').value; // 검색 키워드
    var mapContainer = document.getElementById('map'); // 지도 컨테이너
    var mapOption = {
        center: new kakao.maps.LatLng(37.566826, 126.9786567), // 기본 서울 중심 좌표
        level: 3 // 확대/축소 레벨
    };

    var map = new kakao.maps.Map(mapContainer, mapOption); // 지도 객체 생성
    var ps = new kakao.maps.services.Places(); // 장소 검색 서비스 객체
    var geocoder = new kakao.maps.services.Geocoder(); // 주소 변환 서비스 객체
    var infowindow = new kakao.maps.InfoWindow({ zIndex: 1 }); // 정보 창 객체
    var markers = []; // 마커를 저장할 배열
    var openInfoWindowMarker = null; // 열린 인포윈도우의 마커 저장 변수

    /**
     * 사용자 위치를 기반으로 지도 중심 설정
     */
    function setMapCenterByUserLocation() {
        if (navigator.geolocation) {
            navigator.geolocation.getCurrentPosition(function (position) {
                const userLatLng = new kakao.maps.LatLng(position.coords.latitude, position.coords.longitude);
                map.setCenter(userLatLng); // 사용자 위치로 지도 중심 설정
                searchPlaces(userLatLng); // 위치 기반으로 장소 검색
            }, function () {
                alert("현재 위치를 가져올 수 없습니다. 기본 위치를 표시합니다.");
            });
        } else {
            alert("Geolocation을 지원하지 않는 브라우저입니다. 기본 위치를 표시합니다.");
        }
    }

    /**
     * 장소 검색 기능
     */
    function searchPlaces(location) {
        markers.forEach(marker => marker.setMap(null)); // 기존 마커 제거
        markers = []; // 마커 배열 초기화

        ps.keywordSearch(keyword, placesSearchCB, { location: location, radius: 5000 }); // 키워드 검색
    }

    /**
     * 장소 검색 콜백 함수
     */
    function placesSearchCB(data, status) {
        if (status === kakao.maps.services.Status.OK) {
            data.forEach((place) => {
                displayMarker(place); // 마커 표시
            });
        } else {
            console.error("검색 결과가 없습니다.");
        }
    }

    /**
     * 마커 표시 함수
     */
    function displayMarker(place) {
        var marker = new kakao.maps.Marker({
            map: map,
            position: new kakao.maps.LatLng(place.y, place.x)
        });
        markers.push(marker); // 마커 배열에 추가

        kakao.maps.event.addListener(marker, 'click', function () {
            if (openInfoWindowMarker === marker) {
                infowindow.close(); // 이미 열린 인포윈도우가 있으면 닫기
                openInfoWindowMarker = null;
                return;
            }

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
                        <button onclick="addFavorite('${place.place_name}', '${address}', ${place.x}, ${place.y})">즐겨찾기 추가</button>
                    </div>`;
                    infowindow.setContent(content); // 인포윈도우에 내용 설정
                    infowindow.open(map, marker); // 지도에 인포윈도우 열기
                    openInfoWindowMarker = marker; // 열린 인포윈도우의 마커 저장
                }
            });
        });
    }

    /**
     * 좌표를 기반으로 주소 변환
     */
    function searchDetailAddrFromCoords(coords, callback) {
        geocoder.coord2Address(coords.getLng(), coords.getLat(), callback); // 주소 변환 요청
    }

    /**
     * 즐겨찾기 추가 함수
     */
    window.addFavorite = function (name, address, longitude, latitude) {
        if (!isLoggedIn) {
            alert("로그인이 필요합니다. 로그인 페이지로 이동합니다.");
            window.location.href = '/login';
            return;
        }

        const favoriteData = { name, address, longitude, latitude };

        fetch('/favorites/create', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(favoriteData), // 즐겨찾기 데이터 전송
        })
            .then(response => {
                if (!response.ok) throw new Error('Failed to add favorite');
                return response.json();
            })
            .then(data => {
                alert('즐겨찾기에 추가되었습니다.');
            })
            .catch(error => console.error('Error:', error));
    };

    /**
     * 장소를 데이터베이스에 저장
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
            body: JSON.stringify(placeData), // 장소 데이터 전송
        })
            .then(response => {
                if (!response.ok) throw new Error('Failed to save place');
                return response.json();
            })
            .then(data => {
                const redirectUrl = data.redirectUrl;
                window.location.href = redirectUrl; // 리뷰 작성 후 리다이렉트
            })
            .catch(error => console.error('Error:', error));
    };

    /**
     * 자동차 경로 탐색
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
                drawRoute(data.routes[0].sections); // 경로 그리기
            } catch (error) {
                console.error('Error:', error);
            }
        }, function () {
            alert("현재 위치를 가져올 수 없습니다.");
        });
    };

    /**
     * 경로 그리기
     */
    function drawRoute(sections) {
        if (routePolyline) routePolyline.setMap(null); // 기존 경로 제거

        const path = [];

        sections.forEach(section => {
            section.roads.forEach(road => {
                road.vertexes.forEach((vertex, index) => {
                    if (index % 2 === 0) {
                        const lng = vertex;
                        const lat = road.vertexes[index + 1];
                        path.push(new kakao.maps.LatLng(lat, lng)); // 경로 좌표 추가
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
        path.forEach(point => bounds.extend(point)); // 경로가 포함된 bounds 설정
        map.setBounds(bounds); // 지도 범위 설정
    }

    setMapCenterByUserLocation(); // 사용자 위치를 기준으로 지도 초기화
});
