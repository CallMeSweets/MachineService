package com.configuration.machine.converters;

import com.configuration.machine.dto.LocationDTO;
import com.configuration.machine.dto.MachineLocationDTO;
import com.configuration.machine.dto.ProductDTO;
import com.configuration.machine.models.Location;
import com.configuration.machine.models.Machine;
import com.configuration.machine.models.Product;
import lombok.extern.log4j.Log4j2;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Log4j2
public class ConverterDTO {

    public MachineLocationDTO convertMachineToMachineLocationDTO(Machine machine){
        log.info("machine convert to DTO machine list started");
        if(machine == null){
            log.trace("Machine is null -> not converted to MachineLocationDTO");
            return null;
        }
        log.trace("machine ID: " + machine.getId() + " converted to MachineLocationDTO" );
        return convertMachineToMachineLocDTO(machine);
    }

    public List<MachineLocationDTO> convertMachineToMachineLocationDTO(List<Machine> machines){
        log.info("list machine convert to DTO machine list started");
        if(machines == null || machines.size() == 0){
            log.trace("Machine list is null or empty.");
        }

        return machines
                .stream()
                .map(this::convertMachineToMachineLocDTO)
                .collect(Collectors.toList());
    }

    private MachineLocationDTO convertMachineToMachineLocDTO(Machine machine){
        log.trace("machine convertion to machineDTO, machineId: " + machine.getId());
        MachineLocationDTO machineLocationDTO = new MachineLocationDTO();

        machineLocationDTO.setName(machine.getName());
        machineLocationDTO.setMachineType(machine.getMachineType());
        machineLocationDTO.setLocations(machine.getLocation());

        log.trace("Machine converted to MachineLocationDTO, machineId: ", machine.getId());

        return machineLocationDTO;
    }

    public List<LocationDTO> convertListLocationToListLocationDTO(List<Location> locations){
        log.trace("list location convertion to list locationDTO");

        List<LocationDTO> locationDTOs = new ArrayList<>();
        for(Location location: locations){
            locationDTOs.add(this.convertLocationToLocationDTO(location));
        }

        return locationDTOs;
    }

    public LocationDTO convertLocationToLocationDTO(Location location){
        log.trace("location convertion to locationDTO, locationId: " + location.getId());
        LocationDTO locationDTO = new LocationDTO();

        locationDTO.setCity(location.getCity());
        locationDTO.setDescription(location.getDescription());
        locationDTO.setStreet(location.getStreet());
        locationDTO.setStreetNumber(location.getStreetNumber());
        locationDTO.setId(location.getId());
        locationDTO.setOwnerId(location.getOwner().getId());

        log.trace("Location converted to LocationDTO, locationId: ", location.getId());

        return locationDTO;
    }

    public Location convertLocationDTOToLocation(LocationDTO locationDTO, Location location){
        log.trace("locationDTO convertion to location");
        Location locationConverted = null;
        locationConverted = (location != null) ? location : new Location();


        locationConverted.setCity(locationDTO.getCity());
        locationConverted.setDescription(locationDTO.getDescription());
        locationConverted.setStreet(locationDTO.getStreet());
        locationConverted.setStreetNumber(locationDTO.getStreetNumber());

        return locationConverted;
    }

    public Product convertDTOProductToProduct(ProductDTO productDTO, Product product){
        log.trace("productDTO convertion to product");
        Product productConverted = null;
        productConverted = (product != null) ? product : new Product();

        productConverted.setWeight(productDTO.getWeight());
        productConverted.setName(productDTO.getName());
        productConverted.setPrice(productDTO.getPrice());
        productConverted.setProductType(productDTO.getProductType());

        return productConverted;
    }

    public List<ProductDTO> convertListProductToListProductDTO(List<Product> products){
        log.trace("list product convertion to list productDTO");


        List<ProductDTO> productDTOs = new ArrayList<>();
        for(Product product: products){
            productDTOs.add(this.convertProductToProductDTO(product));
        }

        return productDTOs;
    }

    public ProductDTO convertProductToProductDTO(Product product){
        log.trace("product convertion to productDTO");

        ProductDTO productDTO = new ProductDTO();

        productDTO.setId(product.getId());
        productDTO.setOwnerId(product.getOwner().getId());
        productDTO.setWeight(product.getWeight());
        productDTO.setName(product.getName());
        productDTO.setPrice(product.getPrice());
        productDTO.setProductType(product.getProductType());

        return productDTO;
    }
}
