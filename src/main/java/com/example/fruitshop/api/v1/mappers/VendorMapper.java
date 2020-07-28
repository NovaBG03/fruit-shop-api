package com.example.fruitshop.api.v1.mappers;

import com.example.fruitshop.api.v1.model.VendorDTO;
import com.example.fruitshop.domain.Vendor;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface VendorMapper {

    VendorMapper INSTANCE = Mappers.getMapper(VendorMapper.class);

    @Mapping(expression = "java( com.example.fruitshop.controllers.v1.VendorController.BASE_URL + \"/\" + vendor.getId() )",
            target = "url")
    VendorDTO vendorToVendorDTO(Vendor vendor);

    Vendor vendorDTOToVendor(VendorDTO vendorDTO);
}
