package com.webproject.chonstay_backend.attraction;

public class Attraction {
    private String name;
    private Double latitude; // 위도
    private Double longitude; // 경도

    public Attraction(String name, Double latitude, Double longitude) {
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
    }
    // Getter
    public String getName() {
        return name;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    @Override
    public String toString() {
        return String.format("Attraction{name='%s', latitude=%.6f, longitude=%.6f}", name, latitude, longitude);
    }
}
