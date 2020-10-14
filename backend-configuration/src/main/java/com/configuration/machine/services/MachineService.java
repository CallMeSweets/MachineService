package com.configuration.machine.services;

import com.configuration.machine.converters.ConverterDTO;
import com.configuration.machine.dao.LocationRepository;
import com.configuration.machine.dao.MachineRepository;
import com.configuration.machine.dao.OwnerRepository;
import com.configuration.machine.dto.MachineDTO;
import com.configuration.machine.models.Location;
import com.configuration.machine.models.Machine;
import com.configuration.machine.models.Owner;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Log4j2
public class MachineService {

    MachineRepository machineRepository;
    OwnerRepository ownerRepository;
    LocationRepository locationRepository;
    ConverterDTO converterDTO;

    @Autowired
    public MachineService(MachineRepository machineRepository,
                          OwnerRepository ownerRepository,
                          LocationRepository locationRepository,
                          ConverterDTO converterDTO) {
        this.machineRepository = machineRepository;
        this.ownerRepository = ownerRepository;
        this.locationRepository = locationRepository;
        this.converterDTO = converterDTO;
    }


    public List<MachineDTO> getAllOwnerMachinesByOwnerId(Long id) {
        log.trace("load all owner machines from db");
        List<Machine> machines = machineRepository.getAllOwnerMachinesByOwnerId(id);
        log.trace("Owner machine founded, id: " + id + " number of machines: " + machines.size());
        return converterDTO.convertListMachineToListMachineDTO(machines);
    }

    public Machine createMachine(MachineDTO machineDTO){
        Machine machine = converterDTO.convertMachineDTOToMachine(machineDTO, null);

        Owner owner = ownerRepository.findById(machineDTO.getOwnerId()).get();
        machine.setOwner(owner);

        Location location = locationRepository.findById(machineDTO.getLocationDTO().getId()).get();
        machine.setLocation(location);

        log.trace("save new machine to db");
        return machineRepository.save(machine);
    }

    public Boolean deleteLocations(List<MachineDTO> machineDTOs){
        machineDTOs.forEach(machineDTO -> {
            machineRepository.deleteById(machineDTO.getId());
        });

        return true;
    }

    public MachineDTO updateMachine(MachineDTO machineDTO) {
        log.info("update location in db");
        Machine machine = machineRepository.findById(machineDTO.getId()).get();
        Location location = locationRepository.findById(machineDTO.getLocationDTO().getId()).get();
        converterDTO.convertMachineDTOToMachine(machineDTO, machine);
        converterDTO.convertLocationDTOToLocation(machineDTO.getLocationDTO(), location);
        machine.setLocation(location);
        return converterDTO.convertMachineToMachineDTO(machineRepository.save(machine));
    }
}
