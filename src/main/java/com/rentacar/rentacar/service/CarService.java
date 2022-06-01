package com.rentacar.rentacar.service;

import com.rentacar.rentacar.entity.Car;

import java.util.List;

public interface CarService {

    Car save (Car car);

    List<Car> findAll();
}
