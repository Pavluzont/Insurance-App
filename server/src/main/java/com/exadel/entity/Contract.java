package com.exadel.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Table(name = "contracts")
public class Contract extends BaseEntity {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "plan_id")
    //@JsonBackReference(value="plan-contract")
    private Plan plan;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "contract")
    //    @JsonManagedReference(value="claim-contract")
    private List<Claim> claims;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "consultant_id")
    //@JsonBackReference(value="consultant-contract")
    private Consultant consultant;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "insurance_company_id")
//    @JsonBackReference(value="insuranceCompany-plan")
    private InsuranceCompany insuranceCompany;

    @Column(name = "start_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate startDate;

    @Column(name = "end_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate endDate;

    @OneToOne
    @JoinColumn(name="car_id")
//    @JsonManagedReference(value="contract-car")
    private Car car;
}
