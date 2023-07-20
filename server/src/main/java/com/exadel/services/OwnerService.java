package com.exadel.services;

import com.exadel.dto.OwnerDto;
import com.exadel.entity.Owner;

public interface OwnerService extends CrudService<OwnerDto, Long> {
    void validate(Long ownerId);
    OwnerDto findByUid(String uid);
}
