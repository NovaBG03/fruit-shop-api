package com.example.fruitshop.services;

import com.example.fruitshop.api.v1.model.VendorDTO;
import com.example.fruitshop.api.v1.model.VendorsListDTO;
import com.example.fruitshop.domain.Vendor;

import java.util.List;

public interface VendorService {

    List<VendorDTO> getAllVendors();

    VendorDTO getVendor(Long id);

    VendorDTO createNewVendorDTO(VendorDTO vendorDTO);

    VendorDTO saveVendorDTO(Long id, VendorDTO vendorDTO);

    VendorDTO patchVendorDTO(Long id, VendorDTO vendorDTO);

    void deleteVendor(Long id);
}
