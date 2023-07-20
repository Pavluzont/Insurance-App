package com.exadel.repository;

import com.exadel.entity.Consultant;
import com.exadel.entity.Contract;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContractRepository extends CrudRepository<Contract, Long> {
    List<Contract> findAll();
}
