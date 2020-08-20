package com.configuration.machine.controllers;

import com.configuration.machine.models.Location;
import com.configuration.machine.services.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/locations")
public class LocationController {

    @Autowired
    LocationService locationService;

    @GetMapping("/all")
    public ResponseEntity<List<Location>> getAllLocations(){
        return ResponseEntity.ok(locationService.getAllLocations());
    }

}
