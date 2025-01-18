package com.webproject.chonstay_backend.home;

import com.webproject.chonstay_backend.common.Location;
import com.webproject.chonstay_backend.home.dto.HomeGetResponse;
import com.webproject.chonstay_backend.home.dto.HomePreview;
import com.webproject.chonstay_backend.home.dto.HomesGetResponse;
import jakarta.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
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

    @Transactional(readOnly = true)
    public HomesGetResponse getResultHomes(String searchQuery, Location location) {
        List<Home> homes;

        if (searchQuery != null && location != null) {
            homes = homeRepository.findByHomeNameContainingIgnoreCaseOrDescriptionContainingIgnoreCaseOrAddressContainingIgnoreCaseAndLocationAndHomeStatusTrue(
                    searchQuery, searchQuery, searchQuery, location);
        } else if (searchQuery != null) {
            Set<Home> homeSet = new HashSet<>();
            homeSet.addAll(homeRepository.findByHomeNameContainingIgnoreCaseAndHomeStatusTrue(searchQuery));
            homeSet.addAll(homeRepository.findByDescriptionContainingIgnoreCaseAndHomeStatusTrue(searchQuery));
            homeSet.addAll(homeRepository.findByAddressContainingIgnoreCaseAndHomeStatusTrue(searchQuery));
            homes = new ArrayList<>(homeSet);
        } else if (location != null) {
            homes = homeRepository.findByLocationAndHomeStatusTrue(location);
        } else {
            homes = homeRepository.findAllByHomeStatusTrue();
        }

        List<HomePreview> homePreviews = homes.stream()
                .map(HomePreview::of)
                .collect(Collectors.toList());
        return HomesGetResponse.of(homePreviews);
    }
}
