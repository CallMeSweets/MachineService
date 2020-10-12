package com.configuration.machine.services;

import com.configuration.machine.converters.ConverterDTO;
import com.configuration.machine.dao.LocationRepository;
import com.configuration.machine.dao.MachineRepository;
import com.configuration.machine.dao.OwnerRepository;
import com.configuration.machine.dto.LocationDTO;
import com.configuration.machine.models.Location;
import com.configuration.machine.models.Machine;
import com.configuration.machine.models.Owner;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Log4j2
public class LocationService {

    private LocationRepository locationRepository;
    private OwnerRepository ownerRepository;
    private ConverterDTO converterDTO;

    @Autowired
    public LocationService(LocationRepository locationRepository, OwnerRepository ownerRepository, ConverterDTO converterDTO) {
        this.locationRepository = locationRepository;
        this.ownerRepository = ownerRepository;
        this.converterDTO = converterDTO;
    }


    @Transactional
    public List<Location> getAllLocations(){
        log.info("load location from db");
        return locationRepository.findAll();
    }

    public LocationDTO createLocation(LocationDTO locationDTO){
        log.info("save new location to db");
        Location location = converterDTO.convertLocationDTOToLocation(locationDTO, null);
        Owner owner = ownerRepository.findById(locationDTO.getOwnerId()).get();
        location.setOwner(owner);
        return converterDTO.convertLocationToLocationDTO(locationRepository.save(location));
    }

    public void deleteLocations(List<LocationDTO> locationDTOs) {
        locationDTOs.forEach(locationDTO -> {
            locationRepository.deleteById(locationDTO.getId());
        });
    }

    public LocationDTO updateLocation(LocationDTO locationDTO){
        log.info("update location in db");
        Location location = locationRepository.findById(locationDTO.getId()).get();
        converterDTO.convertLocationDTOToLocation(locationDTO, location);
        return converterDTO.convertLocationToLocationDTO(locationRepository.save(location));
    }

    public List<LocationDTO> getAllUserLocationsByUserId(Long id) {
        return converterDTO.convertListLocationToListLocationDTO(locationRepository.getAllUserLocationsByUserId(id));
    }
}
