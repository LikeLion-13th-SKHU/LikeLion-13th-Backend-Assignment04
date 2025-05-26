package com.likelion.likelionassignmentud.restaurant.api;

import com.likelion.likelionassignmentud.common.error.SuccessCode;
import com.likelion.likelionassignmentud.common.template.ApiResTemplate;
import com.likelion.likelionassignmentud.restaurant.api.dto.request.RestaurantSaveRequestDto;
import com.likelion.likelionassignmentud.restaurant.api.dto.request.RestaurantUpdateRequestDto;
import com.likelion.likelionassignmentud.restaurant.api.dto.response.RestaurantInfoResponseDto;
import com.likelion.likelionassignmentud.restaurant.api.dto.response.RestaurantListResponseDto;
import com.likelion.likelionassignmentud.restaurant.application.RestaurantService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/restaurant")
public class RestaurantController {
    private final RestaurantService restaurantService;

    //식당 저장
    @PostMapping("/save")
    public ApiResTemplate<String> restaurantSave(@RequestBody @Valid RestaurantSaveRequestDto restaurantSaveRequestDto) {
        restaurantService.restaurantSave(restaurantSaveRequestDto);
        return ApiResTemplate.successWithContent(SuccessCode.RESTAURANT_SAVE_SUCCESS);
    }
    //식당 전체 조회
    @GetMapping("/all")
    public ApiResTemplate<RestaurantListResponseDto>restaurantFindAll(
            @PageableDefault(size = 10, sort = "restaurantId", direction = Sort.Direction.ASC)
            Pageable pageable){
        RestaurantListResponseDto restaurantListResponseDto = restaurantService.restaurantfindAll(pageable);
        return ApiResTemplate.successResponse(SuccessCode.GET_SUCCESS, restaurantListResponseDto);
    }

    //식당 id를 통해 특정 식당 조회
    @GetMapping("/{restaurantId}")
    public ApiResTemplate<RestaurantInfoResponseDto> restaurantFindOne(@PathVariable("restaurantId") Long restaurantId){
        RestaurantInfoResponseDto restaurantInfoResponseDto = restaurantService.restaurantFindOne(restaurantId);
        return ApiResTemplate.successResponse(SuccessCode.GET_SUCCESS, restaurantInfoResponseDto);
    }
    //식당 id를 통해 식당 수정
    @PatchMapping("/{restaurantId}")
    public ApiResTemplate<String> restaurantUpdate(
            @PathVariable("restaurantId") Long restaurantId,
            @RequestBody RestaurantUpdateRequestDto restaurantUpdateRequestDto){
        restaurantService.restaurantUpdate(restaurantId, restaurantUpdateRequestDto);
        return ApiResTemplate.successWithContent(SuccessCode.RESTAURANT_UPDATE_SUCCESS);
    }
    //식당 id를 통해 식당을 삭제
    @DeleteMapping("/{restaurantId}")
    public ApiResTemplate<String> restaurantDelete
            (@PathVariable("restaurantId") Long restaurantId){
        restaurantService.restaurantDelete(restaurantId);
        return ApiResTemplate.successWithContent(SuccessCode.RESTAURANT_DELETE_SUCCESS);
    }
}
