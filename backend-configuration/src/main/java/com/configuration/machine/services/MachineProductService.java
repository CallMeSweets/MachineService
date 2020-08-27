package com.configuration.machine.services;

import com.configuration.machine.dao.MachineProductRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Log4j2
public class MachineProductService {

    @Autowired
    MachineProductRepository machineProductRepository;

    public Boolean deleteProductFromMachine(Long id){
        log.trace("delete product from machines, product id: " + id);
        machineProductRepository.deleteProductFromMachineByProductId(id);
        log.trace("product deleted from machines, product id: " + id);

        return true;
    }

}
