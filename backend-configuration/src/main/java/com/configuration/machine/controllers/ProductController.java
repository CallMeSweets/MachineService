package com.configuration.machine.controllers;

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
@RequestMapping("/products")
@Log4j2
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private MachineProductService machineProductService;

    @PostMapping
    public ResponseEntity createProduct(@RequestBody Product product){
        productService.createProduct(product);
        log.trace("New product created, name: " + product.getName());
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    @Transactional
    public ResponseEntity deleteProductById(@PathVariable Long id){
        machineProductService.deleteProductFromMachine(id);
        productService.deleteProductById(id);
        return ResponseEntity.ok(HttpStatus.OK);

    }

    @GetMapping("/owner/{id}")
    public ResponseEntity getAllOwnerProductsByOwnerId(@PathVariable Long id){
        return ResponseEntity.ok(productService.getAllOwnerProductsByOwnerId(id));
    }

}
