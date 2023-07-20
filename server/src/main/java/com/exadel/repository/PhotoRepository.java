package com.exadel.repository;

import com.exadel.entity.Owner;
import com.exadel.entity.Photo;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PhotoRepository extends CrudRepository<Photo, Long> {
    List<Photo> findAll();
}
