package com.exadel.services.impl;

import com.exadel.dto.ClaimDto;
import com.exadel.entity.Claim;
import com.exadel.exception.NotFoundException;
import com.exadel.mapper.ClaimMapper;
import com.exadel.repository.ClaimRepository;
import com.exadel.services.ClaimService;
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
public class ClaimServiceImpl implements ClaimService {
    private final ClaimRepository claimRepository;

    private final ClaimMapper claimMapper;

    public void validate(Long claimId) {
        ClaimDto claim = findById(claimId);
        if (Objects.isNull(claim)) {
            throw new NotFoundException("claim is not found with id: " + claimId);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<ClaimDto> findAll() {
        log.info("Find all claims");
        return claimRepository.findAll().stream()
                .map(claimMapper::claimToClaimDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public ClaimDto findById(Long id) {
        log.info("Get claim by id={}", id);
        Claim claim = claimRepository.findById(id).orElse(null);
        return claimMapper.claimToClaimDto(claim);
    }

    @Override
    @Transactional
    public ClaimDto save(ClaimDto claimDto) {
        log.info("Save claim");
        Claim claim = claimMapper.claimDtoToClaim(claimDto);
        Claim updatedClaim = claimRepository.save(claim);
        return claimMapper.claimToClaimDto(updatedClaim);
    }

    @Override
    @Transactional
    public void delete(ClaimDto claimDto) {
        log.info("Delete claim");
        claimRepository.delete(claimMapper.claimDtoToClaim(claimDto));
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        log.info("Delete claim by id id={}", id);
        claimRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void deleteAll() {
        log.info("Delete all claims");
        claimRepository.deleteAll();
    }
}
