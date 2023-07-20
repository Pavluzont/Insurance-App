package com.exadel.dto;

import com.exadel.entity.Address;
import com.exadel.entity.Car;
import com.exadel.entity.Gender;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OwnerDto {
    @Setter(AccessLevel.NONE)
    private Long id;
    private String firstName;
    private String lastName;
    private int age;
    private Gender gender;
    private String phoneNumber;
    private String email;
    private String password;
    private Address address;
//    private List<Car> cars;

    private String uid;
}
