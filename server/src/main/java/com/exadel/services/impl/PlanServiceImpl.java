package com.exadel.services.impl;

import com.exadel.dto.PlanDto;
import com.exadel.entity.Plan;
import com.exadel.exception.NotFoundException;
import com.exadel.mapper.PlanMapper;
import com.exadel.repository.PlanRepository;
import com.exadel.services.PlanService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class PlanServiceImpl implements PlanService {
    private final PlanRepository planRepository;

    private final PlanMapper planMapper;

    public void validate(Long planId) {
        PlanDto plan = findById(planId);
        if (Objects.isNull(plan)) {
            throw new NotFoundException("plan is not found with id: " + planId);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<PlanDto> findAll() {
        log.info("Find all plans");
        return planRepository.findAll().stream()
                .map(planMapper::planToPlanDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public PlanDto findById(Long id) {
        log.info("Get plan by id={}", id);
        Plan plan = planRepository.findById(id).orElse(null);
        return planMapper.planToPlanDto(plan);
    }

    @Override
    @Transactional
    public PlanDto save(PlanDto planDto) {
        log.info("Save plan");
        Plan plan = planMapper.planDtoToPlan(planDto);
        Plan updatedplan = planRepository.save(plan);
        return planMapper.planToPlanDto(updatedplan);
    }

    @Override
    @Transactional
    public void delete(PlanDto planDto) {
        log.info("Delete plan");
        planRepository.delete(planMapper.planDtoToPlan(planDto));
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        log.info("Delete plan by id id={}", id);
        planRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void deleteAll() {
        log.info("Delete all plans");
        planRepository.deleteAll();
    }
}