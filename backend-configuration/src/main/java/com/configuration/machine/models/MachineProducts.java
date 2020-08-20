package com.configuration.machine.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@NoArgsConstructor
@Setter
@Getter
@Entity
public class MachineProducts {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "MACHINE_ID")
    private Machine machine;

    @ManyToOne
    @JoinColumn(name = "PRODUCT_ID")
    private Product product;

}
