package com.rentacar.rentacar.service;

import com.rentacar.rentacar.entity.Car;
import com.rentacar.rentacar.entity.Customer;
import com.rentacar.rentacar.repository.CustomerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;


@ExtendWith(MockitoExtension.class)
class CustomerServiceImplTest {

    @Mock
    CustomerRepository customerRepository;

    private CustomerServiceImpl customerService;//1.0 класа който искам да тествам

    @Mock
    private CarService carService;

    @BeforeEach
    public void SetUp() { // 1.2 създаваме си в CustomerServiceImpl - Конструктор да приема CustomerRepository - за Тест
        customerService = new CustomerServiceImpl(customerRepository, carService);//1.1 създава се всеки път един Обект

        //1.4 public CustomerServiceImpl (CustomerRepository customerRepository) { добра практита при Тест
        //          this.customerRepository = customerRepository;
    }

    @Test
    public void findAll() {
        customerService.findAll();
        Mockito.verify(customerRepository, Mockito.times(1)).findAll();
    }

    @Test
    public void verifySave() {
        Customer expectedCustomer = Customer.builder()
                .id(1L)
                .name("Ivan")
                .build();

        Mockito.when(customerRepository.save(any(Customer.class)))
                .thenReturn(Customer.builder()
                        .id(1L)
                        .name("Ivan")
                        .build());//1.1 връща customer който сме създали с Customer.builder() тук

        Customer actualCustomer = customerService.save(expectedCustomer);

        Mockito.verify(customerRepository, Mockito.times(1)).save(expectedCustomer);

        assertEquals(expectedCustomer.getId(), actualCustomer.getId());
        assertEquals(expectedCustomer.getName(), actualCustomer.getName());
    }

    @Test
    public void verifyFindByAllName() {


        Mockito.when(customerRepository.findAllByName(any(String.class)))
                .thenReturn(Collections.singletonList(Customer.builder().build()));//връща само списък с 1 елемент

        List<Customer> actual = customerService.findByName("");

        Mockito.verify(customerRepository, Mockito.times(1)).findAllByName(any(String.class));
        assertEquals(1, actual.size());

    }
}