package com.exadel.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import jakarta.persistence.*;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Data
@Entity
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Table(name = "consultants")
public class Consultant extends Person {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "insurance_company_id")
    //@JsonBackReference(value="insuranceCompany-consultant")
    private InsuranceCompany insuranceCompany;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "consultant")
    //    @JsonManagedReference(value="contract-consultant")
    private List<Contract> contracts;
}
