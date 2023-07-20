package com.exadel.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AdressDto {
    private Long id;
    private String country;
    private String state;
    private String city;
    private String street;
    private String homeNum;
    private String postCode;
}
