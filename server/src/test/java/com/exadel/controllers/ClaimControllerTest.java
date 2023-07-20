package com.exadel.controllers;

import com.exadel.dto.ClaimDto;
import com.exadel.services.ClaimService;
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

@WebMvcTest(ClaimController.class)
@ExtendWith(MockitoExtension.class)
public class ClaimControllerTest {
    private static final String TEST_URL = "/claims";

    @Autowired
    MockMvc mockMvc;

    @MockBean
    ClaimService claimService;

    @Test
    public void testAddClaim() throws Exception {
        ClaimDto claimDtoMock = ClaimDto.builder().build();

        String inputJson = this.toJson(claimDtoMock);
        when(claimService.save(Mockito.any(ClaimDto.class))).thenReturn(claimDtoMock);

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
    public void testGetClaimById() throws Exception {
        ClaimDto claimDtoMock = ClaimDto.builder().id(1L).build();

        when(claimService.findById(Mockito.anyLong())).thenReturn(claimDtoMock);

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get(TEST_URL + "/1")
                .accept(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        String expectedJson = this.toJson(claimDtoMock);
        String outputJson = result.getResponse().getContentAsString();

        assertEquals(expectedJson, outputJson);
    }

    @Test
    public void testGetAllClaims() throws Exception {
        List<ClaimDto> claimsDtoMock = new ArrayList<>();
        claimsDtoMock.add(ClaimDto.builder().id(1L).build());
        claimsDtoMock.add(ClaimDto.builder().id(2L).build());
        claimsDtoMock.add(ClaimDto.builder().id(3L).build());

        when(claimService.findAll()).thenReturn(claimsDtoMock);

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get(TEST_URL)
                .accept(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        String expectedJson = this.toJson(claimsDtoMock);
        String outputJson = result.getResponse().getContentAsString();

        assertEquals(expectedJson, outputJson);
    }

    @Test
    public void testDeleteClaimById() throws Exception {
        ClaimDto claimDtoMock = ClaimDto.builder().id(1L).build();

        when(claimService.findById(Mockito.anyLong())).thenReturn(claimDtoMock);

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
