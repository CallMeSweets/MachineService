package com.configuration.machine.unit.context;

import com.configuration.machine.converters.ConverterDTO;
import com.configuration.machine.enums.MachineType;
import com.configuration.machine.models.Location;
import com.configuration.machine.models.Machine;
import com.configuration.machine.models.Owner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;


@Configuration
public class ConfigTests {


    @Bean(name = "converterDTOTests")
    public ConverterDTO getConvertedDTO(){
        return new ConverterDTO();
    }

    @Bean(name = "machinesNoRelations")
    public List<Machine> getMachinesNoRelations(){
        List<Machine> machines = new ArrayList<>();
        machines.add(getDefultMachineNoRelations());
        machines.add(getDefultMachineNoRelations());

        return machines;
    }

    @Bean(name = "machinesWithRelations")
    public List<Machine> getMachinesWithRelations(){
        List<Machine> machines = new ArrayList<>();
        machines.add(getDefultMachineWithRelations());
        machines.add(getDefultMachineWithRelations());

        return machines;
    }

    private Machine getDefultMachineNoRelations(){
        Machine machine = new Machine();
        machine.setId((long) 1);
        machine.setLocation(null);
        machine.setMachineProducts(null);
        machine.setName("DefaultMachine");
        machine.setOwner(null);
        machine.setMachineType(MachineType.DRINK);

        return machine;
    }

    private Machine getDefultMachineWithRelations(){
        Machine machine = getDefultMachineNoRelations();
        machine.setLocation(new Location());
        machine.setOwner(new Owner());

        return machine;
    }
}
