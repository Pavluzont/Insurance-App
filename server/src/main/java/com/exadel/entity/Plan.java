package com.exadel.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import com.fasterxml.jackson.annotation.JsonBackReference;

import java.math.BigDecimal;
import java.time.Period;

@Data
@Entity
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Table(name = "plans")
public class Plan extends BaseEntity {
    private BigDecimal price;

    private String name;

    private String duration;

    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "insurance_company_id")
//    @JsonBackReference(value="insuranceCompany-plan")
    private InsuranceCompany insuranceCompany;
}
