package com.exadel.services.impl;

import com.exadel.dto.CarDto;
import com.exadel.entity.Car;
import com.exadel.exception.NotFoundException;
import com.exadel.mapper.CarMapper;
import com.exadel.repository.CarRepository;
import com.exadel.services.CarService;
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
public class CarServiceImpl implements CarService {
    private final CarRepository carRepository;
    private final CarMapper carMapper;

    public void validate(Long carId) {
        CarDto car = findById(carId);
        if (Objects.isNull(car)) {
            throw new NotFoundException("Car is not found with id: " + carId);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<CarDto> findAll() {
        log.info("Find all cars");
        return carRepository.findAll().stream()
                .map(carMapper::carToCarDto)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<CarDto> findAllByOwnerId(Long ownerId) {
        log.info("Find all cars for owner");

        return carRepository.findAllByOwnerId(ownerId).stream()
                .map(carMapper::carToCarDto)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public CarDto findById(Long id) {
        log.info("Get car by id={}", id);
        Car car = carRepository.findById(id).orElse(null);
        return carMapper.carToCarDto(car);
    }

    @Override
    @Transactional
    public CarDto save(CarDto carDto) {
        log.info("Save car");
        Car car = carMapper.carDtoToCar(carDto);
        Car updatedCar = carRepository.save(car);
        return carMapper.carToCarDto(updatedCar);
    }

    @Override
    @Transactional
    public void delete(CarDto carDto) {
        log.info("Delete car");
        carRepository.delete(carMapper.carDtoToCar(carDto));
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        log.info("Delete car by id id={}", id);
        carRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void deleteAll() {
        log.info("Delete all cars");
        carRepository.deleteAll();
    }
}
