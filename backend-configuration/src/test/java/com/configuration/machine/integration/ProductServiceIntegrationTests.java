package com.configuration.machine.integration;

import com.configuration.machine.models.Owner;
import com.configuration.machine.models.Product;
import com.configuration.machine.services.OwnerService;
import com.configuration.machine.services.ProductService;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@TestPropertySource(locations = "classpath:application-integration-tests.properties")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ProductServiceIntegrationTests {

    private Long PRODUCT_ID = Long.valueOf(1);
    private Long OWNER_ID = Long.valueOf(1);


    @Autowired
    private ProductService productService;

    @Autowired
    private OwnerService ownerService;

    @Autowired
    private DataGenerator dataGenerator;

    @Test
    @Order(1)
    void createProductOwnerExistTest() {
        Product actualProduct = dataGenerator.createBasicProduct();
        Owner actualOwner = actualProduct.getOwner();

        ownerService.createOwner(actualOwner);
        productService.createProduct(actualProduct);

        List<Product> expectedProducts = productService.getAllOwnerProductsByOwnerId(OWNER_ID);

        assertNotNull(expectedProducts);
        assertTrue(expectedProducts.size() == 1);

        actualProduct.setOwner(null);
        assertThat(actualProduct).isEqualToComparingFieldByField(expectedProducts.get(0));

        assertTrue(expectedProducts.get(0).getOwner() == null);
    }

    @Test
    @Order(2)
    void createProductNoOwnerExistTest() {
        Product actualProduct = dataGenerator.createBasicProduct();
        actualProduct.setOwner(null);

        String expectedMessage = "No class com.configuration.machine.models.Owner entity with id 1 exists!";
        try {
            productService.createProduct(actualProduct);
        } catch (EmptyResultDataAccessException e) {
            String actualMessage = e.getMessage();
            assertTrue(actualMessage.contains(expectedMessage));
        }
    }

    @Test
    @Order(3)
    void deleteProductTest() {
        List<Product> expectedProducts;


        expectedProducts = productService.getAllOwnerProductsByOwnerId(OWNER_ID);
        assertTrue(expectedProducts.size() == 1);

        assertTrue(productService.deleteProductById(PRODUCT_ID));

        expectedProducts = productService.getAllOwnerProductsByOwnerId(OWNER_ID);
        assertNotNull(expectedProducts);
        assertTrue(expectedProducts.size() == 0);


        String expectedMessage = "No class com.configuration.machine.models.Product entity with id 1 exists!";
        try {
            productService.deleteProductById(PRODUCT_ID);
        } catch (EmptyResultDataAccessException e) {
            String actualMessage = e.getMessage();
            assertTrue(actualMessage.contains(expectedMessage));
        }

    }


}
