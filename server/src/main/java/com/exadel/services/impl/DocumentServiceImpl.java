package com.exadel.services.impl;

import com.exadel.dto.DocumentDto;
import com.exadel.entity.Document;
import com.exadel.exception.NotFoundException;
import com.exadel.mapper.DocumentMapper;
import com.exadel.repository.DocumentRepository;
import com.exadel.services.DocumentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class DocumentServiceImpl implements DocumentService {
    private final DocumentRepository documentRepository;

    private final DocumentMapper documentMapper;

    public void validate(Long documentId) {
        DocumentDto document = findById(documentId);
        if (Objects.isNull(document)) {
            throw new NotFoundException("document is not found with id: " + documentId);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<DocumentDto> findAll() {
        log.info("Find all documents");
        return documentRepository.findAll().stream()
                .map(documentMapper::documentToDocumentDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public DocumentDto findById(Long id) {
        log.info("Get document by id={}", id);
        Document document = documentRepository.findById(id).orElse(null);
        return documentMapper.documentToDocumentDto(document);
    }

    @Override
    @Transactional
    public DocumentDto save(DocumentDto documentDto) {
        log.info("Save document");
        Document document = documentMapper.documentDtoToDocument(documentDto);
        Document updatedDocument = documentRepository.save(document);
        return documentMapper.documentToDocumentDto(updatedDocument);
    }

    @Override
    @Transactional
    public void delete(DocumentDto documentDto) {
        log.info("Delete document");
        documentRepository.delete(documentMapper.documentDtoToDocument(documentDto));
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        log.info("Delete document by id id={}", id);
        documentRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void deleteAll() {
        log.info("Delete all documents");
        documentRepository.deleteAll();
    }
}
