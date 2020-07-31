package com.example.fruitshop.api.v1.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CustomerDTO {

    @ApiModelProperty(value = "First Name", required = true)
    private String firstname;

    @ApiModelProperty(value = "Last Name", required = true)
    private String lastname;

    @ApiModelProperty(value = "Customer URL", required = false)
    private String customer_url;
}
