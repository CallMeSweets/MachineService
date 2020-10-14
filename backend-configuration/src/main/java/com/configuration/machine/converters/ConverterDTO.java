package com.configuration.machine.converters;

import com.configuration.machine.dto.LocationDTO;
import com.configuration.machine.dto.MachineDTO;
import com.configuration.machine.dto.MachineProductDTO;
import com.configuration.machine.dto.ProductDTO;
import com.configuration.machine.models.Location;
import com.configuration.machine.models.Machine;
import com.configuration.machine.models.MachineProduct;
import com.configuration.machine.models.Product;
import lombok.extern.log4j.Log4j2;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Log4j2
public class ConverterDTO {

    public List<Machine> convertListMachineDTOToListMachine(List<MachineDTO> machineDTOS){
        log.info("list machine convert to DTO machine list started");
        if(machineDTOS == null || machineDTOS.size() == 0){
            log.warn("Machine list is null or empty.");
        }

        List<Machine> machines = new ArrayList<>();

        machineDTOS.forEach((machineDTO -> {
            machines.add(this.convertMachineDTOToMachine(machineDTO, null));
        }));

        return machines;
    }

    public  Machine convertMachineDTOToMachine(MachineDTO machineDTO, Machine machine) {
        log.trace("machine conversion to machineDTO, machineId: " + machineDTO.getId());
        Machine machineConverted = null;
        machineConverted = (machine != null) ? machine : new Machine();

        machineConverted.setName(machineDTO.getName());
        machineConverted.setMachineType(machineDTO.getMachineType());
        machineConverted.setDescription(machineDTO.getDescription());
        machineConverted.setTotalSpace(machineDTO.getTotalSpace());

        log.trace("MachineDTO converted to Machine, machineId: ", machineConverted.getName());

        return machineConverted;
    }

    public List<MachineDTO> convertListMachineToListMachineDTO(List<Machine> machines){
        log.info("list machine convert to DTO machine list started");
        if(machines == null || machines.size() == 0){
            log.trace("Machine list is null or empty.");
        }

        return machines
                .stream()
                .map(this::convertMachineToMachineDTO)
                .collect(Collectors.toList());
    }

    public MachineDTO convertMachineToMachineDTO(Machine machine){
        log.trace("machine conversion to machineDTO, machineId: " + machine.getId());
        MachineDTO machineDTO = new MachineDTO();

        machineDTO.setId(machine.getId());
        machineDTO.setName(machine.getName());
        machineDTO.setMachineType(machine.getMachineType());
        machineDTO.setDescription(machine.getDescription());
        machineDTO.setTotalSpace(machine.getTotalSpace());
        machineDTO.setLocationDTO(this.convertLocationToLocationDTO(machine.getLocation()));
        machineDTO.setOwnerId(machine.getOwner().getId());

        log.trace("Machine converted to MachineDTO, machineId: ", machine.getId());

        return machineDTO;
    }

    public List<LocationDTO> convertListLocationToListLocationDTO(List<Location> locations){
        log.trace("list location conversion to list locationDTO");
        if(locations == null || locations.size() == 0){
            log.warn("Location list is null or empty.");
        }

        List<LocationDTO> locationDTOs = new ArrayList<>();
        for(Location location: locations){
            locationDTOs.add(this.convertLocationToLocationDTO(location));
        }

        return locationDTOs;
    }

    public LocationDTO convertLocationToLocationDTO(Location location){
        log.trace("location conversion to locationDTO, locationId: " + location.getId());
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
        log.trace("locationDTO conversion to location");
        Location locationConverted = null;
        locationConverted = (location != null) ? location : new Location();


        locationConverted.setCity(locationDTO.getCity());
        locationConverted.setDescription(locationDTO.getDescription());
        locationConverted.setStreet(locationDTO.getStreet());
        locationConverted.setStreetNumber(locationDTO.getStreetNumber());

        return locationConverted;
    }

    public List<Product> convertListProductDTOToListProduct(List<ProductDTO> productDTOs){
        log.trace("list product conversion to list productDTO");
        if(productDTOs == null || productDTOs.size() == 0){
            log.warn("ProductDTO list is null or empty.");
        }

        List<Product> products = new ArrayList<>();
        for(ProductDTO productDTO: productDTOs){
            products.add(this.convertDTOProductToProduct(productDTO, null));
        }

        return products;
    }

    public Product convertDTOProductToProduct(ProductDTO productDTO, Product product){
        log.trace("productDTO conversion to product");
        Product productConverted = null;
        productConverted = (product != null) ? product : new Product();

        productConverted.setWeight(productDTO.getWeight());
        productConverted.setName(productDTO.getName());
        productConverted.setPrice(productDTO.getPrice());
        productConverted.setProductType(productDTO.getProductType());

        return productConverted;
    }

    public List<ProductDTO> convertListProductToListProductDTO(List<Product> products){
        log.trace("list product conversion to list productDTO");
        if(products == null || products.size() == 0){
            log.warn("Product list is null or empty.");
        }

        List<ProductDTO> productDTOs = new ArrayList<>();
        for(Product product: products){
            productDTOs.add(this.convertProductToProductDTO(product));
        }

        return productDTOs;
    }

    public ProductDTO convertProductToProductDTO(Product product){
        log.trace("product conversion to productDTO");

        ProductDTO productDTO = new ProductDTO();

        productDTO.setId(product.getId());
        productDTO.setOwnerId(product.getOwner().getId());
        productDTO.setWeight(product.getWeight());
        productDTO.setName(product.getName());
        productDTO.setPrice(product.getPrice());
        productDTO.setProductType(product.getProductType());

        return productDTO;
    }

    public MachineProductDTO convertMachineProductToMachineProductDTO(MachineProduct machineProduct) {
        MachineProductDTO machineProductDTO = new MachineProductDTO();

        machineProductDTO.setId(machineProduct.getId());
        machineProductDTO.setMachineDTO(this.convertMachineToMachineDTO(machineProduct.getMachine()));
        machineProductDTO.setProductDTO(this.convertProductToProductDTO(machineProduct.getProduct()));
        machineProductDTO.setNumberOfProducts(machineProduct.getNumberOfProducts());
        machineProductDTO.getMachineDTO().setLocationDTO(null);

        return machineProductDTO;
    }

    public List<MachineProductDTO> convertListMachineProductToListMachineProductDTO(List<MachineProduct> machineProducts) {
        log.trace("list machineProduct conversion to list productDTO");
        if(machineProducts == null || machineProducts.size() == 0){
            log.warn("Product list is null or empty.");
        }

        List<MachineProductDTO> machineProductDTOs = new ArrayList<>();
        for(MachineProduct machineProduct: machineProducts){
            machineProductDTOs.add(this.convertMachineProductToMachineProductDTO(machineProduct));
        }

        return machineProductDTOs;
    }

}
