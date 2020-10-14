package com.configuration.machine.dto;

import lombok.Data;

@Data
public class MachineProductDTO {

    private Long id;
    private MachineDTO machineDTO;
    private ProductDTO productDTO;
    private Integer numberOfProducts;

}
