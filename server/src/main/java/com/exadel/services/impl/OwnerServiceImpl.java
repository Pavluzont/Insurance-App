package com.exadel.services.impl;

import com.exadel.dto.OwnerDto;
import com.exadel.entity.Owner;
import com.exadel.exception.NotFoundException;
import com.exadel.mapper.OwnerMapper;
import com.exadel.repository.OwnerRepository;
import com.exadel.services.OwnerService;
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
public class OwnerServiceImpl implements OwnerService {
    private final OwnerRepository ownerRepository;

    private final OwnerMapper ownerMapper;

    public void validate(Long ownerId) {
        OwnerDto owner = findById(ownerId);
        if (Objects.isNull(owner)) {
            throw new NotFoundException("owner is not found with id: " + ownerId);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<OwnerDto> findAll() {
        log.info("Find all owners");
        return ownerRepository.findAll().stream()
                .map(ownerMapper::ownerToOwnerDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public OwnerDto findById(Long id) {
        log.info("Get owner by id={}", id);
        Owner owner = ownerRepository.findById(id).orElse(null);
        return ownerMapper.ownerToOwnerDto(owner);
    }

    @Override
    @Transactional(readOnly = true)
    public OwnerDto findByUid(String uid) {
        log.info("Get owner by uid={}", uid);
        Owner owner = ownerRepository.findByUid(uid);
        return ownerMapper.ownerToOwnerDto(owner);
    }

    @Override
    @Transactional
    public OwnerDto save(OwnerDto ownerDto) {
        log.info("Save owner");
        Owner owner = ownerMapper.ownerDtoToOwner(ownerDto);
        Owner updatedowner = ownerRepository.save(owner);
        return ownerMapper.ownerToOwnerDto(updatedowner);
    }

    @Override
    @Transactional
    public void delete(OwnerDto ownerDto) {
        log.info("Delete owner");
        ownerRepository.delete(ownerMapper.ownerDtoToOwner(ownerDto));
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        log.info("Delete owner by id id={}", id);
        ownerRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void deleteAll() {
        log.info("Delete all owners");
        ownerRepository.deleteAll();
    }
}
