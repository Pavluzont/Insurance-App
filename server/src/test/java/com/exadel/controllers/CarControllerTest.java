package com.exadel.controllers;

import com.exadel.dto.CarDto;
import com.exadel.entity.Car;
import com.exadel.services.CarService;
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

@WebMvcTest(CarController.class)
@ExtendWith(MockitoExtension.class)
public class CarControllerTest {
    private static final String TEST_URL = "/cars";

    @Autowired
    MockMvc mockMvc;

    @MockBean
    CarService carService;

    @Test
    public void testAddCar() throws Exception {
        CarDto carDtoMock = CarDto.builder().name("Nissan").build();

        String inputJson = this.toJson(carDtoMock);
        when(carService.save(Mockito.any(CarDto.class))).thenReturn(carDtoMock);

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
    public void testGetCarById() throws Exception {
        CarDto carDtoMock = CarDto.builder().id(1L).name("Nissan").build();

        when(carService.findById(Mockito.anyLong())).thenReturn(carDtoMock);

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get(TEST_URL + "/1")
                .accept(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        String expectedJson = this.toJson(carDtoMock);
        String outputJson = result.getResponse().getContentAsString();

        assertEquals(expectedJson, outputJson);
    }

    @Test
    public void testGetAllCars() throws Exception {
        List<CarDto> carsDtoMock = new ArrayList<>();
        carsDtoMock.add(CarDto.builder().id(1L).name("Nissan").build());
        carsDtoMock.add(CarDto.builder().id(2L).name("Toyota").build());
        carsDtoMock.add(CarDto.builder().id(3L).name("Mazda").build());

        when(carService.findAll()).thenReturn(carsDtoMock);

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get(TEST_URL)
                .accept(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        String expectedJson = this.toJson(carsDtoMock);
        String outputJson = result.getResponse().getContentAsString();

        assertEquals(expectedJson, outputJson);
    }

    @Test
    public void testDeleteCarById() throws Exception {
        CarDto carDtoMock = CarDto.builder().id(1L).name("Nissan").build();

        when(carService.findById(Mockito.anyLong())).thenReturn(carDtoMock);

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
