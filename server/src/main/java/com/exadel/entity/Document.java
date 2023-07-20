package com.exadel.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Table(name = "documents")
public class Document extends BaseEntity {
    private String name;

    private BigDecimal amount;

    @Column(name="claim_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDateTime claimIssueDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "claim_id")
//    //@JsonBackReference(value="claim-document")
    private Claim claim;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "car_id")
//    //@JsonBackReference(value="car-document")
    private Car car;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "document")
    //    @JsonManagedReference(value="photo-document")
    private List<Photo> photos;
}
