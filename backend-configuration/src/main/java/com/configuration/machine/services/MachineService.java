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
        log.trace("load all machines from db");
        return converterDTO.convertMachineToMachineLocationDTO(machineRepository.findAll());
    }

    public List<Machine> getAllMachinesWithLocations() {
        log.trace("load all machines from db with locations");
        return machineRepository.findAll();
    }

    public MachineLocationDTO getMachineById(Long id) {
        log.trace("load machine by id");
        Machine machine = machineRepository.findById(id).get();
        log.trace("Machine founded, id: " + id);
        return converterDTO.convertMachineToMachineLocationDTO(machine);
    }

    public List<MachineLocationDTO> getAllOwnerMachinesByOwnerId(Long id) {
        log.trace("load all owner machines from db");
        List<Machine> machines = machineRepository.getAllOwnerMachinesByOwnerId(id);
        log.trace("Owner machine founded, id: " + id + " number of machines: " + machines.size());
        return converterDTO.convertMachineToMachineLocationDTO(machines);
    }

    public Machine createMachine(Machine machine){
        log.trace("save new machine to db");
        return machineRepository.save(machine);
    }

    public Boolean deleteMachineById(Long id){
        log.trace("delete machine from db");
        machineRepository.deleteById(id);
        log.debug("Machine deleted id: " + id);
        return true;
    }

}
