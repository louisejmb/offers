package com.merchant.offer.service.impl;

import com.merchant.offer.domain.Product;
import com.merchant.offer.repository.ProductRepository;
import com.merchant.offer.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<Product> listAll() {
        return productRepository.findAll();
    }

    @Override
    public Product getById(Long id) {
        Optional<Product> o = productRepository.findById(id);
        return o.get();
    }

    public void createNew(String name, String currency, int price) {
        productRepository.save(new Product(name, currency, price));
    }

    public void deleteById(long id) {
        productRepository.deleteById(id);
    }
}
