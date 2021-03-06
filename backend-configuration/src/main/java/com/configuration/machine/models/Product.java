package com.configuration.machine.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@NoArgsConstructor
@Setter
@Getter
@Entity(name = "PRODUCTS")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "PRICE")
    private Integer price;

    @Column(name = "WEIGHT")
    private Integer weight;

    @Column(name = "PRODUCT_TYPE")
    private String productType;

    @ManyToOne
    @JoinColumn(name = "OWNER_ID")
    private Owner owner;

}
