package com.exadel.entity;

import jakarta.persistence.Column;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class Address {
    private String country;

    private String state;

    private String city;

    private String street;

    @Column(name = "home_num")
    private String homeNum;

    @Column(name = "post_code")
    private String postCode;
}
