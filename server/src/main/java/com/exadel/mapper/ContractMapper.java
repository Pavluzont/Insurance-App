package com.exadel.mapper;

import com.exadel.dto.ContractDto;
import com.exadel.entity.Contract;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ContractMapper {
    ContractMapper INSTANCE = Mappers.getMapper(ContractMapper.class);

    ContractDto contractToContractDto(Contract Contract);

    Contract contractDtoToContract(ContractDto ContractDto);
}
