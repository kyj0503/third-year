package com.webproject.chonstay_backend.home;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class HomeRegisterRequestDto {
    private String homeName;
    private String address;
    private Short maxGuest;
    private BigDecimal latitude;  // null 가능
    private BigDecimal longitude; // null 가능
    private String description;
    private String homeImageUrl;
    private Boolean homeStatus;
}
