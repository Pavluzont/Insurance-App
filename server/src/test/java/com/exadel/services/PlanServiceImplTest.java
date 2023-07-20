package com.exadel.services;

import com.exadel.dto.PlanDto;
import com.exadel.entity.Plan;
import com.exadel.mapper.PlanMapper;
import com.exadel.repository.PlanRepository;
import com.exadel.services.impl.PlanServiceImpl;
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
public class PlanServiceImplTest {
    public static final Long TEST_ID = 1L;

    @Mock
    private PlanRepository planRepository;

    @InjectMocks
    private PlanServiceImpl planService;

    @Mock
    private PlanMapper planMapper;

    @Test
    public void testFindAll() {
        List<Plan> plansMock = new ArrayList<>();
        plansMock.add(Plan.builder().id(1L).build());
        plansMock.add(Plan.builder().id(2L).build());
        plansMock.add(Plan.builder().id(3L).build());

        when(planRepository.findAll()).thenReturn(plansMock);

        List<PlanDto> plans = planService.findAll();

        assertNotNull(plans);
        assertEquals(3, plans.size());
    }

    @Test
    public void testFindById() {
        Plan planMock = Plan.builder().id(TEST_ID).build();
        PlanDto planDtoMock = PlanDto.builder().id(TEST_ID).build();

        when(planRepository.findById(any())).thenReturn(Optional.of(planMock));
        when(planMapper.planToPlanDto(any())).thenReturn(planDtoMock);

        PlanDto plan = planService.findById(TEST_ID);

        assertNotNull(plan);
        assertEquals(TEST_ID, plan.getId());
    }

    @Test
    public void testFindByIdNotFound() {
        when(planRepository.findById(anyLong())).thenReturn(Optional.empty());

        PlanDto plan = planService.findById(TEST_ID);

        assertNull(plan);
    }

    @Test
    public void testSave() {
        Plan planMock = Plan.builder().build();
        PlanDto planDtoMock = PlanDto.builder().build();

        when(planRepository.save(any())).thenReturn(planMock);
        when(planMapper.planDtoToPlan(any())).thenReturn(planMock);
        when(planMapper.planToPlanDto(any())).thenReturn(planDtoMock);
        PlanDto planDto = planService.save(planDtoMock);

        assertNotNull(planDto);
        verify(planRepository, times(1)).save(any());
    }

    @Test
    public void testDelete() {
        PlanDto planDtoMock = PlanDto.builder().build();
        planService.delete(planDtoMock);

        verify(planRepository, times(1)).delete(any());
    }

    @Test
    public void testDeleteById() {
        planService.deleteById(TEST_ID);

        verify(planRepository, times(1)).deleteById(anyLong());
    }
}
