package com.configuration.machine.services;

import com.configuration.machine.converters.ConverterDTO;
import com.configuration.machine.dao.MachineProductRepository;
import com.configuration.machine.dao.MachineRepository;
import com.configuration.machine.dao.ProductRepository;
import com.configuration.machine.dto.MachineProductDTO;
import com.configuration.machine.dto.ProductDTO;
import com.configuration.machine.models.Machine;
import com.configuration.machine.models.MachineProduct;
import com.configuration.machine.models.Product;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Log4j2
public class MachineProductService {

    private MachineProductRepository machineProductRepository;
    private ProductRepository productRepository;
    private MachineRepository machineRepository;
    private ConverterDTO converterDTO;

    @Autowired
    public MachineProductService(MachineProductRepository machineProductRepository,
                                 ProductRepository productRepository,
                                 MachineRepository machineRepository,
                                 ConverterDTO converterDTO) {
        this.machineProductRepository = machineProductRepository;
        this.productRepository = productRepository;
        this.machineRepository = machineRepository;
        this.converterDTO = converterDTO;
    }

    public Boolean deleteProductFromMachine(List<ProductDTO> productDTOs){
        log.trace("delete products from machines, number" + productDTOs.size());
        productDTOs.forEach(productDTO -> {
            machineProductRepository.deleteProductFromMachineByProductId(productDTO.getId());
        });

        log.trace("products deleted from machines, number: " +  productDTOs.size());
        return true;
    }

    public MachineProductDTO createMachineProduct(MachineProductDTO machineProductDTO) {
        log.info("save new MachineProduct to db");

        MachineProduct machineProduct = new MachineProduct();
        Product product = productRepository.findById(machineProductDTO.getProductDTO().getId()).get();
        Machine machine = machineRepository.findById(machineProductDTO.getMachineDTO().getId()).get();

        machineProduct.setProduct(product);
        machineProduct.setMachine(machine);
        machineProduct.setNumberOfProducts(machineProductDTO.getNumberOfProducts());

        return converterDTO.convertMachineProductToMachineProductDTO(machineProductRepository.save(machineProduct));
    }

    public List<MachineProductDTO> getAllUserMachineProductByUserId(Long id) {
        return converterDTO.convertListMachineProductToListMachineProductDTO(machineProductRepository.getAllUserMachineProductByUserId(id));
    }

    public MachineProductDTO updateMachineProduct(MachineProductDTO machineProductDTO){
        log.info("update location in db");

        MachineProduct machineProduct = machineProductRepository.findById(machineProductDTO.getId()).get();
        Product product = productRepository.findById(machineProductDTO.getProductDTO().getId()).get();
        Machine machine = machineRepository.findById(machineProductDTO.getMachineDTO().getId()).get();

        machineProduct.setProduct(product);
        machineProduct.setMachine(machine);
        machineProduct.setNumberOfProducts(machineProductDTO.getNumberOfProducts());

        return converterDTO.convertMachineProductToMachineProductDTO(machineProductRepository.save(machineProduct));
    }

    public void deleteProductMachine(List<MachineProductDTO> machineProductDTOs) {
        machineProductDTOs.forEach(machineProductDTO -> {
            machineProductRepository.deleteById(machineProductDTO.getId());
        });
    }
}
