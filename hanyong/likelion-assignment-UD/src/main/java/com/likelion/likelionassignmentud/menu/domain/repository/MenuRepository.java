package com.likelion.likelionassignmentud.menu.domain.repository;

import com.likelion.likelionassignmentud.menu.domain.Menu;
import com.likelion.likelionassignmentud.restaurant.domain.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MenuRepository extends JpaRepository<Menu, Long> {
    List<Menu> findByRestaurant(Restaurant restaurant);
}
