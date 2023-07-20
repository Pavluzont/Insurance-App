package com.exadel.mapper;

import com.exadel.dto.InsuranceCompanyDto;
import com.exadel.entity.InsuranceCompany;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface InsuranceCompanyMapper {
    InsuranceCompanyMapper INSTANCE = Mappers.getMapper(InsuranceCompanyMapper.class);

    InsuranceCompanyDto insuranceCompanyToInsuranceCompanyDto(InsuranceCompany InsuranceCompany);

    InsuranceCompany insuranceCompanyDtoToInsuranceCompany(InsuranceCompanyDto InsuranceCompanyDto);
}
