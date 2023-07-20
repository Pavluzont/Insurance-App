package com.exadel.services;

import com.exadel.dto.CarDto;
import com.exadel.entity.Car;
import com.exadel.mapper.CarMapper;
import com.exadel.repository.CarRepository;
import com.exadel.services.impl.CarServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CarServiceImplTest {
    public static final Long TEST_ID = 1L;

    @Mock
    private CarRepository carRepository;

    @Mock
    private CarMapper carMapper;

    @InjectMocks
    private CarServiceImpl carService;

    @Test
    public void testFindAll() {
        List<Car> carsMock = new ArrayList<>();
        carsMock.add(Car.builder().id(1L).name("Nissan").build());
        carsMock.add(Car.builder().id(2L).name("Toyota").build());
        carsMock.add(Car.builder().id(3L).name("Mazda").build());

        when(carRepository.findAll()).thenReturn(carsMock);

        List<CarDto> cars = carService.findAll();

        assertNotNull(cars);
        assertEquals(3, cars.size());
    }

    @Test
    public void testFindById() {
        Car carMock = Car.builder().id(TEST_ID).build();
        CarDto carDtoMock = CarDto.builder().id(TEST_ID).build();

        when(carRepository.findById(any())).thenReturn(Optional.of(carMock));
        when(carMapper.carToCarDto(any())).thenReturn(carDtoMock);

        CarDto car = carService.findById(TEST_ID);

        assertNotNull(car);
        assertEquals(TEST_ID, car.getId());
    }

    @Test
    public void testFindByIdNotFound() {
        when(carRepository.findById(anyLong())).thenReturn(Optional.empty());

        CarDto car = carService.findById(TEST_ID);

        assertNull(car);
    }

    @Test
    public void testSave() {
        Car carMock = Car.builder().id(TEST_ID).build();
        CarDto carDtoMock = CarDto.builder().id(TEST_ID).build();

        when(carRepository.save(any())).thenReturn(carMock);
        when(carMapper.carDtoToCar(any())).thenReturn(carMock);
        when(carMapper.carToCarDto(any())).thenReturn(carDtoMock);

        CarDto carDto = carService.save(carDtoMock);

        assertNotNull(carDto);
        verify(carRepository, times(1)).save(carMock);
    }

    @Test
    public void testDelete() {
        CarDto carDtoMock = CarDto.builder().build();
        carService.delete(carDtoMock);

        verify(carRepository, times(1)).delete(any());
    }

    @Test
    public void testDeleteById() {
        carService.deleteById(TEST_ID);

        verify(carRepository, times(1)).deleteById(anyLong());
    }
}