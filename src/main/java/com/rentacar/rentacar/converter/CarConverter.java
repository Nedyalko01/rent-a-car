package com.rentacar.rentacar.converter;

import com.rentacar.rentacar.dto.CarRequest;
import com.rentacar.rentacar.entity.Car;
import org.springframework.stereotype.Component;

@Component
public class CarConverter {

    public Car map (CarRequest carRequest) {
        return Car.builder()
                .brand(carRequest.getBrand())
                .year(carRequest.getYear())
                .build();
    }
}
