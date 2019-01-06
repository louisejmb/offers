package com.merchant.offer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.merchant.offer.domain.Offer;

import java.util.List;

@Repository
public interface OfferRepository extends JpaRepository <Offer, Long> {
    List<Offer> findByProductId(long productId);

}
