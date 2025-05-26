package com.likelion.likelionassignmentud.menu.domain;

import com.likelion.likelionassignmentud.menu.api.dto.request.MenuUpdateRequestDto;
import com.likelion.likelionassignmentud.restaurant.domain.Restaurant;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Menu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "menu_id")
    private Long menuId;

    private String menuName;

    private int price;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_id")
    private Restaurant restaurant;

    @Builder
    private Menu(String menuName, int price, Restaurant restaurant) {
        this.menuName = menuName;
        this.price = price;
        this.restaurant = restaurant;
    }
    public void update(MenuUpdateRequestDto menuUpdateRequestDto) {
        this.menuName = menuUpdateRequestDto.menuName();
        this.price = menuUpdateRequestDto.price();
    }
}
