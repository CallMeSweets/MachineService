package com.configuration.machine.integration;

import com.configuration.machine.enums.MachineType;
import com.configuration.machine.enums.ProductType;
import com.configuration.machine.models.Location;
import com.configuration.machine.models.Machine;
import com.configuration.machine.models.Owner;
import com.configuration.machine.models.Product;
import org.springframework.stereotype.Service;

@Service
public class DataGenerator {

    public Product createBasicProduct(){
        Product product = new Product();
        product.setName("PEPSI");
        product.setProductType(ProductType.DRINK);
        product.setOwner(createBasicOwner());

        return product;
    }

    public Owner createBasicOwner(){
        Owner owner = new Owner();
        owner.setFirstName("George");
        owner.setLastName("Clooney");
        owner.setPhoneNumber("123123123");
        owner.setEmial("georgeclooney@gmail.com");

        return owner;
    }

    public Location createBasicLocation(){
        Location location = new Location();
        location.setCity("Warsaw");
        location.setStreet("Polna");
        location.setStreetNumber(12);
        location.setDescription("short description");
        location.setMachines(null);

        return location;
    }

    public Machine createBasicMachine(){
        Machine machine = new Machine();
        machine.setName("Donkey");
        machine.setMachineType(MachineType.DRINK);
        machine.setOwner(createBasicOwner());
        machine.setLocation(null);

        return machine;
    }

}
