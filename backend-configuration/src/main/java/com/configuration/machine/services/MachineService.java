package com.configuration.machine.services;

import com.configuration.machine.converters.ConverterDTO;
import com.configuration.machine.dao.MachineRepository;
import com.configuration.machine.dto.MachineLocationDTO;
import com.configuration.machine.models.Machine;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@Log4j2
public class MachineService {

    @Autowired
    MachineRepository machineRepository;

    @Autowired
    ConverterDTO converterDTO;

    public List<MachineLocationDTO> getAllMachines(){
        log.trace("inside getAllMachines method");
        return converterDTO.convertMachineToMachineLocationDTO(machineRepository.findAll());
    }

    public List<Machine> getAllMachinesWithLocations() {
        log.trace("inside getAllMachinesWithLocations method");
        return machineRepository.findAll();
    }

    public MachineLocationDTO getMachineById(Long id) {
        log.trace("inside getMachineById method");
        Machine machine = machineRepository.findById(id).get();
        log.trace("Machine founded, id: " + id);
        return converterDTO.convertMachineToMachineLocationDTO(machine);
    }

    public List<MachineLocationDTO> getAllOwnerMachinesByOwnerId(Long id) {
        log.trace("inside getAllOwnerMachinesByOwnerId method");
        List<Machine> machines = machineRepository.getAllOwnerMachinesByOwnerId(id);
        log.trace("Owner machine founded, id: " + id + " number of machines: " + machines.size());
        return converterDTO.convertMachineToMachineLocationDTO(machines);
    }

    public Machine createMachine(Machine machine){
        log.trace("inside createMachine method");
        return machineRepository.save(machine);
    }

    public Boolean deleteMachineById(Long id){
        log.trace("inside deleteMachineById method");
        machineRepository.deleteById(id);
        log.debug("Machine deleted id: " + id);
        return true;
    }

}
