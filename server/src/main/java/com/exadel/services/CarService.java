package com.exadel.services;

import com.exadel.dto.CarDto;

import java.util.List;

public interface CarService extends CrudService<CarDto, Long> {
    void validate(Long carId);
    List<CarDto> findAllByOwnerId(Long ownerId);
}
