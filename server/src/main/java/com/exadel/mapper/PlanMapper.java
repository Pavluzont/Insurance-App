package com.exadel.mapper;

import com.exadel.dto.PlanDto;
import com.exadel.entity.Plan;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PlanMapper {
    PlanMapper INSTANCE = Mappers.getMapper(PlanMapper.class);

    PlanDto planToPlanDto(Plan Plan);

    Plan planDtoToPlan(PlanDto PlanDto);
}
