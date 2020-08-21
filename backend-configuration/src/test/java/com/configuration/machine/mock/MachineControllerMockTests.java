package com.configuration.machine.mock;

import com.configuration.machine.controllers.MachineController;
import com.configuration.machine.dto.MachineLocationDTO;
import com.configuration.machine.models.Machine;
import com.configuration.machine.models.Owner;
import com.configuration.machine.services.MachineService;
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
public class MachineControllerMockTests {

    @Mock
    private MachineService machineService;

    @InjectMocks
    private MachineController machineController;

    @Test
    void getAllMachinesTest(){
        Mockito.when(machineService.getAllMachines()).thenReturn(findAllMock());
        List<MachineLocationDTO> machines = machineController.getAllMachines();

        assertTrue(machines != null);
        assertEquals(2, machines.size());
    }

    @Test
    void getAllMachinesWithLocationsTest(){
        Mockito.when(machineService.getAllMachinesWithLocations()).thenReturn(findAllWithLocationMock());
        List<Machine> machines = machineController.getAllMachinesWithLocations();

        assertTrue(machines != null);
        assertEquals(2, machines.size());
    }


    @Test
    void getMachineByIdTest(){
        MachineLocationDTO machine = createMachineDTOMock();
        long id = 1;

        Mockito.when(machineService.getMachineById(id)).thenReturn(machine);
        ResponseEntity responseEntity = machineController.getMachineById(id);

        assertTrue(responseEntity != null);
        assertEquals(200, responseEntity.getStatusCodeValue());
    }

    @Test
    void deleteMachineByIdTest(){
        long id = 1;

        Mockito.when(machineService.deleteMachineById(id)).thenReturn(true);
        ResponseEntity responseEntity = machineController.deleteMachineById(id);

        assertTrue(responseEntity != null);
        assertEquals(200, responseEntity.getStatusCodeValue());
    }

    @Test
    void createMachineTest(){
        Machine machine = createMachineMock();

        Mockito.when(machineService.createMachine(machine)).thenReturn(machine);
        ResponseEntity responseEntity = machineController.createMachine(machine);

        assertTrue(responseEntity != null);
        assertEquals(200, responseEntity.getStatusCodeValue());
    }

    @Test
    void getAllMachinesByOwnerIdTest(){
        long id = 1;

        Mockito.when(machineService.getAllOwnerMachinesByOwnerId(id)).thenReturn(findAllMock());
        ResponseEntity responseEntity = machineController.getAllMachinesByOwnerId(id);

        assertTrue(responseEntity != null);
        assertEquals(200, responseEntity.getStatusCodeValue());
    }

    private List<MachineLocationDTO> findAllMock(){
        List<MachineLocationDTO> machines = new ArrayList<>();
        machines.add(new MachineLocationDTO());
        machines.add(new MachineLocationDTO());

        return machines;
    }

    private List<Machine> findAllWithLocationMock(){
        List<Machine> machines = new ArrayList<>();
        machines.add(new Machine());
        machines.add(new Machine());

        return machines;
    }

    private MachineLocationDTO createMachineDTOMock(){
        return new MachineLocationDTO();
    }

    private Machine createMachineMock(){
        return new Machine();
    }



}
