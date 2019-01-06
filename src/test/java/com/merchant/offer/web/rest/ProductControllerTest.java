package com.merchant.offer.web.rest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.mockito.BDDMockito.*;

import com.merchant.offer.domain.Product;
import com.merchant.offer.service.ProductService;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {ProductController.class})
@WebMvcTest(ProductController.class)
public class ProductControllerTest {


    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductService productService;

    private ProductController productController;

    String exampleListProductJson = "[\n" +
            "    {\n" +
            "        \"id\": 0,\n" +
            "        \"name\": \"Pencil sharpener\",\n" +
            "        \"currency\": \"GBP\",\n" +
            "        \"price\": 200\n" +
            "    }]";

    String exampleProductJson = "{\n" +
            "        \"id\": 0,\n" +
            "        \"name\": \"Pencil sharpener\",\n" +
            "        \"currency\": \"GBP\",\n" +
            "        \"price\": 200\n" +
            "    }";

    Product testProduct = new Product("Pencil sharpener", "GBP", 200);

    @Test
    public void listAllProductsTest() throws Exception {
        given(this.productService.listAll()).willReturn(Arrays.asList(testProduct));
        mockMvc.perform(get("/product")).andExpect(status().isOk()).andExpect(content().json(exampleListProductJson));
    }

    @Test
    public void listProductByIdTest() throws Exception {
        given(this.productService.getById(new Long(1))).willReturn(testProduct);
        mockMvc.perform(get("/product/1")).andExpect(status().isOk()).andExpect(content().json(exampleProductJson));
    }

    @Test
    public void addProductTest() throws Exception {
        mockMvc.perform(post("/product")
                .param("name","Carrot")
                .param("currency", "GBP")
                .param("price", "100"))
                .andExpect(status().isOk());
    }

    @Test
    public void deleteProductTest() throws Exception {
        mockMvc.perform(delete("/product/1")).andExpect(status().isOk());
    }
}