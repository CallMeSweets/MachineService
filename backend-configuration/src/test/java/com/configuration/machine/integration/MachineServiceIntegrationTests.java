package com.configuration.machine.integration;

import com.configuration.machine.converters.ConverterDTO;
import com.configuration.machine.dto.MachineLocationDTO;
import com.configuration.machine.models.Machine;
import com.configuration.machine.models.Owner;
import com.configuration.machine.models.Product;
import com.configuration.machine.services.MachineService;
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
class MachineServiceIntegrationTests {

    private Long MACHINE_ID = Long.valueOf(1);
    private Long OWNER_ID = Long.valueOf(1);

    @Autowired
    private MachineService machineService;

    @Autowired
    private OwnerService ownerService;

    @Autowired
    private DataGenerator dataGenerator;

    @Autowired
    private ConverterDTO converterDTO;

    @Test
    @Order(1)
    void createAndGetMachineByIdMachineTest() {
        Machine actualMachine = dataGenerator.createBasicMachine();
        MachineLocationDTO actualMachineDTO = converterDTO.convertMachineToMachineLocationDTO(actualMachine);
        Owner actualOwner = actualMachine.getOwner();

        ownerService.createOwner(actualOwner);
        machineService.createMachine(actualMachine);

        MachineLocationDTO expectedMachineDTO = machineService.getMachineById(MACHINE_ID);

        assertNotNull(expectedMachineDTO);
        assertThat(actualMachineDTO).isEqualToComparingFieldByField(actualMachineDTO);
    }

    @Test
    @Order(2)
    void getAllMachinesWithLocationsTest() {
        Machine actualMachine = dataGenerator.createBasicMachine();
        actualMachine.setId(MACHINE_ID);

        Owner actualOwner = actualMachine.getOwner();
        actualOwner.setId(OWNER_ID);
        actualMachine.setOwner(null);

        List<Machine> expectedMachines = machineService.getAllMachinesWithLocations();
        expectedMachines.get(0).setMachineProducts(null);

        Owner expectedOwner = expectedMachines.get(0).getOwner();
        expectedMachines.get(0).setOwner(null);

        assertNotNull(expectedMachines);
        assertThat(actualMachine).isEqualToComparingFieldByField(expectedMachines.get(0));
        assertThat(actualOwner).isEqualToComparingFieldByField(expectedOwner);
    }

    @Test
    @Order(3)
    void getAllOwnerMachinesByOwnerIdTest() {
        Machine actualMachine = dataGenerator.createBasicMachine();
        MachineLocationDTO actualMachineDTO = converterDTO.convertMachineToMachineLocationDTO(actualMachine);

        List<MachineLocationDTO> expectedMachines = machineService.getAllOwnerMachinesByOwnerId(OWNER_ID);

        assertNotNull(expectedMachines);
        assertThat(actualMachineDTO).isEqualToComparingFieldByField(actualMachineDTO);

    }

    @Test
    @Order(4)
    void getAllMachinesTest() {
        Machine actualMachine = dataGenerator.createBasicMachine();
        MachineLocationDTO actualMachineDTO = converterDTO.convertMachineToMachineLocationDTO(actualMachine);

        List<MachineLocationDTO> expectedMachines = machineService.getAllMachines();

        assertNotNull(expectedMachines);
        assertTrue(expectedMachines.size() == 1);
        assertThat(actualMachineDTO).isEqualToComparingFieldByField(expectedMachines.get(0));
    }

    @Test
    @Order(5)
    void deleteMachineByIdTest() {

        List<MachineLocationDTO> expectedMachines = machineService.getAllMachines();
        assertTrue(expectedMachines.size() == 1);

        machineService.deleteMachineById(MACHINE_ID);

        expectedMachines = machineService.getAllMachines();
        assertTrue(expectedMachines.size() == 0);



        String expectedMessage = "No class com.configuration.machine.models.Machine entity with id 1 exists!";
        try {
            machineService.deleteMachineById(MACHINE_ID);
        } catch (EmptyResultDataAccessException e) {
            String actualMessage = e.getMessage();
            assertTrue(actualMessage.contains(expectedMessage));
        }
    }


}
