package com.exadel.controllers;

import com.exadel.dto.DocumentDto;
import com.exadel.services.DocumentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@CrossOrigin
@RestController
@RequestMapping("/documents")
@RequiredArgsConstructor
@Tag(name = "Documents")
public class DocumentController {
    private final DocumentService documentService;

    @GetMapping
    public List<DocumentDto> findAll() {
        log.info("Find all documents");
        return documentService.findAll();
    }

    @GetMapping("/{documentId}")
    @Operation(description = "Get endpoint for document", summary = "This is a summary for document endpoint")
    @ApiResponses(value = {
            @ApiResponse(description = "Success", responseCode = "200"),
            @ApiResponse(description = "document not found", responseCode = "404")
    })
    public DocumentDto getDocument(@PathVariable Long documentId) {
        log.info("Get document by id={}", documentId);
        documentService.validate(documentId);
        return documentService.findById(documentId);
    }

    @PostMapping
    @Operation(description = "Save a new document", summary = "This is a summary for document endpoint")
    @ApiResponses(value = {
            @ApiResponse(description = "document was successfully added", responseCode = "201"),
    })
    public DocumentDto adddocument(@RequestBody DocumentDto documentDto) {
        log.info("Save document");
        DocumentDto dbDocument = documentService.save(documentDto);

        return dbDocument;
    }

    @PutMapping
    @Operation(description = "Get update for document", summary = "This is a summary for document endpoint")
    @ApiResponses(value = {
            @ApiResponse(description = "Success update", responseCode = "202"),
            @ApiResponse(description = "document not found", responseCode = "404")
    })
    public DocumentDto updatedocument(@RequestBody DocumentDto documentDto) {
        log.info("Update document");
        return documentService.save(documentDto);
    }

    private void deleteDocument(@RequestBody DocumentDto documentDto) {
        log.info("Delete document");
        documentService.delete(documentDto);
    }

    @DeleteMapping("/{documentId}")
    @Operation(description = "Delete a document", summary = "This is a summary for document endpoint")
    @ApiResponses(value = {
            @ApiResponse(description = "document was successfully deleted", responseCode = "204"),
            @ApiResponse(description = "document not found", responseCode = "404")
    })
    public void deleteDocumentById(@PathVariable Long documentId) {
        log.info("Delete document by id id={}", documentId);
        documentService.validate(documentId);
        documentService.deleteById(documentId);
    }

    @DeleteMapping
    @Operation(description = "Delete all documents", summary = "This is a summary for documents endpoint")
    @ApiResponses(value = {
            @ApiResponse(description = "documents were successfully deleted", responseCode = "204")
    })
    public void deleteAll() {
        log.info("Delete all documents");
        documentService.deleteAll();
    }
}
