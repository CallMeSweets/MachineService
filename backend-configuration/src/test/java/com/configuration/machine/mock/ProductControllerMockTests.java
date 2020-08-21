package com.configuration.machine.mock;

import com.configuration.machine.controllers.ProductController;
import com.configuration.machine.models.Product;
import com.configuration.machine.services.MachineProductService;
import com.configuration.machine.services.ProductService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@ExtendWith(MockitoExtension.class)
public class ProductControllerMockTests {

    @Mock
    private ProductService productService;

    @Mock
    private MachineProductService machineProductService;

    @InjectMocks
    private ProductController productController;


    @Test
    void createProductTest(){
        Product product = createProductMock();

        Mockito.when(productService.createProduct(product)).thenReturn(product);
        ResponseEntity responseEntity = productController.createProduct(product);

        assertTrue(responseEntity != null);
        assertEquals(200, responseEntity.getStatusCodeValue());
    }


    @Test
    void deleteProductByIdTest(){
        long id = 1;

        Mockito.when(machineProductService.deleteProductFromMachine(id)).thenReturn(true);
        Mockito.when(productService.deleteProductById(id)).thenReturn(true);
        ResponseEntity responseEntity = productController.deleteProductById(id);

        assertTrue(responseEntity != null);
        assertEquals(200, responseEntity.getStatusCodeValue());
    }


    @Test
    void getAllOwnerProductsByOwnerIdTest(){
        long id = 1;

        Mockito.when(productService.getAllOwnerProductsByOwnerId(id)).thenReturn(findAllOwnerProductsMock());
        ResponseEntity responseEntity = productController.getAllOwnerProductsByOwnerId(id);

        assertTrue(responseEntity != null);
        assertEquals(200, responseEntity.getStatusCodeValue());
    }


    private List<Product> findAllOwnerProductsMock(){
        List<Product> products = new ArrayList<>();
        products.add(new Product());
        products.add(new Product());

        return products;
    }

    private Product createProductMock(){
        return new Product();
    }

}
