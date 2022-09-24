package com.rentacar.rentacar.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import java.time.LocalTime;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "Customers")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;




    @JsonManagedReference
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @ManyToMany
    @JoinTable(  // Owner side
            name = "customer_cars",
            joinColumns = {@JoinColumn(name = "customer_id")},
            inverseJoinColumns = {@JoinColumn(name = "car_id")}
    )

    private Set<Car> cars;


}


