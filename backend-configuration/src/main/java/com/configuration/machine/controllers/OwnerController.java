package com.configuration.machine.controllers;

import com.configuration.machine.models.Owner;
import com.configuration.machine.services.OwnerService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/owners")
@Log4j2
public class OwnerController {

    @Autowired
    OwnerService ownerService;

    @GetMapping("/all")
    public List<Owner> getAllOwners(){
        return ownerService.getAllOwners();
    }

    @GetMapping("/{id}")
    public ResponseEntity getOwnerById(@PathVariable("id") Long id){
        return ResponseEntity.ok(ownerService.getOwnerById(id));
    }

    @PostMapping
    public ResponseEntity createOwner(@RequestBody Owner owner) {
        ownerService.createOwner(owner);
        log.trace("New owner created, name: " + owner.getFirstName());
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteOwnerById(@PathVariable Long id){
        ownerService.deleteOwnerById(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }

}
