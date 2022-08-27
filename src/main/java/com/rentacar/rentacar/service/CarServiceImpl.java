package com.rentacar.rentacar.service;

import com.rentacar.rentacar.entity.Car;
import com.rentacar.rentacar.repository.CarRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CarServiceImpl implements CarService {

    private final CarRepository carRepository;

    public CarServiceImpl(CarRepository carRepository) {
        this.carRepository = carRepository;

    }

    @Override
    public Car save(Car car) {
        return carRepository.save(car);
    }

    @Override
    public List<Car> findAll() {
        return carRepository.findAll();
    }

    @Override
    public String [] getAllCars() {
        return carRepository.findAll()
                .stream()
                .map(Car::getBrand)
                .toArray(String[]::new);
    }


    @Override
    public Car[] getCarsByBrand(String brand) {
        Car[] cars = carRepository.findAll()
                .stream()
                .filter(car -> car.getBrand().equals(brand))
                .toArray(Car[]::new);

        return cars;
    }
}
