package com.example.fruitshop.api.v1.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class VendorsListDTO {
    @ApiModelProperty(value = "Collection of vendors")
    private List<VendorDTO> vendors;
}
