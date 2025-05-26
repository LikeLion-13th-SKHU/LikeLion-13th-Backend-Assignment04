package com.likelion.likelionassignmentud.restaurant.domain;

import com.likelion.likelionassignmentud.menu.domain.Menu;
import com.likelion.likelionassignmentud.restaurant.api.dto.request.RestaurantUpdateRequestDto;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Restaurant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "restaurant_id")
    private Long restaurantId;

    private String restaurantName;

    private String number;

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private Part part;

    @OneToMany(mappedBy = "restaurant", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Menu> menu = new ArrayList<>();

    @Builder
    private Restaurant(String name, String number, Part part) {
        this.restaurantName = name;
        this.number = number;
        this.part = part;
    }
    public void update(RestaurantUpdateRequestDto restaurantUpdateRequestDto) {
        this.restaurantName = restaurantUpdateRequestDto.restaurantName();
        this.number = restaurantUpdateRequestDto.number();
    }
}
