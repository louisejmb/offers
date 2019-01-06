package com.merchant.offer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.merchant.offer.domain.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>{

}
