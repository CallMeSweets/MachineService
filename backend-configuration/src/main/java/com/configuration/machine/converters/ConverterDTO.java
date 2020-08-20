package com.configuration.machine.converters;

import com.configuration.machine.dto.MachineLocationDTO;
import com.configuration.machine.models.Machine;
import lombok.extern.log4j.Log4j2;

import java.util.List;
import java.util.stream.Collectors;

@Log4j2
public class ConverterDTO {

    public MachineLocationDTO convertMachineToMachineLocationDTO(Machine machine){
        log.trace("inside convertMachineToMachineLocationDTO method");
        if(machine == null){
            log.trace("Machine is null -> not converted to MachineLocationDTO");
            return null;
        }
        log.trace("machine ID: " + machine.getId() + " converted to MachineLocationDTO" );
        return convertMachineToMachineLocDTO(machine);
    }

    public List<MachineLocationDTO> convertMachineToMachineLocationDTO(List<Machine> machines){
        log.trace("In convertMachineToMachineLocationDTO method");
        if(machines == null || machines.size() == 0){
            log.trace("Machine list is null or empty.");
        }

        return machines
                .stream()
                .map(this::convertMachineToMachineLocDTO)
                .collect(Collectors.toList());
    }

    private MachineLocationDTO convertMachineToMachineLocDTO(Machine machine){
        log.trace("In convertMachineToMachineLocDTO method, machineId: " + machine.getId());
        MachineLocationDTO machineLocationDTO = new MachineLocationDTO();

        machineLocationDTO.setName(machine.getName());
        machineLocationDTO.setMachineType(machine.getMachineType());
        machineLocationDTO.setLocations(machine.getLocation());

        log.trace("Machine converted to MachineLocationDTO, machineId: ", machine.getId());

        return machineLocationDTO;
    }
}
