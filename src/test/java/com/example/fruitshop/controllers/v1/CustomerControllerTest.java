package com.example.fruitshop.controllers.v1;

import com.example.fruitshop.api.v1.model.CustomerDTO;
import com.example.fruitshop.domain.Customer;
import com.example.fruitshop.services.CustomerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class CustomerControllerTest {

    @Mock
    CustomerService customerService;

    @InjectMocks
    CustomerController customerController;

    MockMvc mvc;

    @BeforeEach
    void setUp() {
        mvc = MockMvcBuilders.standaloneSetup(customerController).build();
    }

    @Test
    void getAllCustomersTest() throws Exception {
        CustomerDTO customer1 = new CustomerDTO();
        customer1.setFirstname("David");
        customer1.setLastname("Winter");

        CustomerDTO customer2 = new CustomerDTO();
        customer2.setFirstname("Anne");
        customer2.setLastname("Hine");

        List<CustomerDTO> customers = Arrays.asList(customer1, customer2);

        when(customerService.getAllCustomers()).thenReturn(customers);

        mvc.perform(get("/api/v1/customers")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.customers", hasSize(2)));
    }

    @Test
    void getCustomerByIdTest() throws Exception {
        final Long id = 1L;
        final String customerUrl = "/customers/" + id;
        final String firstName = "David";
        final String lastName = "Winter";

        CustomerDTO customer = new CustomerDTO();
        customer.setCustomer_url(customerUrl);
        customer.setFirstname(firstName);
        customer.setLastname(lastName);

        when(customerService.getCustomer(id)).thenReturn(customer);

        mvc.perform(get("/api/v1/customers" + "/" + id)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstname", equalTo(firstName)))
                .andExpect(jsonPath("$.lastname", equalTo(lastName)))
                .andExpect(jsonPath("$.customer_url", equalTo(customerUrl)));
    }
}