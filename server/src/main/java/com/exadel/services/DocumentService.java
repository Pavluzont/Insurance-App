package com.exadel.services;


import com.exadel.dto.DocumentDto;

public interface DocumentService extends CrudService<DocumentDto, Long> {
    void validate(Long documentId);
}
