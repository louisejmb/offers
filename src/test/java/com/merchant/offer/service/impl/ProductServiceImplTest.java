package com.merchant.offer.service.impl;

import static org.junit.Assert.*;

import com.merchant.offer.domain.Product;
import com.merchant.offer.repository.ProductRepository;
import com.merchant.offer.service.ProductService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.Optional;


@RunWith(SpringRunner.class)
@ContextConfiguration(classes = ProductServiceImpl.class)
public class ProductServiceImplTest {

    @MockBean
    private ProductRepository productRepository;

    @Autowired
    private ProductService productService;


    Product testValidProduct = new Product(
            "Coffee",
            "GBP",
            100
    );

    @Test
    public void listAllTest() throws Exception {
        Mockito.when(productRepository.findAll()).thenReturn(Arrays.asList(testValidProduct));
        assertEquals(productService.listAll(), Arrays.asList(testValidProduct));
    }

    @Test
    public void getByIdTest() throws Exception {
        Mockito.when(productRepository.findById(new Long(1))).thenReturn(Optional.of(testValidProduct));
        assertEquals(productService.getById(new Long(1)), testValidProduct);
    }

    @Test
    public void createNewTest() throws Exception {
        productService.createNew(
                "Sweets",
                "GBP",
                199
        );
    }

    @Test
    public void deleteProductTest() throws Exception {
        productService.deleteById(new Long(2));
    }
}