package com.configuration.machine.controllers;

import com.configuration.machine.converters.ConverterDTO;
import com.configuration.machine.dto.LocationDTO;
import com.configuration.machine.dto.MachineProductDTO;
import com.configuration.machine.models.MachineProduct;
import com.configuration.machine.services.MachineProductService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/configuration/machine-products")
@CrossOrigin
@Log4j2
public class MachineProductController {

    private MachineProductService machineProductService;

    @Autowired
    public MachineProductController(MachineProductService machineProductService) {
        this.machineProductService = machineProductService;
    }

    @GetMapping("/{id}")
    public ResponseEntity getAllUserMachineProduct(@PathVariable Long id) {
        log.info("getAllUserMachineProduct");
        return ResponseEntity.ok(machineProductService.getAllUserMachineProductByUserId(id));
    }

    @PostMapping("/create")
    public ResponseEntity addProductToMachine(@RequestBody MachineProductDTO machineProductDTO) {
        MachineProductDTO createdMachineProductDTO = machineProductService.createMachineProduct(machineProductDTO);
        log.trace("New machineProduct created, id: " + machineProductDTO.getId());
        return ResponseEntity.ok(machineProductDTO);
    }

    @PatchMapping("/update")
    public ResponseEntity updateProductMachine(@RequestBody MachineProductDTO machineProductDTO) {
        MachineProductDTO updatedMachineProductDTO = machineProductService.updateMachineProduct(machineProductDTO);
        log.trace("machineProduct updated, id: " + updatedMachineProductDTO.getId());
        return ResponseEntity.ok(updatedMachineProductDTO);
    }

    @PostMapping("/delete")
    public ResponseEntity deleteProductMachine(@RequestBody List<MachineProductDTO> machineProductDTOs) {
        machineProductService.deleteProductMachine(machineProductDTOs);
        log.trace("machineProducts deleted, number: " + machineProductDTOs.size());
        return ResponseEntity.ok(HttpStatus.OK);
    }
}
