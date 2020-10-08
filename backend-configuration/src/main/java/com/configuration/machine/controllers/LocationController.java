package com.configuration.machine.controllers;

import com.configuration.machine.dto.LocationDTO;
import com.configuration.machine.models.Location;
import com.configuration.machine.services.LocationService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/configuration/locations")
@CrossOrigin
@Log4j2
public class LocationController {

    @Autowired
    LocationService locationService;

    @GetMapping("/all")
    public ResponseEntity<List<Location>> getAllLocations(){
        return ResponseEntity.ok(locationService.getAllLocations());
    }

    @PostMapping("/create")
    public ResponseEntity createLocation(@RequestBody LocationDTO locationDTO) {
        LocationDTO createdLocationDTO = locationService.createLocation(locationDTO);
        log.trace("New location created, id: " + createdLocationDTO.getId());
        return ResponseEntity.ok(createdLocationDTO);
    }

    @PatchMapping("/update")
    public ResponseEntity updateLocation(@RequestBody LocationDTO locationDTO) {
        LocationDTO createdLocationDTO = locationService.updateLocation(locationDTO);
        log.trace("New location created, id: " + createdLocationDTO.getId());
        return ResponseEntity.ok(createdLocationDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity getAllUserLocations(@PathVariable Long id) {
        log.info("getAllUserLocations");
        return ResponseEntity.ok(locationService.getAllUserLocationsByUserId(id));
    }

}
