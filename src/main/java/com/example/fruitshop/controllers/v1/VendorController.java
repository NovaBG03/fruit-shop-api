package com.example.fruitshop.controllers.v1;

import com.example.fruitshop.api.v1.model.CustomerDTO;
import com.example.fruitshop.api.v1.model.VendorDTO;
import com.example.fruitshop.api.v1.model.VendorsListDTO;
import com.example.fruitshop.domain.Vendor;
import com.example.fruitshop.services.VendorService;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(VendorController.BASE_URL)
public class VendorController {

    public static final String BASE_URL = "/api/v1/vendors";

    private final VendorService vendorService;

    public VendorController(VendorService vendorService) {
        this.vendorService = vendorService;
    }

    @ApiOperation(value = "Get all Vendors", notes = "Returns a collection of all Vendors")
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public VendorsListDTO getAllVendors() {
        return new VendorsListDTO(vendorService.getAllVendors());
    }

    @ApiOperation(value = "Get Vendor", notes = "Returns a Vendor with a specific id")
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public VendorDTO getVendor(@PathVariable Long id) {
        return vendorService.getVendor(id);
    }

    @ApiOperation(value = "Create new Vendor", notes = "Creates new Vendor")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public VendorDTO createNewVendor(@RequestBody VendorDTO vendorDTO) {
        return vendorService.createNewVendorDTO(vendorDTO);
    }

    @ApiOperation(value = "Save Vendor", notes = "If provided Vendor exists - updates it, if not - creates new.")
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public VendorDTO saveVendor(@RequestBody VendorDTO vendorDTO, @PathVariable Long id) {
        return vendorService.saveVendorDTO(id, vendorDTO);
    }

    @ApiOperation(value = "Update Vendor", notes = "Updates provided fields of a specific Vendor")
    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public VendorDTO patchVendor(@RequestBody VendorDTO vendorDTO, @PathVariable Long id) {
        return vendorService.patchVendorDTO(id, vendorDTO);
    }

    @ApiOperation(value = "Delete Vendor", notes = "Deletes a specific Vendor if it exists")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteVendor(@PathVariable Long id) {
        vendorService.deleteVendor(id);
    }
}

