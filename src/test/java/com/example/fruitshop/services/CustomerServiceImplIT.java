package com.example.fruitshop.services;

import com.example.fruitshop.api.v1.mappers.CustomerMapper;
import com.example.fruitshop.api.v1.model.CustomerDTO;
import com.example.fruitshop.bootstrap.Bootstrap;
import com.example.fruitshop.domain.Customer;
import com.example.fruitshop.repositories.CategoryRepository;
import com.example.fruitshop.repositories.CustomerRepository;
import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class CustomerServiceImplIT {

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    CustomerRepository customerRepository;

    CustomerService customerService;

    @BeforeEach
    void setUp() throws Exception {
        Bootstrap bootstrap = new Bootstrap(categoryRepository, customerRepository);
        bootstrap.run();

        customerService = new CustomerServiceImpl(customerRepository, CustomerMapper.INSTANCE);
    }

    @Test
    void patchCustomerFirstNameTest() {
        CustomerDTO customerDTO = customerService.getAllCustomers().get(0);
        assertNotNull(customerDTO);

        Long id = Long.valueOf(customerDTO.getCustomer_url().split("/")[2]);
        String initialFirstName = customerDTO.getFirstname();
        String initialLastName = customerDTO.getLastname();

        String newFirstName = "newFirstName";
        customerDTO.setFirstname(newFirstName);

        customerService.patchCustomerDTO(id, customerDTO);

        Customer patchedCustomer = customerRepository.getOne(id);

        assertNotNull(patchedCustomer);
        assertEquals(newFirstName, patchedCustomer.getFirstName());
        assertEquals(initialLastName, patchedCustomer.getLastName());
    }

    @Test
    void patchCustomerLastNameTest() {
        CustomerDTO customerDTO = customerService.getAllCustomers().get(0);
        assertNotNull(customerDTO);

        Long id = Long.valueOf(customerDTO.getCustomer_url().split("/")[2]);
        String initialFirstName = customerDTO.getFirstname();
        String initialLastName = customerDTO.getLastname();

        String newLastName = "newLastName";
        customerDTO.setLastname(newLastName);

        customerService.patchCustomerDTO(id, customerDTO);

        Customer patchedCustomer = customerRepository.getOne(id);

        assertNotNull(patchedCustomer);
        assertEquals(initialFirstName, patchedCustomer.getFirstName());
        assertEquals(newLastName, patchedCustomer.getLastName());
    }
}
