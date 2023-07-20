package com.exadel.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@MappedSuperclass
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Person extends BaseEntity {
    @Column(name="first_name")
    private String firstName;

    @Column(name="last_name")
    private String lastName;

    private int age;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Column(name="phone_number")
    private String phoneNumber;

    @Column(name="email")
    private String email;

    private String password;

    @Embedded
    private Address address;

    @Column(updatable = false)
    private String uid;
}
