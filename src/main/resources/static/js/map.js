document.addEventListener("DOMContentLoaded", function () {
    // 지도를 표시할 div (HTML에서 id="map"인 요소)와 지도 옵션을 설정합니다.
    var mapContainer = document.getElementById('map'), // 지도를 표시할 div
        mapOption = {
            center: new kakao.maps.LatLng(37.5665, 126.9780), // 지도 중심좌표 (예: 서울시청 좌표)
            level: 3 // 지도의 확대 레벨
        };

    // 지도 생성
    var map = new kakao.maps.Map(mapContainer, mapOption);

    // 마커 표시 (옵션)
    var markerPosition  = new kakao.maps.LatLng(37.5665, 126.9780);
    var marker = new kakao.maps.Marker({
        position: markerPosition
    });
    marker.setMap(map);
});
