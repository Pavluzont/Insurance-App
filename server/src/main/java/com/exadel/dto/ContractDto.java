package com.exadel.dto;

import com.exadel.entity.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.time.Instant;
import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ContractDto {
    @Setter(AccessLevel.NONE)
    private Long id;
    private PlanDto plan;

    private CarDto car;
    private InsuranceCompanyDto insuranceCompany;
    private ConsultantDto consultant;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate startDate;
    private Object DateUtils;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate endDate;
    private Instant createdDate;

    private List<ClaimDto> claims;
}
