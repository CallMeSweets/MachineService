package com.configuration.machine.services;

import com.configuration.machine.converters.ConverterDTO;
import com.configuration.machine.dao.OwnerRepository;
import com.configuration.machine.dao.ProductRepository;
import com.configuration.machine.dto.ProductDTO;
import com.configuration.machine.models.Owner;
import com.configuration.machine.models.Product;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Log4j2
public class ProductService {


    private ProductRepository productRepository;
    private OwnerRepository ownerRepository;
    private ConverterDTO converterDTO;

    @Autowired
    public ProductService(ProductRepository productRepository, OwnerRepository ownerRepository, ConverterDTO converterDTO) {
        this.productRepository = productRepository;
        this.ownerRepository = ownerRepository;
        this.converterDTO = converterDTO;
    }

    public ProductDTO createProduct(ProductDTO productDTO){
        log.trace("save new product to db");
        Product product = converterDTO.convertDTOProductToProduct(productDTO, null);
        Owner owner = ownerRepository.findById(productDTO.getOwnerId()).get();
        product.setOwner(owner);
        return converterDTO.convertProductToProductDTO(productRepository.save(product));
    }

    public ProductDTO updateProduct(ProductDTO productDTO) {
        log.trace("update product in db");
        Product product = productRepository.findById(productDTO.getId()).get();
        converterDTO.convertDTOProductToProduct(productDTO, product);
        return converterDTO.convertProductToProductDTO(productRepository.save(product));
    }

    public Boolean deleteProducts(List<ProductDTO> productDTOs){
        log.debug("delete product from db");
        productDTOs.forEach(productDTO -> {
            productRepository.deleteById(productDTO.getId());
        });

        log.debug("Products deleted from database, number: " + productDTOs.size());
        return true;
    }

    public List<ProductDTO> getAllOwnerProductsByOwnerId(Long id){
        log.trace("get all owner product from db");
        return converterDTO.convertListProductToListProductDTO(productRepository.getAllOwnerProductsByOwnerId(id));
    }

}
