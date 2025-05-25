package com.likelion.likelionassignmentcrud.delivery.api.dto;

import com.likelion.likelionassignmentcrud.common.error.SuccessCode;
import com.likelion.likelionassignmentcrud.common.template.ApiResTemplate;
import com.likelion.likelionassignmentcrud.delivery.api.dto.request.DeliverySaveRequestDto;
import com.likelion.likelionassignmentcrud.delivery.api.dto.request.DeliveryUpdateRequestDto;
import com.likelion.likelionassignmentcrud.delivery.api.dto.response.DeliveryInfoResponseDto;
import com.likelion.likelionassignmentcrud.delivery.api.dto.response.DeliveryListResponseDto;
import com.likelion.likelionassignmentcrud.delivery.application.DeliveryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/delivery")
public class DeliveryController {

    private final DeliveryService deliveryService;

    //주문 저장
    @PostMapping("/save")
    public ApiResTemplate<String> deliverySave(@RequestBody @Valid DeliverySaveRequestDto deliverySaveRequestDto){
        deliveryService.deliverySave(deliverySaveRequestDto);
        return ApiResTemplate.successWithNoContent(SuccessCode.DELIVERY_SAVE_SUCCESS);

    }


    //고객 id로 배달 내역 검색
    @GetMapping("/{clientId}")
    public ApiResTemplate<DeliveryListResponseDto> findMyAllDelivery(@PathVariable("clientId") Long clientId){
        DeliveryListResponseDto deliveryListResponseDto = deliveryService.deliveryFindByClientId(clientId);
        return ApiResTemplate.successResponse(SuccessCode.GET_SUCCESS, deliveryListResponseDto);
    }


    //배달 id로 배달 수정
    @PatchMapping("/{deliveryId}")
    public ApiResTemplate<String> deliveryUpdate(
            @PathVariable("deliveryId") Long deliveryId,
            @RequestBody @Valid DeliveryUpdateRequestDto deliveryUpdateRequestDto){
        deliveryService.deliveryUpdate(deliveryId, deliveryUpdateRequestDto);
        return ApiResTemplate.successWithNoContent(SuccessCode.DELIVERY_UPDATE_SUCCESS);
    }


    //배달 id로 배달 삭제
    @DeleteMapping("/{deliveryId}")
    public ApiResTemplate<String> deliveryDelete(
            @PathVariable("deliveryId") Long deliveryId
    ){
        deliveryService.deliveryDelete(deliveryId);
        return ApiResTemplate.successWithNoContent(SuccessCode.DELIVERY_DELETE_SUCCESS);
    }

}
