package com.configuration.machine.converters;

import com.configuration.machine.dto.LocationDTO;
import com.configuration.machine.dto.MachineLocationDTO;
import com.configuration.machine.models.Location;
import com.configuration.machine.models.Machine;
import lombok.extern.log4j.Log4j2;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Log4j2
public class ConverterDTO {

    public MachineLocationDTO convertMachineToMachineLocationDTO(Machine machine){
        log.info("machine convert to DTO machine list started");
        if(machine == null){
            log.trace("Machine is null -> not converted to MachineLocationDTO");
            return null;
        }
        log.trace("machine ID: " + machine.getId() + " converted to MachineLocationDTO" );
        return convertMachineToMachineLocDTO(machine);
    }

    public List<MachineLocationDTO> convertMachineToMachineLocationDTO(List<Machine> machines){
        log.info("list machine convert to DTO machine list started");
        if(machines == null || machines.size() == 0){
            log.trace("Machine list is null or empty.");
        }

        return machines
                .stream()
                .map(this::convertMachineToMachineLocDTO)
                .collect(Collectors.toList());
    }

    private MachineLocationDTO convertMachineToMachineLocDTO(Machine machine){
        log.trace("machine convertion to machineDTO, machineId: " + machine.getId());
        MachineLocationDTO machineLocationDTO = new MachineLocationDTO();

        machineLocationDTO.setName(machine.getName());
        machineLocationDTO.setMachineType(machine.getMachineType());
        machineLocationDTO.setLocations(machine.getLocation());

        log.trace("Machine converted to MachineLocationDTO, machineId: ", machine.getId());

        return machineLocationDTO;
    }

    public List<LocationDTO> convertListLocationToListLocationDTO(List<Location> locations){
        log.trace("list location convertion to list locationDTO");

        List<LocationDTO> locationDTOs = new ArrayList<>();
        for(Location location: locations){
            locationDTOs.add(this.convertLocationToLocationDTO(location));
        }

        return locationDTOs;
    }

    public LocationDTO convertLocationToLocationDTO(Location location){
        log.trace("location convertion to locationDTO, locationId: " + location.getId());
        LocationDTO locationDTO = new LocationDTO();

        locationDTO.setCity(location.getCity());
        locationDTO.setDescription(location.getDescription());
        locationDTO.setStreet(location.getStreet());
        locationDTO.setStreetNumber(location.getStreetNumber());
        locationDTO.setId(location.getId());
        locationDTO.setOwnerId(location.getOwner().getId());

        log.trace("Location converted to LocationDTO, locationId: ", location.getId());

        return locationDTO;
    }

    public Location convertLocationDTOToLocation(LocationDTO locationDTO){
        log.trace("locationDTO convertion to location");
        Location location = new Location();

        location.setCity(locationDTO.getCity());
        location.setDescription(locationDTO.getDescription());
        location.setStreet(locationDTO.getStreet());
        location.setStreetNumber(locationDTO.getStreetNumber());
        locationDTO.setId(null);

        return location;
    }
}
