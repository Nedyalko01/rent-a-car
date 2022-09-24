package com.rentacar.rentacar.service;

import com.rentacar.rentacar.entity.Car;
import com.rentacar.rentacar.entity.Customer;
import com.rentacar.rentacar.repository.CarRepository;
import com.rentacar.rentacar.repository.CustomerRepository;
import org.assertj.core.internal.bytebuddy.matcher.ElementMatcher;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.core.Is.*;
import static org.hamcrest.MatcherAssert.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class CustomerServiceImplTest {

    @Mock
    CustomerRepository customerRepository;

    private CustomerServiceImpl customerService;

    @Mock
    private CarService carService;

    @BeforeEach
    public void SetUp() {
        customerService = new CustomerServiceImpl(customerRepository, carService);

    }

    @Test
    public void findAll() {
        customerService.findAll();
        Mockito.verify(customerRepository, Mockito.times(1)).findAll();
    }

    @Test
    public void getAllCars_when2Cars_2CarsBrand() {

        CarRepository repository = mock(CarRepository.class);
        when(repository.findAll())
                .thenReturn(List.of(new Car() {{
                                        setId(1L);
                                        setBrand("Audi");
                                    }},
                        new Car() {{
                            setId(2L);
                            setBrand("BMW");
                        }}));


        CarServiceImpl service = new CarServiceImpl(repository);

        String[] carBrand = service.getAllCars();

        assertThat(carBrand.length, is(2));


    }

    @Test
    public void getAllCars_whenNoCars_emptyArray() {

        CarRepository repository = mock(CarRepository.class);
        when(repository.findAll())
                .thenReturn(List.of());

        CarServiceImpl service = new CarServiceImpl(repository);

        String[] carBrand = service.getAllCars();

        assertThat(carBrand.length, is(0));


    }


    @Test
    public void verifySave() {
        Customer expectedCustomer = Customer.builder()
                .id(1L)
                .name("Ivan")
                .build();

        when(customerRepository.save(any(Customer.class)))
                .thenReturn(Customer.builder()
                        .id(1L)
                        .name("Ivan")
                        .build());

        Customer actualCustomer = customerService.save(expectedCustomer);

        Mockito.verify(customerRepository, Mockito.times(1)).save(expectedCustomer);

        assertEquals(expectedCustomer.getId(), actualCustomer.getId());
        assertEquals(expectedCustomer.getName(), actualCustomer.getName());
    }

    @Test
    public void verifyFindByAllName() {


        when(customerRepository.findAllByName(any(String.class)))
                .thenReturn(Collections.singletonList(Customer.builder().build()));

        List<Customer> actual = customerService.findByName("");

        Mockito.verify(customerRepository, Mockito.times(1)).findAllByName(any(String.class));
        assertEquals(1, actual.size());

    }


}