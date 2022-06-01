package com.rentacar.rentacar.controller;


import com.rentacar.rentacar.converter.CustomerConverter;
import com.rentacar.rentacar.dto.CustomerRequest;
import com.rentacar.rentacar.dto.CustomerResponse;
import com.rentacar.rentacar.entity.Customer;
import com.rentacar.rentacar.service.CustomerService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping(value = "customers")
public class CustomerController {

    private final CustomerService customerService;
    private final CustomerConverter customerConverter;

    @PostMapping
    public ResponseEntity<CustomerResponse> save (@RequestBody final CustomerRequest customerRequest) {
        final Customer customer = customerConverter.map(customerRequest);
        final Customer saved = customerService.save(customer);

        return null;
    }

    @GetMapping("/{id}")
    public ResponseEntity<HttpStatus> findById(@PathVariable Long id) {
        Customer FromDBbyId = customerService.findById(id);
        return ResponseEntity.ok(HttpStatus.OK);

    }


}
