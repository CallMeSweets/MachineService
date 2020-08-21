package com.configuration.machine.mock;

import com.configuration.machine.controllers.LocationController;
import com.configuration.machine.models.Location;
import com.configuration.machine.services.LocationService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@ExtendWith(MockitoExtension.class)
public class LocationControllerMockTests {

    @Mock
    private LocationService locationService;

    @InjectMocks
    private LocationController locationController;

    @Test
    void getAllLocationsTest(){
        Mockito.when(locationService.getAllLocations()).thenReturn(findAllMock());
        ResponseEntity responseEntity = locationController.getAllLocations();

        assertTrue(responseEntity != null);
        assertEquals(200, responseEntity.getStatusCodeValue());
    }




    private List<Location> findAllMock(){
        List<Location> locations = new ArrayList<>();
        locations.add(new Location());
        locations.add(new Location());

        return locations;
    }


}
