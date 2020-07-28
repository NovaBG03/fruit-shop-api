package com.example.fruitshop.api.v1.mappers;

import com.example.fruitshop.api.v1.model.CustomerDTO;
import com.example.fruitshop.domain.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CustomerMapper {

    CustomerMapper INSTANCE = Mappers.getMapper(CustomerMapper.class);

    @Mapping(source = "firstName", target = "firstname")
    @Mapping(source = "lastName", target = "lastname")
    @Mapping(expression = "java( com.example.fruitshop.controllers.v1.CustomerController.BASE_URL + \"/\" + customer.getId() )",
            target = "customer_url")
    CustomerDTO customerToCustomerDTO(Customer customer);

    @Mapping(source = "firstname", target = "firstName")
    @Mapping(source = "lastname", target = "lastName")
    Customer CustomerDTOToCustomer(CustomerDTO customerDTO);
}
