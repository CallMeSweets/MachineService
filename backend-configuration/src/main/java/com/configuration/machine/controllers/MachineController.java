package com.configuration.machine.controllers;

import com.configuration.machine.converters.ConverterDTO;
import com.configuration.machine.dto.MachineDTO;
import com.configuration.machine.models.Machine;
import com.configuration.machine.services.MachineService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/configuration/machines")
@Log4j2
@CrossOrigin
public class MachineController {

    private MachineService machineService;
    private ConverterDTO converterDTO;

    @Autowired
    public MachineController(MachineService machineService, ConverterDTO converterDTO) {
        this.machineService = machineService;
        this.converterDTO = converterDTO;
    }

    @GetMapping("/{id}")
    public ResponseEntity getAllOwnerMachines(@PathVariable("id") Long id){
        List<MachineDTO> machineDTOS = machineService.getAllOwnerMachinesByOwnerId(id);
        return ResponseEntity.ok(machineDTOS);
    }

    @PostMapping("/create")
    public ResponseEntity createMachine(@RequestBody MachineDTO machineDTO){
        Machine machine = machineService.createMachine(machineDTO);
        return ResponseEntity.ok(converterDTO.convertMachineToMachineDTO(machine));
    }

    @PatchMapping("/update")
    public ResponseEntity getAllMachinesWithLocations(@RequestBody MachineDTO machineDTO){
        MachineDTO updatedMachineDTO = machineService.updateMachine(machineDTO);
        log.trace("location updated, id: " + updatedMachineDTO.getId());
        return ResponseEntity.ok(updatedMachineDTO);
    }

    @PostMapping("/delete")
    public ResponseEntity deleteMachineById(@RequestBody List<MachineDTO> machineDTOs){
        machineService.deleteLocations(machineDTOs);
        log.trace("machines deleted, number: " + machineDTOs.size());
        return ResponseEntity.ok(HttpStatus.OK);
    }

}
