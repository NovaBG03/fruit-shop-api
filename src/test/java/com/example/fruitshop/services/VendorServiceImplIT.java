package com.example.fruitshop.services;

import com.example.fruitshop.api.v1.mappers.CustomerMapper;
import com.example.fruitshop.api.v1.mappers.VendorMapper;
import com.example.fruitshop.api.v1.model.CustomerDTO;
import com.example.fruitshop.api.v1.model.VendorDTO;
import com.example.fruitshop.bootstrap.Bootstrap;
import com.example.fruitshop.domain.Customer;
import com.example.fruitshop.domain.Vendor;
import com.example.fruitshop.repositories.CategoryRepository;
import com.example.fruitshop.repositories.CustomerRepository;
import com.example.fruitshop.repositories.VendorRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class VendorServiceImplIT {

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    VendorRepository vendorRepository;

    VendorService vendorService;

    @BeforeEach
    void setUp() throws Exception {
        Bootstrap bootstrap = new Bootstrap(categoryRepository, customerRepository, vendorRepository);
        bootstrap.run();

        vendorService = new VendorServiceImpl(vendorRepository, VendorMapper.INSTANCE);
    }

    @Test
    void patchVendorNameTest() {
        VendorDTO vendorDTO = vendorService.getAllVendors().get(0);
        assertNotNull(vendorDTO);

        Long id = Long.valueOf(vendorDTO.getUrl().split("/")[4]);
        String initialName = vendorDTO.getName();

        String newName = "newName";
        vendorDTO.setName(newName);

        vendorService.patchVendorDTO(id, vendorDTO);

        Vendor patchedVendor = vendorRepository.getOne(id);

        assertNotNull(patchedVendor);
        assertEquals(newName, patchedVendor.getName());
        assertEquals(id, patchedVendor.getId());
    }
}
