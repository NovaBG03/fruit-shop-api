package com.example.fruitshop.services;

import com.example.fruitshop.api.v1.mappers.CustomerMapper;
import com.example.fruitshop.api.v1.model.CustomerDTO;
import com.example.fruitshop.domain.Customer;
import com.example.fruitshop.repositories.CustomerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CustomerServiceImplTest {

    @Mock
    CustomerRepository customerRepository;

    CustomerService customerService;

    @BeforeEach
    void setUp() {
        customerService = new CustomerServiceImpl(customerRepository, CustomerMapper.INSTANCE);
    }

    @Test
    void getAllCustomersTest() {
        Customer customer1 = new Customer();
        customer1.setFirstName("David");
        customer1.setLastName("Winter");

        Customer customer2 = new Customer();
        customer2.setFirstName("Anne");
        customer2.setLastName("Hine");

        List<Customer> customers = Arrays.asList(customer1, customer2);

        when(customerRepository.findAll()).thenReturn(customers);

        List<CustomerDTO> customerDTOs = customerService.getAllCustomers();

        assertEquals(customers.size(), customerDTOs.size());
    }

    @Test
    void getCustomerTest() {
        final Long id = 1L;
        final String firstName = "David";
        final String lastName = "Winter";

        Customer customer = new Customer();
        customer.setId(id);
        customer.setFirstName(firstName);
        customer.setLastName(lastName);

        when(customerRepository.findById(id)).thenReturn(Optional.of(customer));

        CustomerDTO customerDTO = customerService.getCustomer(id);

        assertNotNull(customerDTO);
        assertEquals(firstName, customerDTO.getFirstname());
        assertEquals(lastName, customerDTO.getLastname());
        assertEquals("/customers/" + id, customerDTO.getCustomer_url());
    }

    @Test
    void createNewCustomerTest() {
        final Long id = 1L;
        final String firstName = "David";
        final String lastName = "Winter";

        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setFirstname(firstName);
        customerDTO.setLastname(lastName);

        Customer savedCustomer = new Customer();
        savedCustomer.setId(id);
        savedCustomer.setFirstName(firstName);
        savedCustomer.setLastName(lastName);

        when(customerRepository.save(any(Customer.class))).thenReturn(savedCustomer);

        CustomerDTO newCustomerDTO = customerService.createNewCustomer(customerDTO);

        assertNotNull(newCustomerDTO);
        assertNotNull(newCustomerDTO.getCustomer_url());
        assertEquals(firstName, newCustomerDTO.getFirstname());
        assertEquals(lastName, newCustomerDTO.getLastname());
    }

    @Test
    void saveCustomerDTOTest() {
        final Long id = 1L;
        final String firstName = "David";
        final String lastName = "Winter";

        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setFirstname(firstName);
        customerDTO.setLastname(lastName);

        Customer savedCustomer = new Customer();
        savedCustomer.setId(id);
        savedCustomer.setFirstName(firstName);
        savedCustomer.setLastName(lastName);

        when(customerRepository.save(any(Customer.class))).thenReturn(savedCustomer);

        CustomerDTO newCustomerDTO = customerService.saveCustomerDTO(id, customerDTO);

        assertNotNull(newCustomerDTO);
        assertNotNull(newCustomerDTO.getCustomer_url());
        assertEquals(firstName, newCustomerDTO.getFirstname());
        assertEquals(lastName, newCustomerDTO.getLastname());
    }

    @Test
    void deleteByIdTest() {
        final Long id = 1L;

        customerService.deleteCustomerById(id);

        ArgumentCaptor<Long> idCapture = ArgumentCaptor.forClass(Long.class);
        verify(customerRepository, times(1)).deleteById(idCapture.capture());

        assertEquals(id, idCapture.getValue());
    }
}