package com.webproject.chonstay_backend.home.dto;

import java.util.List;

public record HomesGetResponse(
        List<HomeGetResponse> homePreviews
) {
    public static HomesGetResponse of(List<HomeGetResponse> homePreviews) {
        return new HomesGetResponse(homePreviews);
    }
}
