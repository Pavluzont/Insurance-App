package com.exadel.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Data
@Entity
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Table(name = "cars")
public class Car extends BaseEntity {
    private String name;

    private String model;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_id")
    //@JsonBackReference(value="owner-car")
    private Owner owner;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "insurance_company_id")
    //@JsonBackReference(value="insuranceCompany-car")
    private InsuranceCompany insuranceCompany;

//    @OneToOne(mappedBy = "car", cascade = CascadeType.ALL)
//    @JsonBackReference(value = "contract-car")
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="contract_id")
    private Contract contract;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "car")
    //    @JsonManagedReference(value="claim-car")
    private List<Claim> claim;

    private String vin;

    private String color;

    private String plateNum;
}
