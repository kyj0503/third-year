package com.webproject.chonstay_backend.home;

import com.webproject.chonstay_backend.home.dto.HomeGetResponse;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.json.JSONException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class HomeService {

    private final GeoLocationService geoLocationService;
    private final HomeRepository homeRepository;

    //@param requestDto HomeRegisterRequestDto

    @Transactional
    public void registerHome(HomeRegisterRequestDto requestDto) {
        // DTO를 엔티티로 변환
        Home home = new Home();
        home.setHomeName(requestDto.getHomeName());
        home.setAddress(requestDto.getAddress());
        home.setMaxGuest(requestDto.getMaxGuest());
        home.setDescription(requestDto.getDescription());
        home.setHomeImageUrl(requestDto.getHomeImageUrl());
        home.setHomeStatus(requestDto.getHomeStatus());

        // 주소가 제공된 경우, Geocoding API를 통해 위도와 경도 설정
        if (requestDto.getAddress() != null && !requestDto.getAddress().isEmpty()) {

            try {
                geoLocationService.setCoordinatesFromAddress(home, requestDto.getAddress());
            } catch (JSONException e) {
                // 예외 처리 (예: 로깅, 사용자에게 피드백 제공)
                e.printStackTrace();
            }
            // GeoLocationService.setCoordinatesFromAddress(home, requestDto.getAddress());
        }

        // 엔티티 저장
        homeRepository.save(home);
    }

    public Home getHomeOrException(Long homeId) {
        return homeRepository.findById(homeId).orElseThrow(() ->
                new EntityNotFoundException("Home with ID " + homeId + " not found"));
    }

    @Transactional(readOnly = true)
    public HomeGetResponse getHome(Long homeId) {
        Home home = getHomeOrException(homeId);
        return HomeGetResponse.of(home);
    }
}
