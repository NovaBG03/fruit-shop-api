package com.example.fruitshop.api.v1.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class VendorDTO {

    @ApiModelProperty(value = "Vendor's name", required = true)
    private String name;

    @ApiModelProperty(value = "Vendor's url", required = false)
    @JsonProperty("vendor_url")
    private String url;
}
