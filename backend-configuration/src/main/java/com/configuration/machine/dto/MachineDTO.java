package com.configuration.machine.dto;

import com.configuration.machine.models.Location;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@ToString
@NoArgsConstructor
public class MachineDTO {

    private Long id;
    private String name;
    private Long ownerId;
    private Long locationId;
    private Integer numProductSpace;
    private String machineType;
    private String description;

}
