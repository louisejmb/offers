package com.merchant.offer.service.impl;

import com.merchant.offer.domain.Offer;
import com.merchant.offer.domain.Product;
import com.merchant.offer.exception.OffersExistException;
import com.merchant.offer.repository.OfferRepository;
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

    @Autowired
    private OfferRepository offerRepository;

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
        List<Offer> o = offerRepository.findByProductId(id);

        if(o.isEmpty()) {
            productRepository.deleteById(id);
        } else {
            throw new OffersExistException("Can't delete product, offers exist");
        }
    }
}
