package com.example.kproject.request;

public class ReviewRequest {
    private Long placeId;  // 리뷰를 남길 장소의 ID
    private int rating;     // 별점 (1~10)
    private String comment; // 리뷰 내용

    public Long getPlaceId() {
        return placeId;
    }

    public int getRating() {
        return rating;
    }

    public String getComment() {
        return comment;
    }
}
