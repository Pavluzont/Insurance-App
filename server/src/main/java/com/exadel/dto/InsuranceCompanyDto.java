package com.exadel.dto;

import com.exadel.entity.Address;
import com.exadel.entity.Consultant;
import com.exadel.entity.Plan;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InsuranceCompanyDto {
    @Setter(AccessLevel.NONE)
    private Long id;
    private String name;
    private Address address;

    private List<ConsultantDto> consultants;
    private List<PlanDto> plans;
}
