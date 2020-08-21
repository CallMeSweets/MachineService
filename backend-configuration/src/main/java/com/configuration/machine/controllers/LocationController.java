package com.configuration.machine.controllers;

import com.configuration.machine.models.Location;
import com.configuration.machine.services.LocationService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/locations")
@Log4j2
public class LocationController {

    @Autowired
    LocationService locationService;

    @GetMapping("/all")
    public ResponseEntity<List<Location>> getAllLocations(){
        return ResponseEntity.ok(locationService.getAllLocations());
    }

    @PostMapping
    public ResponseEntity createLocation(@RequestBody Location location) {
        Location createdLocation = locationService.createLocation(location);
        log.trace("New location created, id: " + createdLocation.getId());
        return ResponseEntity.ok(HttpStatus.OK);
    }

}
