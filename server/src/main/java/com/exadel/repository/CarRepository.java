package com.exadel.repository;

import com.exadel.entity.Car;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarRepository extends CrudRepository<Car, Long> {
    List<Car> findAll();

    List<Car> findAllByOwnerId(Long ownerId);
}
