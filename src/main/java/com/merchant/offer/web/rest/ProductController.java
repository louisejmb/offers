package com.merchant.offer.web.rest;

import com.merchant.offer.domain.Product;
import com.merchant.offer.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController {

    @Autowired
    ProductService productService;

    @RequestMapping(value = "/product", method = RequestMethod.GET)
    public List<Product> getProduct() {
        return productService.listAll();
    }

    @RequestMapping(value = "/product/{id}", method = RequestMethod.GET)
    public Product getProductById(@PathVariable long id) {
        return productService.getById(id);
    }

    @RequestMapping(value = "/product", method = RequestMethod.POST)
    public void addProduct(
            @RequestParam(value = "name") String name,
            @RequestParam(value = "currency") String currency,
            @RequestParam(value = "price") int price) {
        productService.createNew(name, currency, price);
    }

    @RequestMapping(value = "/product/{id}", method = RequestMethod.DELETE)
    public void deleteProduct(@PathVariable long id) {
        productService.deleteById(id);
    }
}
