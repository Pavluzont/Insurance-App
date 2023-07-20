package com.exadel.services.impl;

import com.exadel.dto.PhotoDto;
import com.exadel.entity.Photo;
import com.exadel.exception.NotFoundException;
import com.exadel.mapper.PhotoMapper;
import com.exadel.repository.PhotoRepository;
import com.exadel.services.PhotoService;
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
public class PhotoServiceImpl implements PhotoService {
    private final PhotoRepository photoRepository;

    private final PhotoMapper photoMapper;

    public void validate(Long photoId) {
        PhotoDto photo = findById(photoId);
        if (Objects.isNull(photo)) {
            throw new NotFoundException("photo is not found with id: " + photoId);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<PhotoDto> findAll() {
        log.info("Find all photos");
        return photoRepository.findAll().stream()
                .map(photoMapper::photoToPhotoDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public PhotoDto findById(Long id) {
        log.info("Get photo by id={}", id);
        Photo photo = photoRepository.findById(id).orElse(null);
        return photoMapper.photoToPhotoDto(photo);
    }

    @Override
    @Transactional
    public PhotoDto save(PhotoDto photoDto) {
        log.info("Save photo");
        Photo photo = photoMapper.photoDtoToPhoto(photoDto);
        Photo updatedphoto = photoRepository.save(photo);
        return photoMapper.photoToPhotoDto(updatedphoto);
    }

    @Override
    @Transactional
    public void delete(PhotoDto photoDto) {
        log.info("Delete photo");
        photoRepository.delete(photoMapper.photoDtoToPhoto(photoDto));
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        log.info("Delete photo by id id={}", id);
        photoRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void deleteAll() {
        log.info("Delete all photos");
        photoRepository.deleteAll();
    }
}