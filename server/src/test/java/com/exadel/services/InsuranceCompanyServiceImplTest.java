package com.exadel.services;

import com.exadel.dto.InsuranceCompanyDto;
import com.exadel.entity.InsuranceCompany;
import com.exadel.mapper.InsuranceCompanyMapper;
import com.exadel.repository.InsuranceCompanyRepository;
import com.exadel.services.impl.InsuranceCompanyServiceImpl;
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
public class InsuranceCompanyServiceImplTest {
    public static final Long TEST_ID = 1L;

    @Mock
    private InsuranceCompanyRepository insuranceCompanyRepository;

    @InjectMocks
    private InsuranceCompanyServiceImpl insuranceCompanyService;

    @Mock
    private InsuranceCompanyMapper insuranceCompanyMapper;

    @Test
    public void testFindAll() {
        List<InsuranceCompany> insuranceCompanysMock = new ArrayList<>();
        insuranceCompanysMock.add(InsuranceCompany.builder().id(1L).build());
        insuranceCompanysMock.add(InsuranceCompany.builder().id(2L).build());
        insuranceCompanysMock.add(InsuranceCompany.builder().id(3L).build());

        when(insuranceCompanyRepository.findAll()).thenReturn(insuranceCompanysMock);

        List<InsuranceCompanyDto> insuranceCompanys = insuranceCompanyService.findAll();

        assertNotNull(insuranceCompanys);
        assertEquals(3, insuranceCompanys.size());
    }

    @Test
    public void testFindById() {
        InsuranceCompany insuranceCompanyMock = InsuranceCompany.builder().id(TEST_ID).build();
        InsuranceCompanyDto insuranceCompanyDtoMock = InsuranceCompanyDto.builder().id(TEST_ID).build();

        when(insuranceCompanyRepository.findById(any())).thenReturn(Optional.of(insuranceCompanyMock));
        when(insuranceCompanyMapper.insuranceCompanyToInsuranceCompanyDto(any())).thenReturn(insuranceCompanyDtoMock);

        InsuranceCompanyDto insuranceCompany = insuranceCompanyService.findById(TEST_ID);

        assertNotNull(insuranceCompany);
        assertEquals(TEST_ID, insuranceCompany.getId());
    }

    @Test
    public void testFindByIdNotFound() {
        when(insuranceCompanyRepository.findById(anyLong())).thenReturn(Optional.empty());

        InsuranceCompanyDto insuranceCompany = insuranceCompanyService.findById(TEST_ID);

        assertNull(insuranceCompany);
    }

    @Test
    public void testSave() {
        InsuranceCompany insuranceCompanyMock = InsuranceCompany.builder().build();
        InsuranceCompanyDto insuranceCompanyDtoMock = InsuranceCompanyDto.builder().build();

        when(insuranceCompanyRepository.save(any())).thenReturn(insuranceCompanyMock);
        when(insuranceCompanyMapper.insuranceCompanyDtoToInsuranceCompany(any())).thenReturn(insuranceCompanyMock);
        when(insuranceCompanyMapper.insuranceCompanyToInsuranceCompanyDto(any())).thenReturn(insuranceCompanyDtoMock);

        InsuranceCompanyDto insuranceCompanyDto = insuranceCompanyService.save(insuranceCompanyDtoMock);

        assertNotNull(insuranceCompanyDto);
        verify(insuranceCompanyRepository, times(1)).save(any());
    }

    @Test
    public void testDelete() {
        InsuranceCompanyDto insuranceCompanyDtoMock = InsuranceCompanyDto.builder().build();
        insuranceCompanyService.delete(insuranceCompanyDtoMock);

        verify(insuranceCompanyRepository, times(1)).delete(any());
    }

    @Test
    public void testDeleteById() {
        insuranceCompanyService.deleteById(TEST_ID);

        verify(insuranceCompanyRepository, times(1)).deleteById(anyLong());
    }
}
