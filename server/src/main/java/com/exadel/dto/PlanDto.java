package com.exadel.dto;

import com.exadel.entity.InsuranceCompany;
import lombok.*;

import java.math.BigDecimal;
import java.time.Period;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PlanDto {
    @Setter(AccessLevel.NONE)
    private Long id;
    private String name;
    private BigDecimal price;
    private String duration;
    private String description;
//    private InsuranceCompanyDto insuranceCompany;
}
