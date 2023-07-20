package com.exadel.controllers;

import com.exadel.dto.OwnerDto;
import com.exadel.services.OwnerService;
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

@WebMvcTest(OwnerController.class)
@ExtendWith(MockitoExtension.class)
public class OwnerControllerTest {
    private static final String TEST_URL = "/owners";

    @Autowired
    MockMvc mockMvc;

    @MockBean
    OwnerService ownerService;

    @Test
    public void testAddOwner() throws Exception {
        OwnerDto ownerDtoMock = OwnerDto.builder().build();

        String inputJson = this.toJson(ownerDtoMock);
        when(ownerService.save(Mockito.any(OwnerDto.class))).thenReturn(ownerDtoMock);

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
    public void testGetOwnerById() throws Exception {
        OwnerDto ownerDtoMock = OwnerDto.builder().id(1L).build();

        when(ownerService.findById(Mockito.anyLong())).thenReturn(ownerDtoMock);

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get(TEST_URL + "/1")
                .accept(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        String expectedJson = this.toJson(ownerDtoMock);
        String outputJson = result.getResponse().getContentAsString();

        assertEquals(expectedJson, outputJson);
    }

    @Test
    public void testGetAllOwners() throws Exception {
        List<OwnerDto> ownersDtoMock = new ArrayList<>();
        ownersDtoMock.add(OwnerDto.builder().id(1L).build());
        ownersDtoMock.add(OwnerDto.builder().id(2L).build());
        ownersDtoMock.add(OwnerDto.builder().id(3L).build());

        when(ownerService.findAll()).thenReturn(ownersDtoMock);

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get(TEST_URL)
                .accept(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        String expectedJson = this.toJson(ownersDtoMock);
        String outputJson = result.getResponse().getContentAsString();

        assertEquals(expectedJson, outputJson);
    }

    @Test
    public void testDeleteOwnerById() throws Exception {
        OwnerDto ownerDtoMock = OwnerDto.builder().id(1L).build();

        when(ownerService.findById(Mockito.anyLong())).thenReturn(ownerDtoMock);

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
