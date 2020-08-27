package com.configuration.machine.services;

import com.configuration.machine.dao.ProductRepository;
import com.configuration.machine.models.Product;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Log4j2
public class ProductService {

    @Autowired
    ProductRepository productRepository;

    public Product createProduct(Product product){
        log.trace("save new product to db");
        return productRepository.save(product);
    }

    public Boolean deleteProductById(Long id){
        log.debug("delete product from db");
        productRepository.deleteById(id);
        log.debug("Product deleted from database id: " + id);
        return true;
    }

    public List<Product> getAllOwnerProductsByOwnerId(Long id){
        log.trace("get all owner product from db");
        return removeUsersProductsRelation(productRepository.getAllOwnerProductsByOwnerId(id));
    }

    private List<Product> removeUsersProductsRelation(List<Product> products){
        return products
                    .stream()
                    .map(this::removeUsersProductsRelation)
                    .collect(Collectors.toList());
    }

    private Product removeUsersProductsRelation(Product product){
        product.setOwner(null);
        return product;
    }

}
