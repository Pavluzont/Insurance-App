package com.exadel.services.impl;

import com.exadel.dto.InsuranceCompanyDto;
import com.exadel.entity.InsuranceCompany;
import com.exadel.exception.NotFoundException;
import com.exadel.mapper.InsuranceCompanyMapper;
import com.exadel.repository.InsuranceCompanyRepository;
import com.exadel.services.InsuranceCompanyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class InsuranceCompanyServiceImpl implements InsuranceCompanyService {
    private final InsuranceCompanyRepository insuranceCompanyRepository;

    private final InsuranceCompanyMapper insuranceCompanyMapper;

    public void validate(Long insuranceCompanyId) {
        InsuranceCompanyDto insuranceCompany = findById(insuranceCompanyId);
        if (Objects.isNull(insuranceCompany)) {
            throw new NotFoundException("insuranceCompany is not found with id: " + insuranceCompanyId);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<InsuranceCompanyDto> findAll() {
        log.info("Find all insuranceCompanys");
        return insuranceCompanyRepository.findAll().stream()
                .map(insuranceCompanyMapper::insuranceCompanyToInsuranceCompanyDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public InsuranceCompanyDto findById(Long id) {
        log.info("Get insuranceCompany by id={}", id);
        InsuranceCompany insuranceCompany = insuranceCompanyRepository.findById(id).orElse(null);
        return insuranceCompanyMapper.insuranceCompanyToInsuranceCompanyDto(insuranceCompany);
    }

    @Override
    @Transactional
    public InsuranceCompanyDto save(InsuranceCompanyDto insuranceCompanyDto) {
        log.info("Save insuranceCompany");
        InsuranceCompany insuranceCompany = insuranceCompanyMapper.insuranceCompanyDtoToInsuranceCompany(insuranceCompanyDto);
        InsuranceCompany updatedinsuranceCompany = insuranceCompanyRepository.save(insuranceCompany);
        return insuranceCompanyMapper.insuranceCompanyToInsuranceCompanyDto(updatedinsuranceCompany);
    }

    @Override
    @Transactional
    public void delete(InsuranceCompanyDto insuranceCompanyDto) {
        log.info("Delete insuranceCompany");
        insuranceCompanyRepository.delete(insuranceCompanyMapper.insuranceCompanyDtoToInsuranceCompany(insuranceCompanyDto));
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        log.info("Delete insuranceCompany by id id={}", id);
        insuranceCompanyRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void deleteAll() {
        log.info("Delete all insuranceCompanys");
        insuranceCompanyRepository.deleteAll();
    }
}
