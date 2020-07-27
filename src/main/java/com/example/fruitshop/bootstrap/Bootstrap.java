package com.example.fruitshop.bootstrap;

import com.example.fruitshop.domain.Category;
import com.example.fruitshop.domain.Customer;
import com.example.fruitshop.repositories.CategoryRepository;
import com.example.fruitshop.repositories.CustomerRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class Bootstrap implements CommandLineRunner {

    private final CategoryRepository categoryRepository;
    private final CustomerRepository customerRepository;

    public Bootstrap(CategoryRepository categoryRepository, CustomerRepository customerRepository) {
        this.categoryRepository = categoryRepository;
        this.customerRepository = customerRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        loadCategories();
        loadCustomers();
    }

    private void loadCategories() {
        Category fruits = new Category();
        fruits.setName("Fruits");
        categoryRepository.save(fruits);

        Category dried = new Category();
        dried.setName("Dried");
        categoryRepository.save(dried);

        Category fresh = new Category();
        fresh.setName("Fresh");
        categoryRepository.save(fresh);

        Category exotic = new Category();
        exotic.setName("Exotic");
        categoryRepository.save(exotic);

        Category nuts = new Category();
        nuts.setName("Nuts");
        categoryRepository.save(nuts);

        log.info("Loaded Categories: " + categoryRepository.count());
    }

    private void loadCustomers() {

        Customer michael = new Customer();
        michael.setFirstName("Michael");
        michael.setLastName("Lachappele");
        customerRepository.save(michael);

        Customer ivan = new Customer();
        ivan.setFirstName("Ivan");
        ivan.setLastName("Georgiev");
        customerRepository.save(ivan);

        Customer david = new Customer();
        david.setFirstName("David");
        david.setLastName("Winter");
        customerRepository.save(david);

        Customer anne = new Customer();
        anne.setFirstName("Anne");
        anne.setLastName("Hine");
        customerRepository.save(anne);

        Customer joe = new Customer();
        joe.setFirstName("Joe");
        joe.setLastName("Buck");
        customerRepository.save(joe);

        log.info("Loaded Customers: " + customerRepository.count());
    }
}
