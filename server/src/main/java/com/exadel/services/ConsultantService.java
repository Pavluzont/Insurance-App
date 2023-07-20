package com.exadel.services;

import com.exadel.dto.ConsultantDto;

public interface ConsultantService extends CrudService<ConsultantDto, Long> {
    void validate(Long consultantId);
}
