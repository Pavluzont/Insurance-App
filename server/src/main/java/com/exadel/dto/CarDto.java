package com.exadel.dto;

import com.exadel.entity.Claim;
import com.exadel.entity.InsuranceCompany;
import com.exadel.entity.Owner;
import jakarta.persistence.Column;
import lombok.*;

import java.time.Instant;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CarDto {
    @Setter(AccessLevel.NONE)
    private Long id;
    private String name;
    private Instant createdDate;
    private String model;
    private OwnerDto owner;
    @Column(name = "insurance_company")
    private InsuranceCompanyDto insuranceCompany;

    private ContractDto contract;

    private List<ClaimDto> claim;

    private String vin;

    private String color;

    @Column(name = "plate_num")
    private String plateNum;
}
