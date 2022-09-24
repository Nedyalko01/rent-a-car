package com.rentacar.rentacar;

import com.rentacar.rentacar.entity.Car;
import com.rentacar.rentacar.entity.Customer;
import com.rentacar.rentacar.service.CarServiceImpl;
import com.rentacar.rentacar.service.CustomerServiceImpl;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class StarterMain implements CommandLineRunner {

    private final CustomerServiceImpl customerService;
    private final CarServiceImpl carService;


    public StarterMain(CustomerServiceImpl customerService, CarServiceImpl carService) {
        this.customerService = customerService;
        this.carService = carService;
    }

    @Override
    public void run(String... args) throws Exception {

        Car cars = Car
                .builder()
                .id(2L)
                .brand("BMW")
                .year(2005)
                .build();

        Set<Car> carSet = new HashSet<>();
        carSet.add(cars);

        Customer user = new Customer();
        user.setName("Ivan Petrov");
        user.setCars(carSet);

        customerService.save(user);

        Customer customer = new Customer();
        customer.setName("Anni Avramova");
        customer.setCars(carSet);

        customerService.save(customer);

        Customer update = new Customer();
        update.setName("Customer");
        customerService.swap(user.getId(), update);


    }
}


