package com.exadel.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import jakarta.persistence.*;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Table(name = "insurance_companies")
public class InsuranceCompany extends BaseEntity {
    private String name;

    @Embedded
    private Address address;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "insuranceCompany")
    //    @JsonManagedReference(value="consultant-insuranceCompany")
    private List<Consultant> consultants;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "insuranceCompany")
    //    @JsonManagedReference(value="plan-insuranceCompany")
    private List<Plan> plans;
}
