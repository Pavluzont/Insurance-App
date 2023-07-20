package com.exadel.services;

import com.exadel.dto.ConsultantDto;
import com.exadel.entity.Consultant;
import com.exadel.mapper.ConsultantMapper;
import com.exadel.repository.ConsultantRepository;
import com.exadel.services.impl.ConsultantServiceImpl;
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
public class ConsultantServiceImplTest {
    public static final Long TEST_ID = 1L;

    @Mock
    private ConsultantRepository consultantRepository;

    @InjectMocks
    private ConsultantServiceImpl consultantService;

    @Mock
    private ConsultantMapper consultantMapper;

    @Test
    public void testFindAll() {
        List<Consultant> consultantsMock = new ArrayList<>();
        consultantsMock.add(Consultant.builder().id(1L).build());
        consultantsMock.add(Consultant.builder().id(2L).build());
        consultantsMock.add(Consultant.builder().id(3L).build());

        when(consultantRepository.findAll()).thenReturn(consultantsMock);

        List<ConsultantDto> consultants = consultantService.findAll();

        assertNotNull(consultants);
        assertEquals(3, consultants.size());
    }

    @Test
    public void testFindById() {
        Consultant consultantMock = Consultant.builder().id(TEST_ID).build();
        ConsultantDto consultantDtoMock = ConsultantDto.builder().id(TEST_ID).build();

        when(consultantRepository.findById(any())).thenReturn(Optional.of(consultantMock));
        when(consultantMapper.consultantToConsultantDto(any())).thenReturn(consultantDtoMock);

        ConsultantDto consultant = consultantService.findById(TEST_ID);

        assertNotNull(consultant);
        assertEquals(TEST_ID, consultant.getId());
    }

    @Test
    public void testFindByIdNotFound() {
        when(consultantRepository.findById(anyLong())).thenReturn(Optional.empty());

        ConsultantDto consultant = consultantService.findById(TEST_ID);

        assertNull(consultant);
    }

    @Test
    public void testSave() {
        Consultant consultantMock = Consultant.builder().build();
        ConsultantDto consultantDtoMock = ConsultantDto.builder().build();

        when(consultantRepository.save(any())).thenReturn(consultantMock);
        when(consultantMapper.consultantDtoToConsultant(any())).thenReturn(consultantMock);
        when(consultantMapper.consultantToConsultantDto(any())).thenReturn(consultantDtoMock);

        ConsultantDto consultantDto = consultantService.save(consultantDtoMock);

        assertNotNull(consultantDto);
        verify(consultantRepository, times(1)).save(any());
    }

    @Test
    public void testDelete() {
        ConsultantDto consultantDtoMock = ConsultantDto.builder().build();
        consultantService.delete(consultantDtoMock);

        verify(consultantRepository, times(1)).delete(any());
    }

    @Test
    public void testDeleteById() {
        consultantService.deleteById(TEST_ID);

        verify(consultantRepository, times(1)).deleteById(anyLong());
    }
}
