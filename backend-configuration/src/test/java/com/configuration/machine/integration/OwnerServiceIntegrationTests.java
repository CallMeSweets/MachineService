package com.configuration.machine.integration;


import com.configuration.machine.models.Owner;
import com.configuration.machine.services.OwnerService;
import org.junit.jupiter.api.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@TestPropertySource(locations = "classpath:application-integration-tests.properties")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class OwnerServiceIntegrationTests {

    @Autowired
    private OwnerService ownerService;

    @Autowired
    private DataGenerator dataGenerator;



    @Test
    @Order(2)
    void createOwnerTest() {
        Owner actualOwner = dataGenerator.createBasicOwner();
        ownerService.createOwner(actualOwner);
        Owner expectedOwner = ownerService.getOwnerById((long) 1);

        assertNotNull(expectedOwner);
        assertThat(actualOwner).isEqualToComparingFieldByField(expectedOwner);
    }

    @Test
    @Order(3)
    void getAllOwnersTest() {
        Owner actualOwner = dataGenerator.createBasicOwner();
        actualOwner.setId((long) 1);
        List<Owner> expectedOwners = ownerService.getAllOwners();

        assertTrue(expectedOwners != null);
        assertTrue(expectedOwners.size() == 1);
        assertThat(actualOwner).isEqualToComparingFieldByField(expectedOwners.get(0));
    }

    @Test
    @Order(4)
    void updateOwnerTest() {
        Owner expectedOwnerNotUpdated = ownerService.getOwnerById((long) 1);
        Owner actualOwner = dataGenerator.createBasicOwner();
        actualOwner.setFirstName("Mario");
        actualOwner.setId((long) 1);

        ownerService.createOwner(actualOwner);
        Owner expectedOwnerUpdated = ownerService.getOwnerById((long) 1);

        assertFalse(expectedOwnerNotUpdated.getFirstName().equals(expectedOwnerUpdated.getFirstName()));
        assertThat(actualOwner).isEqualToComparingFieldByField(expectedOwnerUpdated);
    }

    @Test
    @Order(5)
    void deleteOwnerTest() {
        List<Owner> expectedOwners = ownerService.getAllOwners();

        assertTrue(expectedOwners.size() == 1);
        assertTrue(ownerService.deleteOwnerById((long) 1));

        expectedOwners = ownerService.getAllOwners();
        assertTrue(expectedOwners.size() == 0);

        String expectedMessage = "No class com.configuration.machine.models.Owner entity with id 1 exists!";
        try {
            ownerService.deleteOwnerById((long) 1);
        } catch (EmptyResultDataAccessException e) {
            String actualMessage = e.getMessage();
            assertTrue(actualMessage.contains(expectedMessage));
        }

    }

}
