package com.rentacar.rentacar.service;

import com.rentacar.rentacar.entity.Customer;

import java.util.List;

public interface CustomerService {

    Customer save (Customer customer);

    Customer findById(Long id);

    List<Customer> findAll();

    List<Customer> findByName(String name);// връща Лист от имена при -> подадено име
}
