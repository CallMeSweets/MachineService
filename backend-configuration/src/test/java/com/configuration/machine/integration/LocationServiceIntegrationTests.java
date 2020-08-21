package com.configuration.machine.integration;

import com.configuration.machine.models.Location;
import com.configuration.machine.models.Owner;
import com.configuration.machine.models.Product;
import com.configuration.machine.services.LocationService;
import com.configuration.machine.services.OwnerService;
import com.configuration.machine.services.ProductService;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
@TestPropertySource(locations = "classpath:application-integration-tests.properties")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class LocationServiceIntegrationTests {

    @Autowired
    private LocationService locationService;

    @Autowired
    private DataGenerator dataGenerator;

    @Test
    @Order(1)
    void createAndGetAllLocationTest() {
        Location actualLocation = dataGenerator.createBasicLocation();

        locationService.createLocation(actualLocation);

        List<Location> expectedLocations = locationService.getAllLocations();

        assertNotNull(expectedLocations);
        assertTrue(expectedLocations.size() == 1);

        expectedLocations.get(0).setMachines(null);
        assertThat(actualLocation).isEqualToComparingFieldByField(expectedLocations.get(0));
    }


}
