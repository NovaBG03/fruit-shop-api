package com.example.fruitshop.services;

import com.example.fruitshop.api.v1.mappers.VendorMapper;
import com.example.fruitshop.api.v1.model.VendorDTO;
import com.example.fruitshop.domain.Vendor;
import com.example.fruitshop.repositories.VendorRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static com.example.fruitshop.controllers.v1.VendorController.BASE_URL;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class VendorServiceImplTest {

    @Mock
    VendorRepository vendorRepository;

    VendorService vendorService;

    private Long id = 1L;
    private String name = "testName";

    @BeforeEach
    void setUp() {
        vendorService = new VendorServiceImpl(vendorRepository, VendorMapper.INSTANCE);
    }

    @Test
    void getAllVendorsTest() {
        Vendor vendor1 = new Vendor();
        vendor1.setId(id);
        vendor1.setName(name);

        final Long id2 = id + 1;
        final String name2 = name + 2;
        Vendor vendor2 = new Vendor();
        vendor2.setId(id2);
        vendor2.setName(name2);

        List<Vendor> returnVendors = Arrays.asList(vendor1, vendor2);
        int vendorsCount = returnVendors.size();

        when(vendorRepository.findAll()).thenReturn(returnVendors);

        List<VendorDTO> vendors = vendorService.getAllVendors();

        assertNotNull(vendors);
        assertEquals(vendorsCount, vendors.size());
    }

    @Test
    void getVendorTest() {
        Vendor vendor = new Vendor();
        vendor.setId(id);
        vendor.setName(name);

        when(vendorRepository.findById(id)).thenReturn(Optional.of(vendor));

        VendorDTO vendorDTO = vendorService.getVendor(id);

        assertNotNull(vendorDTO);
        assertEquals(name, vendorDTO.getName());
        assertEquals(getVendorUrl(id), vendorDTO.getUrl());
    }

    @Test
    void createNewVendorDTOTest() {
        VendorDTO vendorDTO = new VendorDTO();
        vendorDTO.setName(name);

        Vendor savedVendor = new Vendor();
        savedVendor.setId(id);
        savedVendor.setName(name);

        ArgumentCaptor<Vendor> vendorCaptor = ArgumentCaptor.forClass(Vendor.class);
        when(vendorRepository.save(vendorCaptor.capture())).thenReturn(savedVendor);

        VendorDTO newVendorDTO = vendorService.createNewVendorDTO(vendorDTO);

        assertNull(vendorCaptor.getValue().getId());
        assertNotNull(newVendorDTO);
        assertEquals(name, newVendorDTO.getName());
        assertEquals(getVendorUrl(id), newVendorDTO.getUrl());
    }

    @Test
    void saveVendorDTOTest() {
        VendorDTO vendorDTO = new VendorDTO();
        vendorDTO.setName(name);

        Vendor savedVendor = new Vendor();
        savedVendor.setId(id);
        savedVendor.setName(name);

        when(vendorRepository.save(any(Vendor.class))).thenReturn(savedVendor);

        VendorDTO newVendorDTO = vendorService.saveVendorDTO(id, vendorDTO);

        assertNotNull(newVendorDTO);
        assertEquals(name, newVendorDTO.getName());
        assertEquals(getVendorUrl(id), newVendorDTO.getUrl());
    }


    @Test
    void deleteVendorTest() {
        vendorService.deleteVendor(id);

        verify(vendorRepository, times(1)).deleteById(id);
    }

    private String getVendorUrl(Long id) {
        return BASE_URL + "/" + id;
    }
}