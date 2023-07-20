package com.exadel.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Data
@Entity
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Table(name = "owners")
public class Owner extends Person {
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "owner")
    //    @JsonManagedReference(value="car-owner")
    private List<Car> cars;
}
