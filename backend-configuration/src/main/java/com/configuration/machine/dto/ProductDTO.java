package com.configuration.machine.dto;

import com.configuration.machine.enums.ProductType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductDTO {

    private Long id;
    private String name;
    private Integer price;
    private Integer weight;
    private ProductType productType;
    private Long ownerId;
}
