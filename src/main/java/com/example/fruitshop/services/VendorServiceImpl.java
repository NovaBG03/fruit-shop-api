package com.example.fruitshop.services;

import com.example.fruitshop.api.v1.mappers.VendorMapper;
import com.example.fruitshop.api.v1.model.VendorDTO;
import com.example.fruitshop.api.v1.model.VendorsListDTO;
import com.example.fruitshop.domain.Vendor;
import com.example.fruitshop.exceptions.ResourceNotFoundException;
import com.example.fruitshop.repositories.VendorRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class VendorServiceImpl implements VendorService {

    private final VendorRepository vendorRepository;
    private final VendorMapper vendorMapper;

    public VendorServiceImpl(VendorRepository vendorRepository, VendorMapper vendorMapper) {
        this.vendorRepository = vendorRepository;
        this.vendorMapper = vendorMapper;
    }


    @Override
    public List<VendorDTO> getAllVendors() {
        return vendorRepository.findAll()
                .stream()
                .map(vendorMapper::vendorToVendorDTO)
                .collect(Collectors.toList());
    }

    @Override
    public VendorDTO getVendor(Long id) {
        return vendorRepository.findById(id)
                .map(vendorMapper::vendorToVendorDTO)
                .orElseThrow(ResourceNotFoundException::new);
    }

    @Override
    public VendorDTO createNewVendorDTO(VendorDTO vendorDTO) {
        Vendor vendor = vendorMapper.vendorDTOToVendor(vendorDTO);
        vendor.setId(null);

        return saveVendor(vendor);
    }

    @Override
    public VendorDTO saveVendorDTO(Long id, VendorDTO vendorDTO) {
        Vendor vendor = vendorMapper.vendorDTOToVendor(vendorDTO);
        vendor.setId(id);

        return saveVendor(vendor);
    }

    @Override
    public VendorDTO patchVendorDTO(Long id, VendorDTO vendorDTO) {
        Vendor vendor = vendorRepository.findById(id)
                .orElseThrow(ResourceNotFoundException::new);

        if (vendorDTO.getName() != null) {
            vendor.setName(vendorDTO.getName());
        }

        return saveVendor(vendor);
    }

    private VendorDTO saveVendor(Vendor vendor) {
        Vendor savedVendor  = vendorRepository.save(vendor);
        return vendorMapper.vendorToVendorDTO(savedVendor);
    }

    @Override
    public void deleteVendor(Long id) {
        vendorRepository.deleteById(id);
    }
}
