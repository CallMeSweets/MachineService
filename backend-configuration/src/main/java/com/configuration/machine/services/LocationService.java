package com.configuration.machine.services;

import com.configuration.machine.dao.LocationRepository;
import com.configuration.machine.dao.MachineRepository;
import com.configuration.machine.models.Location;
import com.configuration.machine.models.Machine;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Log4j2
public class LocationService {

    @Autowired
    LocationRepository locationRepository;

    public List<Location> getAllLocations(){
        log.trace("inside getAllLocations method");
        return locationRepository.findAll();
    }

}
