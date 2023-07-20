package com.exadel.services.impl;

import com.exadel.dto.ContractDto;
import com.exadel.entity.Contract;
import com.exadel.exception.NotFoundException;
import com.exadel.mapper.ContractMapper;
import com.exadel.repository.ContractRepository;
import com.exadel.services.ContractService;
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
public class ContractServiceImpl implements ContractService {
    private final ContractRepository contractRepository;

    private final ContractMapper contractMapper;

    public void validate(Long contractId) {
        ContractDto contract = findById(contractId);
        if (Objects.isNull(contract)) {
            throw new NotFoundException("Contract is not found with id: " + contractId);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<ContractDto> findAll() {
        log.info("Find all Contracts");
        return contractRepository.findAll().stream()
                .map(contractMapper::contractToContractDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public ContractDto findById(Long id) {
        log.info("Get Contract by id={}", id);
        Contract contract = contractRepository.findById(id).orElse(null);
        return contractMapper.contractToContractDto(contract);
    }

    @Override
    @Transactional
    public ContractDto save(ContractDto contractDto) {
        log.info("Save Contract");
        Contract contract = contractMapper.contractDtoToContract(contractDto);
        Contract updatedContract = contractRepository.save(contract);
        return contractMapper.contractToContractDto(updatedContract);
    }

    @Override
    @Transactional
    public void delete(ContractDto contractDto) {
        log.info("Delete Contract");
        contractRepository.delete(contractMapper.contractDtoToContract(contractDto));
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        log.info("Delete Contract by id id={}", id);
        contractRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void deleteAll() {
        log.info("Delete all Contracts");
        contractRepository.deleteAll();
    }
}
