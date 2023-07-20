package com.exadel.services.impl;

import com.exadel.dto.ConsultantDto;
import com.exadel.entity.Consultant;
import com.exadel.exception.NotFoundException;
import com.exadel.mapper.ConsultantMapper;
import com.exadel.repository.ConsultantRepository;
import com.exadel.services.ConsultantService;
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
public class ConsultantServiceImpl implements ConsultantService {
    private final ConsultantRepository consultantRepository;

    private final ConsultantMapper consultantMapper;

    public void validate(Long consultantId) {
        ConsultantDto consultant = findById(consultantId);
        if (Objects.isNull(consultant)) {
            throw new NotFoundException("consultant is not found with id: " + consultantId);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<ConsultantDto> findAll() {
        log.info("Find all consultants");
        return consultantRepository.findAll().stream()
                .map(consultantMapper::consultantToConsultantDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public ConsultantDto findById(Long id) {
        log.info("Get consultant by id={}", id);
        Consultant consultant = consultantRepository.findById(id).orElse(null);
        return consultantMapper.consultantToConsultantDto(consultant);
    }

    @Override
    @Transactional
    public ConsultantDto save(ConsultantDto consultantDto) {
        log.info("Save consultant");
        Consultant consultant = consultantMapper.consultantDtoToConsultant(consultantDto);
        Consultant updatedconsultant = consultantRepository.save(consultant);
        return consultantMapper.consultantToConsultantDto(updatedconsultant);
    }

    @Override
    @Transactional
    public void delete(ConsultantDto consultantDto) {
        log.info("Delete consultant");
        consultantRepository.delete(consultantMapper.consultantDtoToConsultant(consultantDto));
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        log.info("Delete consultant by id id={}", id);
        consultantRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void deleteAll() {
        log.info("Delete all consultants");
        consultantRepository.deleteAll();
    }
}
