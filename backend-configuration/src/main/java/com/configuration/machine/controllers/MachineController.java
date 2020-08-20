package com.configuration.machine.controllers;

import com.configuration.machine.dto.MachineLocationDTO;
import com.configuration.machine.models.Machine;
import com.configuration.machine.services.MachineService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/machines")
@Log4j2
public class MachineController {

    @Autowired
    MachineService machineService;

    @GetMapping("/all")
    public List<MachineLocationDTO> getAllMachines(){
        return machineService.getAllMachines();
    }

    @GetMapping("/locations/all")
    public List<Machine> getAllMachinesWithLocations(){
        return machineService.getAllMachinesWithLocations();
    }

    @GetMapping("/{id}")
    public ResponseEntity getMachineById(@PathVariable("id") Long id){
        return ResponseEntity.ok(machineService.getMachineById(id));
    }

    @PostMapping
    public ResponseEntity createMachine(@RequestBody Machine machine){
        machineService.createMachine(machine);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteMachineById(@PathVariable Long id){
        machineService.deleteMachineById(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @GetMapping("/owner/{id}")
    public ResponseEntity getAllMachinesByOwnerId(@PathVariable("id") Long id){
        List<MachineLocationDTO> machineLocationDTOs = machineService.getAllOwnerMachinesByOwnerId(id);
        return ResponseEntity.ok(machineLocationDTOs);
    }

}
