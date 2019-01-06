package com.merchant.offer.service.impl;

import com.merchant.offer.domain.Offer;
import com.merchant.offer.domain.Product;
import com.merchant.offer.exception.InvalidDateException;
import com.merchant.offer.exception.ResourceNotFoundException;
import com.merchant.offer.repository.OfferRepository;
import com.merchant.offer.repository.ProductRepository;
import com.merchant.offer.service.OfferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class OfferServiceImpl implements OfferService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private OfferRepository offerRepository;

    @Override
    public List<Offer> listAll() {
        return offerRepository.findAll();
    }

    @Override
    public List<Offer> findByProductId(long productId) {
        return offerRepository.findByProductId(productId);
    }

    public Offer getById(Long id) {
        Optional<Offer> o = offerRepository.findById(id);
        if(o.isPresent()) {
            return o.get();
        } else {
            throw new ResourceNotFoundException("Not found");
        }
    }

    public void createNew(long productId, String description, String currency, int price, LocalDate startTime, LocalDate endTime) {

        Optional<Product> p = productRepository.findById(productId);

        if (p.isPresent()) {

            if (endTime.isBefore(LocalDate.now())) {
                throw new InvalidDateException("End date is in the past");
            }

            offerRepository.save(new Offer(p.get(), description, currency, price, startTime, endTime));
        } else {
            throw new ResourceNotFoundException("Product not found");
        }
    }

    public void cancelOffer(long id) {
        Optional<Offer> o = offerRepository.findById(id);
        if(o.isPresent()) {
            Offer offer = o.get();
            offer.setCancelled(true);
            offerRepository.save(offer);
        } else {
            throw new ResourceNotFoundException("Not found");
        }
    }
}
