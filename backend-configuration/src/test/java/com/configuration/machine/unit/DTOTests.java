package com.configuration.machine.unit;


import com.configuration.machine.converters.ConverterDTO;
import com.configuration.machine.dto.MachineLocationDTO;
import com.configuration.machine.models.Machine;
import com.configuration.machine.unit.context.ConfigTests;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;


@ContextConfiguration(classes = {ConfigTests.class})
@SpringBootTest
public class DTOTests {

    @Autowired
    @Qualifier("converterDTOTests")
    private ConverterDTO converterDTO;

    @Autowired
    @Qualifier("machinesNoRelations")
    private List<Machine> machinesNoRelations;

    @Autowired
    @Qualifier("machinesWithRelations")
    private List<Machine> machinesWithRelations;

    @Test
    public void convertMachineToMachineLocationDTOSingleNoRelationTest(){
        Machine machine = machinesNoRelations.get(0);

        MachineLocationDTO expectedMachineLocationDTO = convertMachineToMachineLocationDTO(machine);
        MachineLocationDTO actualMachineLocationDTO = converterDTO.convertMachineToMachineLocationDTO(machine);

        assertThat(actualMachineLocationDTO).isEqualToComparingFieldByField(expectedMachineLocationDTO);
    }

    @Test
    public void convertMachineToMachineLocationDTOSingleWithRelationTest(){
        Machine machine = machinesWithRelations.get(0);

        MachineLocationDTO expectedMachineLocationDTO = convertMachineToMachineLocationDTO(machine);
        MachineLocationDTO actualMachineLocationDTO = converterDTO.convertMachineToMachineLocationDTO(machine);

        assertThat(actualMachineLocationDTO).isEqualToComparingFieldByField(expectedMachineLocationDTO);
    }

    @Test
    public void convertMachineToMachineLocationDTOListNoRelationTest(){
        // no relation
        List<MachineLocationDTO> expectedMachineLocationDTOs = convertMachineToMachineLocationDTO(machinesNoRelations);
        List<MachineLocationDTO> actualMachineLocationDTOs = converterDTO.convertMachineToMachineLocationDTO(machinesNoRelations);

        assertEquals(expectedMachineLocationDTOs.size(), actualMachineLocationDTOs.size());

        for(int i = 0; i < expectedMachineLocationDTOs.size(); i++){
            assertThat(expectedMachineLocationDTOs.get(i)).isEqualToComparingFieldByField(actualMachineLocationDTOs.get(i));
        }
    }

    @Test
    public void convertMachineToMachineLocationDTOListWithRelationTest(){
        // no relation
        List<MachineLocationDTO> expectedMachineLocationDTOs = convertMachineToMachineLocationDTO(machinesWithRelations);
        List<MachineLocationDTO> actualMachineLocationDTOs = converterDTO.convertMachineToMachineLocationDTO(machinesWithRelations);

        assertEquals(expectedMachineLocationDTOs.size(), actualMachineLocationDTOs.size());

        for(int i = 0; i < expectedMachineLocationDTOs.size(); i++){
            assertThat(expectedMachineLocationDTOs.get(i)).isEqualToComparingFieldByField(actualMachineLocationDTOs.get(i));
        }
    }

    private List<MachineLocationDTO> convertMachineToMachineLocationDTO(List<Machine> machines){
        return machines
                .stream()
                .map(this::convertMachineToMachineLocationDTO)
                .collect(Collectors.toList());
    }

    private MachineLocationDTO convertMachineToMachineLocationDTO(Machine machine) {
        MachineLocationDTO machineLocationDTO = new MachineLocationDTO();
        machineLocationDTO.setName(machine.getName());
        machineLocationDTO.setMachineType(machine.getMachineType());
        machineLocationDTO.setLocations(machine.getLocation());

        return machineLocationDTO;
    }
}
