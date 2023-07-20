package com.exadel.controllers;

import com.exadel.dto.DocumentDto;
import com.exadel.services.DocumentService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@WebMvcTest(DocumentController.class)
@ExtendWith(MockitoExtension.class)
public class DocumentControllerTest {
    private static final String TEST_URL = "/documents";

    @Autowired
    MockMvc mockMvc;

    @MockBean
    DocumentService documentService;

    @Test
    public void testAddDocument() throws Exception {
        DocumentDto documentDtoMock = DocumentDto.builder().build();

        String inputJson = this.toJson(documentDtoMock);
        when(documentService.save(Mockito.any(DocumentDto.class))).thenReturn(documentDtoMock);

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post(TEST_URL)
                .accept(MediaType.APPLICATION_JSON).content(inputJson)
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();
        String outputJson = response.getContentAsString();

        assertEquals(inputJson, outputJson);
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
    }

    @Test
    public void testGetDocumentById() throws Exception {
        DocumentDto documentDtoMock = DocumentDto.builder().id(1L).build();

        when(documentService.findById(Mockito.anyLong())).thenReturn(documentDtoMock);

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get(TEST_URL + "/1")
                .accept(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        String expectedJson = this.toJson(documentDtoMock);
        String outputJson = result.getResponse().getContentAsString();

        assertEquals(expectedJson, outputJson);
    }

    @Test
    public void testGetAllDocuments() throws Exception {
        List<DocumentDto> documentsDtoMock = new ArrayList<>();
        documentsDtoMock.add(DocumentDto.builder().id(1L).build());
        documentsDtoMock.add(DocumentDto.builder().id(2L).build());
        documentsDtoMock.add(DocumentDto.builder().id(3L).build());

        when(documentService.findAll()).thenReturn(documentsDtoMock);

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get(TEST_URL)
                .accept(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        String expectedJson = this.toJson(documentsDtoMock);
        String outputJson = result.getResponse().getContentAsString();

        assertEquals(expectedJson, outputJson);
    }

    @Test
    public void testDeleteDocumentById() throws Exception {
        DocumentDto documentDtoMock = DocumentDto.builder().id(1L).build();

        when(documentService.findById(Mockito.anyLong())).thenReturn(documentDtoMock);

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .delete(TEST_URL)
                .accept(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
    }

    private String toJson(Object object) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(object);
    }
}
