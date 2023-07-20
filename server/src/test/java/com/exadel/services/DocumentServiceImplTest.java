package com.exadel.services;

import com.exadel.dto.DocumentDto;
import com.exadel.entity.Document;
import com.exadel.mapper.DocumentMapper;
import com.exadel.repository.DocumentRepository;
import com.exadel.services.impl.DocumentServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class DocumentServiceImplTest {
    public static final Long TEST_ID = 1L;

    @Mock
    private DocumentRepository documentRepository;

    @InjectMocks
    private DocumentServiceImpl documentService;

    @Mock
    private DocumentMapper documentMapper;

    @Test
    public void testFindAll() {
        List<Document> documentsMock = new ArrayList<>();
        documentsMock.add(Document.builder().id(1L).build());
        documentsMock.add(Document.builder().id(2L).build());
        documentsMock.add(Document.builder().id(3L).build());

        when(documentRepository.findAll()).thenReturn(documentsMock);

        List<DocumentDto> documents = documentService.findAll();

        assertNotNull(documents);
        assertEquals(3, documents.size());
    }

    @Test
    public void testFindById() {
        Document documentMock = Document.builder().id(TEST_ID).build();
        DocumentDto documentDtoMock = DocumentDto.builder().id(TEST_ID).build();
        documentRepository.save(Document.builder().id(TEST_ID).build());

        when(documentRepository.findById(any())).thenReturn(Optional.of(documentMock));
        when(documentMapper.documentToDocumentDto(any())).thenReturn(documentDtoMock);

        DocumentDto document = documentService.findById(TEST_ID);

        assertNotNull(document);
        assertEquals(TEST_ID, document.getId());
    }

    @Test
    public void testFindByIdNotFound() {
        when(documentRepository.findById(anyLong())).thenReturn(Optional.empty());

        DocumentDto document = documentService.findById(TEST_ID);

        assertNull(document);
    }

    @Test
    public void testSave() {
        Document documentMock = Document.builder().build();
        DocumentDto documentDtoMock = DocumentDto.builder().build();

        when(documentRepository.save(any())).thenReturn(documentMock);
        when(documentMapper.documentDtoToDocument(any())).thenReturn(documentMock);
        when(documentMapper.documentToDocumentDto(any())).thenReturn(documentDtoMock);

        DocumentDto documentDto = documentService.save(documentDtoMock);

        assertNotNull(documentDto);
        verify(documentRepository, times(1)).save(any());
    }

    @Test
    public void testDelete() {
        DocumentDto documentDtoMock = DocumentDto.builder().build();
        documentService.delete(documentDtoMock);

        verify(documentRepository, times(1)).delete(any());
    }

    @Test
    public void testDeleteById() {
        documentService.deleteById(TEST_ID);

        verify(documentRepository, times(1)).deleteById(anyLong());
    }
}
