package com.rentacar.rentacar.service;

import com.rentacar.rentacar.entity.Car;
import com.rentacar.rentacar.entity.Customer;
import com.rentacar.rentacar.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CustomerServiceImpl implements CustomerService {


    private final CustomerRepository customerRepository;
    private final CarService carService;

    public CustomerServiceImpl (final CustomerRepository customerRepository, final CarService carService) {
          this.customerRepository = customerRepository;
          this.carService = carService;
    }


    @Override
    public Customer save(Customer customer) {
        Set<Car> carSet = customer.getCars();
        for (Car car: carSet) {
            carService.save(car);
        }
       return customerRepository.save(customer);
    }   

    @Override
    public Customer findById(Long id) {
        return customerRepository.findById(id)
                .orElseThrow(RuntimeException::new);
    }

    @Override
    public List<Customer> findAll() {
        return customerRepository.findAll();
    }

    @Override
    public List<Customer> findByName(String name) {
        return customerRepository.findAllByName(name);
    }
}
