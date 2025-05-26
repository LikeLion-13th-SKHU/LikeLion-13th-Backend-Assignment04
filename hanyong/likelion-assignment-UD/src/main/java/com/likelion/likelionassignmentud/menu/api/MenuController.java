package com.likelion.likelionassignmentud.menu.api;

import com.likelion.likelionassignmentud.common.error.SuccessCode;
import com.likelion.likelionassignmentud.common.template.ApiResTemplate;
import com.likelion.likelionassignmentud.menu.api.dto.request.MenuSaveRequestDto;
import com.likelion.likelionassignmentud.menu.api.dto.request.MenuUpdateRequestDto;
import com.likelion.likelionassignmentud.menu.api.dto.response.MenuListResponseDto;
import com.likelion.likelionassignmentud.menu.application.MenuService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/menu")
public class MenuController {
    private final MenuService menuService;

    //메뉴 저장
    @PostMapping("/save")
    public ApiResTemplate<String> menuSave(@RequestBody @Valid MenuSaveRequestDto menuSaveRequestDto) {
        menuService.menuSave(menuSaveRequestDto);
        return ApiResTemplate.successWithContent(SuccessCode.MENU_SAVE_SUCCESS);
    }

    //식당 id를 기준으로 해당 식당의 메뉴 목록 조회
    @GetMapping("/{restaurantId}")
    public ApiResTemplate<MenuListResponseDto> MenuFindAll(@PathVariable("restaurantId") Long restaurantId){
        MenuListResponseDto menuListResponseDto = menuService.menuFindRestaurant(restaurantId);
        return ApiResTemplate.successResponse(SuccessCode.GET_SUCCESS, menuListResponseDto);
    }
    //메뉴 id를 기준으로 사용자가 작성한 식당 수정
    @PatchMapping("/{menuId}")
    public ApiResTemplate<String> menuUpdate(@PathVariable("menuId") Long menuId, @RequestBody MenuUpdateRequestDto updateRequestDto){
        menuService.menuUpdate(menuId, updateRequestDto);
        return ApiResTemplate.successWithContent(SuccessCode.MENU_UPDATE_SUCCESS);
    }
    //메뉴 id를 기준으로 사용자가 입력한 메뉴 삭제
    @DeleteMapping("/{menuId}")
    public ApiResTemplate<String> menuDelete(@PathVariable("menuId") Long menuId){
        menuService.menuDelete(menuId);
        return ApiResTemplate.successWithContent(SuccessCode.MENU_DELETE_SUCCESS);
    }
}
