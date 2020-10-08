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


@RestController
@RequestMapping("/configuration/products")
@Log4j2
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
        ProductDTO createdProductDTO = productService.updateProduct(productDTO);
        log.trace("New product created, name: " + createdProductDTO.getName());
        return ResponseEntity.ok(createdProductDTO);
    }

    @DeleteMapping("/delete/{id}")
    @Transactional
    public ResponseEntity deleteProductById(@PathVariable Long id){
        machineProductService.deleteProductFromMachine(id);
        productService.deleteProductById(id);
        return ResponseEntity.ok(HttpStatus.OK);

    }

    @GetMapping("/{id}")
    public ResponseEntity getAllOwnerProductsByOwnerId(@PathVariable Long id){
        return ResponseEntity.ok(productService.getAllOwnerProductsByOwnerId(id));
    }

}
