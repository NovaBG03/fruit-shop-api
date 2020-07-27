package com.example.fruitshop.services;

import com.example.fruitshop.api.v1.model.CustomerDTO;

import java.util.List;

public interface CustomerService {

    List<CustomerDTO> getAllCustomers();

    CustomerDTO getCustomer(Long id);
}
