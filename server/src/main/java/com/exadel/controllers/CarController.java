package com.exadel.controllers;

import com.exadel.dto.CarDto;
import com.exadel.services.CarService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Slf4j
@RequiredArgsConstructor
@CrossOrigin
@RestController
@RequestMapping("/cars")
@Tag(name = "Cars")
public class CarController {
    private final CarService carService;

    @GetMapping
    public List<CarDto> findAll() {
        log.info("Find all cars");
        return carService.findAll();
    }

    @GetMapping("/{ownerId}/cars")
    public List<CarDto> findAllByOwnerId(@PathVariable Long ownerId) {
        log.info("Find all cars for owner");
        return carService.findAllByOwnerId(ownerId);
    }

    @GetMapping("/{carId}")
    @Operation(description = "Get endpoint for car", summary = "This is a summary for car endpoint")
    @ApiResponses(value = {
            @ApiResponse(description = "Success", responseCode = "200"),
            @ApiResponse(description = "Car not found", responseCode = "404")
    })
    public CarDto getCar(@PathVariable Long carId) {
        log.info("Get car by id={}", carId);
        carService.validate(carId);
        return carService.findById(carId);
    }

    @PostMapping
    @Operation(description = "Save a new car", summary = "This is a summary for car endpoint")
    @ApiResponses(value = {
            @ApiResponse(description = "Car was successfully added", responseCode = "201"),
    })
    public CarDto addCar(@RequestBody CarDto carDto) {
        log.info("Save car");
        CarDto dbCar = carService.save(carDto);

        return dbCar;
    }

    @PutMapping
    @Operation(description = "Get update for car", summary = "This is a summary for car endpoint")
    @ApiResponses(value = {
            @ApiResponse(description = "Success update", responseCode = "202"),
            @ApiResponse(description = "Car not found", responseCode = "404")
    })
    public CarDto updateCar(@RequestBody CarDto carDto) {
        log.info("Update car");
        return carService.save(carDto);
    }

    private void deleteCar(@RequestBody CarDto carDto) {
        log.info("Delete car");
        carService.delete(carDto);
    }

    @DeleteMapping("/{carId}")
    @Operation(description = "Delete a car", summary = "This is a summary for car endpoint")
    @ApiResponses(value = {
            @ApiResponse(description = "Car was successfully deleted", responseCode = "204"),
            @ApiResponse(description = "Car not found", responseCode = "404")
    })
    public void deleteCarById(@PathVariable Long carId) {
        log.info("Delete car by id id={}", carId);
        carService.validate(carId);
        carService.deleteById(carId);
    }

    @DeleteMapping
    @Operation(description = "Delete all cars", summary = "This is a summary for cars endpoint")
    @ApiResponses(value = {
            @ApiResponse(description = "Cars were successfully deleted", responseCode = "204")
    })
    public void deleteAll() {
        log.info("Delete all cars");
        carService.deleteAll();
    }
}
