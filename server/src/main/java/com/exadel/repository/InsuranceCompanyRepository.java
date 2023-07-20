package com.exadel.repository;

import com.exadel.entity.Document;
import com.exadel.entity.InsuranceCompany;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InsuranceCompanyRepository extends CrudRepository<InsuranceCompany, Long> {
    List<InsuranceCompany> findAll();
}
