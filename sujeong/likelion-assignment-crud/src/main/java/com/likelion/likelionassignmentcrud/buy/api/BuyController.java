package com.likelion.likelionassignmentcrud.buy.api;

import com.likelion.likelionassignmentcrud.common.error.SuccessCode;
import com.likelion.likelionassignmentcrud.common.template.ApiResTemplate;
import com.likelion.likelionassignmentcrud.buy.api.dto.request.BuySaveRequestDto;
import com.likelion.likelionassignmentcrud.buy.api.dto.request.BuyUpdateRequestDto;
import com.likelion.likelionassignmentcrud.buy.api.dto.response.BuyListResponseDto;
import com.likelion.likelionassignmentcrud.buy.application.BuyService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/buy")
public class BuyController {
    private final BuyService buyService;

    // 구매 목록 저장
    @PostMapping("/save")
    public ApiResTemplate<String> buySave(@RequestBody @Valid BuySaveRequestDto buySaveRequestDto) {
        buyService.buySave(buySaveRequestDto);
        return ApiResTemplate.successWithNoContent(SuccessCode.BUY_CREATE_SUCCESS);
    }

    // 제품 id를 기준으로 해당 제품 구매 목록 조회
    @GetMapping("/{productId}")
    public ApiResTemplate<BuyListResponseDto> myBuyFindAll(@PathVariable("productId") Long productId) {
        BuyListResponseDto buyListResponseDto = buyService.buyFindProduct(productId);
        return ApiResTemplate.successResponse(SuccessCode.GET_SUCCESS, buyListResponseDto);
    }

    // 구매 목록 id를 기준으로 제품 구매 목록 수정
    @PatchMapping("/{buyId}")
    public ApiResTemplate<String> buyUpdate(@PathVariable("buyId") Long buyId,
                                             @RequestBody BuyUpdateRequestDto buyUpdateRequestDto) {
        buyService.buyUpdate(buyId, buyUpdateRequestDto);
        return ApiResTemplate.successWithNoContent(SuccessCode.BUY_UPDATE_SUCCESS);
    }

    // 구매 목록 id를 기준으로 제품 구매 목록 삭제
    @DeleteMapping("/{buyId}")
    public ApiResTemplate<String> buyDelete(@PathVariable("buyId") Long buyId) {
        buyService.buyDelete(buyId);
        return ApiResTemplate.successWithNoContent(SuccessCode.BUY_DELETE_SUCCESS);
    }
}