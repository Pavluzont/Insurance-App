package com.exadel.repository;

import com.exadel.entity.Contract;
import com.exadel.entity.Document;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DocumentRepository extends CrudRepository<Document, Long> {
    List<Document> findAll();
}
