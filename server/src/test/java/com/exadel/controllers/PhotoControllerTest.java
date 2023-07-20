package com.exadel.controllers;

import com.exadel.dto.PhotoDto;
import com.exadel.services.PhotoService;
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

@WebMvcTest(PhotoController.class)
@ExtendWith(MockitoExtension.class)
public class PhotoControllerTest {
    private static final String TEST_URL = "/photos";

    @Autowired
    MockMvc mockMvc;

    @MockBean
    PhotoService photoService;

    @Test
    public void testAddPhoto() throws Exception {
        PhotoDto photoDtoMock = PhotoDto.builder().build();

        String inputJson = this.toJson(photoDtoMock);
        when(photoService.save(Mockito.any(PhotoDto.class))).thenReturn(photoDtoMock);

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
    public void testGetPhotoById() throws Exception {
        PhotoDto photoDtoMock = PhotoDto.builder().id(1L).build();

        when(photoService.findById(Mockito.anyLong())).thenReturn(photoDtoMock);

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get(TEST_URL + "/1")
                .accept(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        String expectedJson = this.toJson(photoDtoMock);
        String outputJson = result.getResponse().getContentAsString();

        assertEquals(expectedJson, outputJson);
    }

    @Test
    public void testGetAllPhotos() throws Exception {
        List<PhotoDto> photosDtoMock = new ArrayList<>();
        photosDtoMock.add(PhotoDto.builder().id(1L).build());
        photosDtoMock.add(PhotoDto.builder().id(2L).build());
        photosDtoMock.add(PhotoDto.builder().id(3L).build());

        when(photoService.findAll()).thenReturn(photosDtoMock);

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get(TEST_URL)
                .accept(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        String expectedJson = this.toJson(photosDtoMock);
        String outputJson = result.getResponse().getContentAsString();

        assertEquals(expectedJson, outputJson);
    }

    @Test
    public void testDeletePhotoById() throws Exception {
        PhotoDto photoDtoMock = PhotoDto.builder().id(1L).build();

        when(photoService.findById(Mockito.anyLong())).thenReturn(photoDtoMock);

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
