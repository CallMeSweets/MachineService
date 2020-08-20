package com.configuration.machine.dto;

import com.configuration.machine.enums.MachineType;
import com.configuration.machine.models.Location;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@ToString
@NoArgsConstructor
public class MachineLocationDTO {

    private String name;

    private MachineType machineType;

    private Location locations;

}
