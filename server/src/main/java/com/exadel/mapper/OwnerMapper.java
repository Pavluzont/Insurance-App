package com.exadel.mapper;

import com.exadel.dto.OwnerDto;
import com.exadel.entity.Owner;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface OwnerMapper {
    OwnerMapper INSTANCE = Mappers.getMapper(OwnerMapper.class);

    OwnerDto ownerToOwnerDto(Owner Owner);

    Owner ownerDtoToOwner(OwnerDto OwnerDto);
}