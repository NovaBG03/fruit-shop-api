package com.example.fruitshop.controllers.v1;

import com.example.fruitshop.api.v1.model.CustomerDTO;
import com.example.fruitshop.domain.Customer;
import com.example.fruitshop.services.CustomerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static com.example.fruitshop.controllers.v1.AbstractRestControllerTest.asJsonString;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
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

    @Test
    void createNewCustomerTest() throws Exception {
        final String firstName = "David";
        final String lastName = "Winter";
        final String customerUrl = "/customers/1";

        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setFirstname(firstName);
        customerDTO.setLastname(lastName);

        CustomerDTO savedCustomerDTO = new CustomerDTO();
        savedCustomerDTO.setCustomer_url(customerUrl);
        savedCustomerDTO.setFirstname(firstName);
        savedCustomerDTO.setLastname(lastName);

        when(customerService.createNewCustomer(any(CustomerDTO.class))).thenReturn(savedCustomerDTO);

        mvc.perform(post("/api/v1/customers")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(customerDTO)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.firstname", equalTo(firstName)))
                .andExpect(jsonPath("$.lastname", equalTo(lastName)))
                .andExpect(jsonPath("$.customer_url", equalTo(customerUrl)));
    }

    @Test
    void saveCustomerTest() throws Exception {
        final String firstName = "David";
        final String lastName = "Winter";
        final Long id = 1L;
        final String customerUrl = "/customers/" + id;

        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setFirstname(firstName);
        customerDTO.setLastname(lastName);

        CustomerDTO savedCustomerDTO = new CustomerDTO();
        savedCustomerDTO.setCustomer_url(customerUrl);
        savedCustomerDTO.setFirstname(firstName);
        savedCustomerDTO.setLastname(lastName);

        when(customerService.saveCustomerDTO(anyLong() ,any(CustomerDTO.class))).thenReturn(savedCustomerDTO);

        mvc.perform(put("/api/v1/customers/" + id)
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(customerDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstname", equalTo(firstName)))
                .andExpect(jsonPath("$.lastname", equalTo(lastName)))
                .andExpect(jsonPath("$.customer_url", equalTo(customerUrl)));
    }

    @Test
    void patchCustomerTest() throws Exception {
        final String firstName = "David";
        final String lastName = "Winter";
        final Long id = 1L;
        final String customerUrl = "/customers/" + id;

        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setFirstname(firstName);
        customerDTO.setLastname(lastName);

        CustomerDTO savedCustomerDTO = new CustomerDTO();
        savedCustomerDTO.setCustomer_url(customerUrl);
        savedCustomerDTO.setFirstname(firstName);
        savedCustomerDTO.setLastname(lastName);

        when(customerService.patchCustomerDTO(anyLong() ,any(CustomerDTO.class))).thenReturn(savedCustomerDTO);

        mvc.perform(patch("/api/v1/customers/" + id)
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(customerDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstname", equalTo(firstName)))
                .andExpect(jsonPath("$.lastname", equalTo(lastName)))
                .andExpect(jsonPath("$.customer_url", equalTo(customerUrl)));
    }

    @Test
    void deleteCustomerTest() throws Exception {
        final Long id = 1L;

        mvc.perform(delete("/api/v1/customers/" + id)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(customerService, times(1)).deleteCustomerById(id);
    }
}