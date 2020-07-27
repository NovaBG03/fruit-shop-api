package com.example.fruitshop.repositories;

import com.example.fruitshop.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
