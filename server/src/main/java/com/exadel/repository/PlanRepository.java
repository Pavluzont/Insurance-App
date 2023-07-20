package com.exadel.repository;

import com.exadel.entity.Photo;
import com.exadel.entity.Plan;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlanRepository extends CrudRepository<Plan, Long> {
    List<Plan> findAll();
}
