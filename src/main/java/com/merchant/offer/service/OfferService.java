package com.merchant.offer.service;

import com.merchant.offer.domain.Offer;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface OfferService {

    List<Offer> listAll();

    List<Offer> findByProductId(long productId);

    Offer getById(Long id);

    void createNew(long productId, String description, String currency, int price, LocalDate startTime, LocalDate endTime);

    void cancelOffer(long id);
}
