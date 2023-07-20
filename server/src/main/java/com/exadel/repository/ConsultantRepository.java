package com.exadel.repository;

import com.exadel.entity.Consultant;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ConsultantRepository extends CrudRepository<Consultant, Long> {
    List<Consultant> findAll();
}
