package com.webproject.chonstay_backend.home;

import com.webproject.chonstay_backend.home.dto.HomeGetResponse;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class HomeService {

    private final HomeRepository homeRepository;

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
