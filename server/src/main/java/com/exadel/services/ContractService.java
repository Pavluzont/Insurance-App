package com.exadel.services;

import com.exadel.dto.ContractDto;

public interface ContractService extends CrudService<ContractDto, Long> {
    void validate(Long contractId);
}
