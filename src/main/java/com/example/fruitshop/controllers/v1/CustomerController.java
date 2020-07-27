package com.example.fruitshop.controllers.v1;

import com.example.fruitshop.api.v1.model.CustomerDTO;
import com.example.fruitshop.api.v1.model.CustomerListDTO;
import com.example.fruitshop.services.CustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/api/v1/customers")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping()
    public ResponseEntity<CustomerListDTO> getAllCustomers() {
        return new ResponseEntity<>(new CustomerListDTO(customerService.getAllCustomers()), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerDTO> getCustomerById(@PathVariable Long id) {
        return new ResponseEntity<>(customerService.getCustomer(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<CustomerDTO> createNewCustomer(@RequestBody CustomerDTO customerDTO) {
        return new ResponseEntity<>(customerService.createNewCustomer(customerDTO), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CustomerDTO> saveCustomer(@RequestBody CustomerDTO customerDTO, @PathVariable Long id) {
        return new ResponseEntity<>(customerService.saveCustomerDTO(id, customerDTO), HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<CustomerDTO> patchCustomer(@RequestBody CustomerDTO customerDTO, @PathVariable Long id) {
        return new ResponseEntity<>(customerService.patchCustomerDTO(id, customerDTO), HttpStatus.OK);
    }
}