package com.likelion.likelionassignmentud.menu.application;

import com.likelion.likelionassignmentud.menu.api.dto.request.MenuSaveRequestDto;
import com.likelion.likelionassignmentud.menu.api.dto.request.MenuUpdateRequestDto;
import com.likelion.likelionassignmentud.menu.api.dto.response.MenuInfoResponseDto;
import com.likelion.likelionassignmentud.menu.api.dto.response.MenuListResponseDto;
import com.likelion.likelionassignmentud.menu.domain.Menu;
import com.likelion.likelionassignmentud.menu.domain.repository.MenuRepository;
import com.likelion.likelionassignmentud.restaurant.domain.Restaurant;
import com.likelion.likelionassignmentud.restaurant.domain.repository.RestaurantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional (readOnly = true)
public class MenuService {
    private final RestaurantRepository restaurantRepository;
    private final MenuRepository menuRepository;

    //메뉴 저장
    @Transactional
    public void menuSave(MenuSaveRequestDto menuSaveRequestDto) {
        Restaurant restaurant = restaurantRepository.findById(menuSaveRequestDto.menuId()).orElseThrow(IllegalArgumentException::new);
        Menu menu = Menu.builder()
                .menuName(menuSaveRequestDto.menuName())
                .price(menuSaveRequestDto.price())
                .restaurant(restaurant)
                .build();
        menuRepository.save(menu);
    }
    //특정 식당으로 메뉴 목록을 조회
    public MenuListResponseDto menuFindRestaurant(Long restaurantId) {
        Restaurant restaurant = restaurantRepository.findById(restaurantId).orElseThrow(IllegalArgumentException::new);

        List<Menu> menus = menuRepository.findByRestaurant(restaurant);
        List<MenuInfoResponseDto> menuInfoResponseDtos = menus.stream()
                .map(MenuInfoResponseDto::from)
                .toList();
        return MenuListResponseDto.from(menuInfoResponseDtos);
    }
    //메뉴 수정
    @Transactional
    public void menuUpdate(Long menuId, MenuUpdateRequestDto menuUpdateRequestDto) {
        Menu menu = menuRepository.findById(menuId).orElseThrow(IllegalArgumentException::new);
        menu.update(menuUpdateRequestDto);
    }

    //메뉴 삭제
    @Transactional
    public void menuDelete(Long menuId){
        Menu menu = menuRepository.findById(menuId).orElseThrow(IllegalArgumentException::new);
        menuRepository.delete(menu);
    }
}
