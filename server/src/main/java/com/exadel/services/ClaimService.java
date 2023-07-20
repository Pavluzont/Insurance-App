package com.exadel.services;

import com.exadel.dto.ClaimDto;

public interface ClaimService extends CrudService<ClaimDto, Long> {
    void validate(Long claimId);
}
