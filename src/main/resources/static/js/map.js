window.onload = function() {



var markers = JSON.parse(document.getElementById('markers-data').value);

// 마커 데이터 확인
console.log("Markers Data: ", markers);

// 첫 번째 마커의 좌표를 지도의 중심으로 설정
if (markers.length > 0) {
    var mapCenter = new kakao.maps.LatLng(markers[0].latitude, markers[0].longitude);
} else {
    var mapCenter = new kakao.maps.LatLng(33.450701, 126.570667); // 기본 좌표 설정 (마커가 없을 경우)
}

var mapContainer = document.getElementById('map'), // 지도를 표시할 div
    mapOption = {
        center: mapCenter, // 첫 번째 마커의 좌표로 중심 설정
        level: 3 // 지도의 확대 레벨
    };

var map = new kakao.maps.Map(mapContainer, mapOption); // 지도를 생성합니다

// 지도가 제대로 생성되었는지 확인
console.log("Map Initialized: ", map);

// 각 마커를 지도에 표시
markers.forEach(function(marker) {
    var markerPosition = new kakao.maps.LatLng(marker.latitude, marker.longitude);

    var kakaoMarker = new kakao.maps.Marker({
        position: markerPosition,
        map: map // 마커를 생성할 지도
    });

    var infowindow = new kakao.maps.InfoWindow({
        content: '<div style="padding:5px;">' + marker.name + '</div>' // 마커에 표시할 인포윈도우 내용
    });

    // 마커 생성 확인
    console.log("Marker Created: ", kakaoMarker);

    kakao.maps.event.addListener(kakaoMarker, 'mouseover', function() {
        infowindow.open(map, kakaoMarker);
    });

    kakao.maps.event.addListener(kakaoMarker, 'mouseout', function() {
        infowindow.close();
    }); 1
});

    // 여기 안에 지도 초기화 코드를 작성하세요
};