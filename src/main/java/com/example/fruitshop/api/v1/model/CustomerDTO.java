package com.example.fruitshop.api.v1.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CustomerDTO {
    private String firstname;
    private String lastname;
    private String customer_url;
}
