package com.configuration.machine.controllers;

import com.configuration.machine.converters.ConverterDTO;
import com.configuration.machine.dto.ProductDTO;
import com.configuration.machine.models.Product;
import com.configuration.machine.services.MachineProductService;
import com.configuration.machine.services.ProductService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/configuration/products")
@Log4j2
@CrossOrigin
public class ProductController {

    private ProductService productService;
    private MachineProductService machineProductService;

    @Autowired
    public ProductController(ProductService productService,
                             MachineProductService machineProductService) {
        this.productService = productService;
        this.machineProductService = machineProductService;
    }

    @PostMapping("/create")
    public ResponseEntity createProduct(@RequestBody ProductDTO productDTO){
        ProductDTO createdProductDTO = productService.createProduct(productDTO);
        log.trace("New product created, name: " + createdProductDTO.getName());
        return ResponseEntity.ok(createdProductDTO);
    }

    @PatchMapping("/update")
    public ResponseEntity updateProduct(@RequestBody ProductDTO productDTO){
        ProductDTO updatedProductDTO = productService.updateProduct(productDTO);
        log.trace("product updated, name: " + updatedProductDTO.getName());
        return ResponseEntity.ok(updatedProductDTO);
    }

    @PostMapping("/delete")
    @Transactional
    public ResponseEntity deleteProducts(@RequestBody List<ProductDTO> productDTOs){
        machineProductService.deleteProductFromMachine(productDTOs);
        productService.deleteProducts(productDTOs);
        return ResponseEntity.ok(HttpStatus.OK);

    }

    @GetMapping("/{id}")
    public ResponseEntity getAllOwnerProductsByOwnerId(@PathVariable Long id){
        return ResponseEntity.ok(productService.getAllOwnerProductsByOwnerId(id));
    }

}
