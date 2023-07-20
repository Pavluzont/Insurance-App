package com.exadel.services;

import com.exadel.dto.InsuranceCompanyDto;

public interface InsuranceCompanyService extends CrudService<InsuranceCompanyDto, Long> {
    void validate(Long companyId);
}
