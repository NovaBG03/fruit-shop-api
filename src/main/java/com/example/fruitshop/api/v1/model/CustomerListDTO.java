package com.example.fruitshop.api.v1.model;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CustomerListDTO {

    @JacksonXmlElementWrapper(localName = "customers")
    @JacksonXmlProperty(localName = "customer")
    private List<CustomerDTO> customers;
}
