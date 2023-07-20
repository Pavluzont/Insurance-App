package com.exadel.controllers;

import com.exadel.dto.ConsultantDto;
import com.exadel.services.ConsultantService;
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

@WebMvcTest(ConsultantController.class)
@ExtendWith(MockitoExtension.class)
public class ConsultantControllerTest {
    private static final String TEST_URL = "/consultants";

    @Autowired
    MockMvc mockMvc;

    @MockBean
    ConsultantService consultantService;

    @Test
    public void testAddConsultant() throws Exception {
        ConsultantDto consultantDtoMock = ConsultantDto.builder().build();

        String inputJson = this.toJson(consultantDtoMock);
        when(consultantService.save(Mockito.any(ConsultantDto.class))).thenReturn(consultantDtoMock);

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
    public void testGetConsultantById() throws Exception {
        ConsultantDto consultantDtoMock = ConsultantDto.builder().id(1L).build();

        when(consultantService.findById(Mockito.anyLong())).thenReturn(consultantDtoMock);

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get(TEST_URL + "/1")
                .accept(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        String expectedJson = this.toJson(consultantDtoMock);
        String outputJson = result.getResponse().getContentAsString();

        assertEquals(expectedJson, outputJson);
    }

    @Test
    public void testGetAllConsultants() throws Exception {
        List<ConsultantDto> consultantsDtoMock = new ArrayList<>();
        consultantsDtoMock.add(ConsultantDto.builder().id(1L).build());
        consultantsDtoMock.add(ConsultantDto.builder().id(2L).build());
        consultantsDtoMock.add(ConsultantDto.builder().id(3L).build());

        when(consultantService.findAll()).thenReturn(consultantsDtoMock);

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get(TEST_URL)
                .accept(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        String expectedJson = this.toJson(consultantsDtoMock);
        String outputJson = result.getResponse().getContentAsString();

        assertEquals(expectedJson, outputJson);
    }

    @Test
    public void testDeleteConsultantById() throws Exception {
        ConsultantDto consultantDtoMock = ConsultantDto.builder().id(1L).build();

        when(consultantService.findById(Mockito.anyLong())).thenReturn(consultantDtoMock);

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
