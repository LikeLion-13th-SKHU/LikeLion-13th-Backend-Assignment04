package com.likelion.likelionassignmentcrud.product.domain.repository;

import com.likelion.likelionassignmentcrud.product.domain.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
    Page<Product> findAll(Pageable pageable); // 기존 List를 Page로 수정
}