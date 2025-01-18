package com.webproject.chonstay_backend.home.dto;

import java.util.List;

public record HomesGetResponse(
        List<HomePreview> homePreviews
) {
    public static HomesGetResponse of(List<HomePreview> homePreviews) {
        return new HomesGetResponse(homePreviews);
    }
}
