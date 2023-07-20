package com.exadel.repository;

import com.exadel.entity.Car;
import com.exadel.entity.Claim;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClaimRepository extends CrudRepository<Claim, Long> {
    List<Claim> findAll();
}
