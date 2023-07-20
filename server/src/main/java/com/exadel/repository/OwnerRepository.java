package com.exadel.repository;

import com.exadel.entity.InsuranceCompany;
import com.exadel.entity.Owner;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OwnerRepository extends CrudRepository<Owner, Long> {
    List<Owner> findAll();

    Owner findByUid(String uid);
}
