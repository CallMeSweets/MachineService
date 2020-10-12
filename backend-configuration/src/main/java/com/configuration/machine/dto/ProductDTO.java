package com.configuration.machine.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductDTO {

    private Long id;
    private String name;
    private Integer price;
    private Integer weight;
    private String productType;
    private Long ownerId;
}
