package com.example.fruitshop.api.v1.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class VendorDTO {
    private String name;

    @JsonProperty("vendor_url")
    private String url;
}
