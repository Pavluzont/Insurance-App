package com.exadel.services;

import com.exadel.dto.PhotoDto;

public interface PhotoService extends CrudService<PhotoDto, Long> {
    void validate(Long photoId);
}
