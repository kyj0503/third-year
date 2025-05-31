package com.webproject.chonstay_backend.home.dto;

import com.webproject.chonstay_backend.home.Home;
import java.math.BigDecimal;

public record HomeGetResponse(
        String homeName,
        String address,
        BigDecimal latitude,
        BigDecimal longitude,
        Short maxGuest,
        String description,
        String homeImageUrl,
        Boolean homeStatus
) {
    public static HomeGetResponse of(Home home) {
        return new HomeGetResponse(
                home.getHomeName(),
                home.getAddress(),
                home.getLatitude(),
                home.getLongitude(),
                home.getMaxGuest(),
                home.getDescription(),
                home.getHomeImageUrl(),
                home.getHomeStatus()
        );
    }
}
