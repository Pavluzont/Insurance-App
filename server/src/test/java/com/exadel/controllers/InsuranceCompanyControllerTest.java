package com.exadel.controllers;

import com.exadel.dto.InsuranceCompanyDto;
import com.exadel.services.InsuranceCompanyService;
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

@WebMvcTest(InsuranceCompanyController.class)
@ExtendWith(MockitoExtension.class)
public class InsuranceCompanyControllerTest {
    private static final String TEST_URL = "/companies";

    @Autowired
    MockMvc mockMvc;

    @MockBean
    InsuranceCompanyService insuranceCompanyService;

    @Test
    public void testAddinsuranceCompany() throws Exception {
        InsuranceCompanyDto insuranceCompanyDtoMock = InsuranceCompanyDto.builder().build();

        String inputJson = this.toJson(insuranceCompanyDtoMock);
        when(insuranceCompanyService.save(Mockito.any(InsuranceCompanyDto.class))).thenReturn(insuranceCompanyDtoMock);

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
    public void testGetinsuranceCompanyById() throws Exception {
        InsuranceCompanyDto insuranceCompanyDtoMock = InsuranceCompanyDto.builder().id(1L).build();

        when(insuranceCompanyService.findById(Mockito.anyLong())).thenReturn(insuranceCompanyDtoMock);

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get(TEST_URL + "/1")
                .accept(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        String expectedJson = this.toJson(insuranceCompanyDtoMock);
        String outputJson = result.getResponse().getContentAsString();

        assertEquals(expectedJson, outputJson);
    }

    @Test
    public void testGetAllinsuranceCompanys() throws Exception {
        List<InsuranceCompanyDto> insuranceCompanysDtoMock = new ArrayList<>();
        insuranceCompanysDtoMock.add(InsuranceCompanyDto.builder().id(1L).build());
        insuranceCompanysDtoMock.add(InsuranceCompanyDto.builder().id(2L).build());
        insuranceCompanysDtoMock.add(InsuranceCompanyDto.builder().id(3L).build());

        when(insuranceCompanyService.findAll()).thenReturn(insuranceCompanysDtoMock);

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get(TEST_URL)
                .accept(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        String expectedJson = this.toJson(insuranceCompanysDtoMock);
        String outputJson = result.getResponse().getContentAsString();

        assertEquals(expectedJson, outputJson);
    }

    @Test
    public void testDeleteInsuranceCompanyById() throws Exception {
        InsuranceCompanyDto insuranceCompanyDtoMock = InsuranceCompanyDto.builder().id(1L).build();

        when(insuranceCompanyService.findById(Mockito.anyLong())).thenReturn(insuranceCompanyDtoMock);

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
