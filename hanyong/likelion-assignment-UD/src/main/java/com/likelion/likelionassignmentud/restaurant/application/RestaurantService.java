package com.likelion.likelionassignmentud.restaurant.application;

import com.likelion.likelionassignmentud.common.error.ErrorCode;
import com.likelion.likelionassignmentud.common.exception.BusinessException;
import com.likelion.likelionassignmentud.restaurant.api.dto.request.RestaurantSaveRequestDto;
import com.likelion.likelionassignmentud.restaurant.api.dto.request.RestaurantUpdateRequestDto;
import com.likelion.likelionassignmentud.restaurant.api.dto.response.RestaurantInfoResponseDto;
import com.likelion.likelionassignmentud.restaurant.api.dto.response.RestaurantListResponseDto;
import com.likelion.likelionassignmentud.restaurant.domain.Restaurant;
import com.likelion.likelionassignmentud.restaurant.domain.repository.RestaurantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class RestaurantService {
    private final RestaurantRepository restaurantRepository;

    //식당 정보 저장
    @Transactional
    public void restaurantSave(RestaurantSaveRequestDto restaurantSaveRequestDto) {
        Restaurant restaurant = Restaurant.builder()
                .name(restaurantSaveRequestDto.restaurantName())
                .number(restaurantSaveRequestDto.number())
                .part(restaurantSaveRequestDto.part())
                .build();
        restaurantRepository.save(restaurant);
    }

    //식당 모두 조회
    public RestaurantListResponseDto restaurantfindAll(Pageable pageable) {
        Page<Restaurant> restaurants = restaurantRepository.findAll(pageable); //기존의 List를 Page로 수정
        List<RestaurantInfoResponseDto> restaurantInfoResponseDtoList = restaurants.stream()
                .map(RestaurantInfoResponseDto::from)
                .toList();
        return RestaurantListResponseDto.from(restaurantInfoResponseDtoList);
    }
    // 단일 식당 조회
    public RestaurantInfoResponseDto restaurantFindOne(Long restaurantId) {
        Restaurant restaurant = restaurantRepository
                .findById(restaurantId)
                .orElseThrow(
                        () -> new BusinessException(ErrorCode.RESTAURANT_NOT_FOUND_EXCEPTION,
                                ErrorCode.RESTAURANT_NOT_FOUND_EXCEPTION.getMessage()+ restaurantId)
                );
        return RestaurantInfoResponseDto.from(restaurant);
    }
    //식당 정보 수정
    @Transactional
    public void restaurantUpdate(Long restaurantId, RestaurantUpdateRequestDto restaurantUpdateRequestDto) {
        Restaurant restaurant = restaurantRepository.findById(restaurantId).orElseThrow(IllegalArgumentException::new);
        restaurant.update(restaurantUpdateRequestDto);
    }
    //식당 정보 삭제
    @Transactional
    public void restaurantDelete(Long restaurantId) {
        Restaurant restaurant = restaurantRepository.findById(restaurantId).orElseThrow(IllegalArgumentException::new);
        restaurantRepository.delete(restaurant);
    }
}
