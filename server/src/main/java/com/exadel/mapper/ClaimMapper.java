package com.exadel.mapper;

import com.exadel.dto.ClaimDto;
import com.exadel.entity.Claim;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ClaimMapper {
    ClaimMapper INSTANCE = Mappers.getMapper(ClaimMapper.class);

    ClaimDto claimToClaimDto(Claim Claim);

    Claim claimDtoToClaim(ClaimDto ClaimDto);
}
