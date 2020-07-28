package com.example.fruitshop.services;

import com.example.fruitshop.api.v1.model.CustomerDTO;

import java.util.List;

public interface CustomerService {

    List<CustomerDTO> getAllCustomers();

    CustomerDTO getCustomer(Long id);

    CustomerDTO createNewCustomer(CustomerDTO customerDTO);

    CustomerDTO saveCustomerDTO(Long id, CustomerDTO customerDTO);

    CustomerDTO patchCustomerDTO(Long id, CustomerDTO customerDTO);

    void deleteCustomerById(Long id);
}
