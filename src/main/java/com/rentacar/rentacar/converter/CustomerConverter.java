package com.rentacar.rentacar.converter;

import com.rentacar.rentacar.dto.CarRequest;
import com.rentacar.rentacar.dto.CustomerRequest;
import com.rentacar.rentacar.entity.Car;
import com.rentacar.rentacar.entity.Customer;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class CustomerConverter {

    private CarConverter carConverter;

    public Customer map(CustomerRequest customerRequest) {

        return Customer.builder()
                .name(customerRequest.getName())
                .cars(customerRequest.getCarRequests()
                        .stream()
                        .map(carRequest -> carConverter.map(carRequest))
                        .collect(Collectors.toSet()))

                .build();
    }
}
