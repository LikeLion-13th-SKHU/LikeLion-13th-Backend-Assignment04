package com.likelion.likelionassignmentcrud.buy.domain.repository;

import com.likelion.likelionassignmentcrud.product.domain.Product;
import com.likelion.likelionassignmentcrud.buy.domain.Buy;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BuyRepository extends JpaRepository<Buy, Long> {
    List<Buy> findByProduct(Product product);
}