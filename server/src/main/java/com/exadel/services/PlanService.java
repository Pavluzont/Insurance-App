package com.exadel.services;

import com.exadel.dto.PlanDto;

public interface PlanService extends CrudService<PlanDto, Long> {
    void validate(Long planId);
}
