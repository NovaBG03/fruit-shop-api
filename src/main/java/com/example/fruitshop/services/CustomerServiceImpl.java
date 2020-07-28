package com.example.fruitshop.services;

import com.example.fruitshop.api.v1.mappers.CustomerMapper;
import com.example.fruitshop.api.v1.model.CustomerDTO;
import com.example.fruitshop.domain.Customer;
import com.example.fruitshop.exceptions.ResourceNotFoundException;
import com.example.fruitshop.repositories.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;

    public CustomerServiceImpl(CustomerRepository customerRepository, CustomerMapper customerMapper) {
        this.customerRepository = customerRepository;
        this.customerMapper = customerMapper;
    }

    @Override
    public List<CustomerDTO> getAllCustomers() {
        return customerRepository.findAll()
                .stream()
                .map(customerMapper::customerToCustomerDTO)
                .collect(Collectors.toList());
    }

    @Override
    public CustomerDTO getCustomer(Long id) {
        return customerRepository.findById(id)
                .map(customerMapper::customerToCustomerDTO)
                .orElseThrow(ResourceNotFoundException::new);
    }

    @Override
    public CustomerDTO createNewCustomer(CustomerDTO customerDTO) {
        Customer customer = customerMapper.CustomerDTOToCustomer(customerDTO);
        customer.setId(null);

        return saveCustomer(customer);
    }

    @Override
    public CustomerDTO saveCustomerDTO(Long id, CustomerDTO customerDTO) {
        Customer customer = customerMapper.CustomerDTOToCustomer(customerDTO);
        customer.setId(id);

        return saveCustomer(customer);
    }

    @Override
    public CustomerDTO patchCustomerDTO(Long id, CustomerDTO customerDTO) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(RuntimeException::new);

        if (customerDTO.getFirstname() != null) {
            customer.setFirstName(customerDTO.getFirstname());
        }

        if (customerDTO.getLastname() != null) {
            customer.setLastName(customerDTO.getLastname());
        }

        return saveCustomer(customer);
    }

    private CustomerDTO saveCustomer(Customer customer) {
        Customer savedCustomer = customerRepository.save(customer);
        return customerMapper.customerToCustomerDTO(savedCustomer);
    }

    @Override
    public void deleteCustomerById(Long id) {
        customerRepository.deleteById(id);
    }
}
