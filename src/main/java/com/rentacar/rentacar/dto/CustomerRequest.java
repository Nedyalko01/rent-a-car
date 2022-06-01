package com.rentacar.rentacar.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerRequest {

    private String name;
    private Set<CarRequest> carRequests;

}
