package com.likelion.likelionassignmentud.restaurant.domain.repository;

import com.likelion.likelionassignmentud.restaurant.domain.Restaurant;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {
    Page<Restaurant> findAll(Pageable pageable); //기존의 리스트를 Page로 수정
}
