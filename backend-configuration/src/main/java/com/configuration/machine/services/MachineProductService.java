package com.configuration.machine.services;

import com.configuration.machine.dao.MachineProductRepository;
import com.configuration.machine.dto.ProductDTO;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Log4j2
public class MachineProductService {

    @Autowired
    MachineProductRepository machineProductRepository;

    public Boolean deleteProductFromMachine(List<ProductDTO> productDTOs){
        log.trace("delete products from machines, number" + productDTOs.size());
        productDTOs.forEach(productDTO -> {
            machineProductRepository.deleteProductFromMachineByProductId(productDTO.getId());
        });

        log.trace("products deleted from machines, number: " +  productDTOs.size());
        return true;
    }

}
