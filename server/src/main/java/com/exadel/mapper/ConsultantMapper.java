package com.exadel.mapper;

import com.exadel.dto.ConsultantDto;
import com.exadel.entity.Consultant;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ConsultantMapper {
    ConsultantMapper INSTANCE = Mappers.getMapper(ConsultantMapper.class);

    ConsultantDto consultantToConsultantDto(Consultant Consultant);

    Consultant consultantDtoToConsultant(ConsultantDto ConsultantDto);
}
