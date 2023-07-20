package com.exadel.dto;

import com.exadel.entity.Address;
import com.exadel.entity.Contract;
import com.exadel.entity.Gender;
import com.exadel.entity.InsuranceCompany;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ConsultantDto {
    @Setter(AccessLevel.NONE)
    private Long id;
    private String firstName;
    private String lastName;
    private int age;
    private Gender gender;
    private String phoneNumber;
    private String email;

    private Address address;
//    private InsuranceCompanyDto insuranceCompany;
//    private List<ContractDto> contracts;
}
