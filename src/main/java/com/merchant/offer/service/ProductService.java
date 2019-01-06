package com.merchant.offer.service;

import com.merchant.offer.domain.Product;

import java.util.List;

public interface ProductService {
    List<Product> listAll();
    Product getById(Long id);
    void createNew(String name, String currency, int price);
    void deleteById(long id);
}