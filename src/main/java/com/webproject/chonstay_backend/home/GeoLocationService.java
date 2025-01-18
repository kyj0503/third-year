package com.webproject.chonstay_backend.home;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Service
public class GeoLocationService {

    @Value("${google.api.key}")  // application.properties에서 API 키를 읽어옵니다.
    private String googleApiKey;

    private static final String GEOCODING_API_URL = "https://maps.googleapis.com/maps/api/geocode/json?address=%s&key=%s";

    // 주소를 입력받아 위도와 경도를 얻어오는 메서드
    public void setCoordinatesFromAddress(Home home, String address) throws JSONException {
        String url = String.format(GEOCODING_API_URL, address, googleApiKey);

        // RestTemplate을 사용하여 Google Maps API에 요청
        RestTemplate restTemplate = new RestTemplate();
        String response = restTemplate.getForObject(url, String.class);

        // JSON 응답 파싱
        JSONObject jsonResponse = new JSONObject(response);
        JSONObject location = jsonResponse.getJSONArray("results")
                .getJSONObject(0)
                .getJSONObject("geometry")
                .getJSONObject("location");

        // 위도(lat)와 경도(lng) 추출
        String latitudeStr = location.getString("lat");
        String longitudeStr = location.getString("lng");

        // BigDecimal로 변환하여 저장 (정밀도를 위해 String으로 전달)
        BigDecimal latitude = new BigDecimal(latitudeStr).setScale(8, RoundingMode.HALF_UP); // 위도 소수점 8자리
        BigDecimal longitude = new BigDecimal(longitudeStr).setScale(8, RoundingMode.HALF_UP); // 경도 소수점 8자리

        // home 객체에 위도와 경도 값 설정
        home.setCoordinates(latitude, longitude);

    }
}
