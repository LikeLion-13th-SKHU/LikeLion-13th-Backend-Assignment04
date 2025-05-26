package com.likelion.likelionassignmentcrud.product.api;

import com.likelion.likelionassignmentcrud.common.error.SuccessCode;
import com.likelion.likelionassignmentcrud.common.template.ApiResTemplate;
import com.likelion.likelionassignmentcrud.product.api.dto.request.ProductSaveRequestDto;
import com.likelion.likelionassignmentcrud.product.api.dto.request.ProductUpdateRequestDto;
import com.likelion.likelionassignmentcrud.product.api.dto.response.ProductInfoResponseDto;
import com.likelion.likelionassignmentcrud.product.api.dto.response.ProductListResponseDto;
import com.likelion.likelionassignmentcrud.product.application.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/product")
public class ProductController {

    private final ProductService productService;

    // 제품 저장
    @PostMapping("/save")
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResTemplate<String> productSave(@RequestBody ProductSaveRequestDto productSaveRequestDto) {
        productService.productSave(productSaveRequestDto);
        return ApiResTemplate.successWithNoContent(SuccessCode.PRODUCT_SAVE_SUCCESS);
    }

    // 제품 전체 조회
    @GetMapping("/all")
    public ApiResTemplate<ProductListResponseDto> productFindAll(
            @PageableDefault(size = 10, sort = "productId", direction = Sort.Direction.ASC)
            Pageable pageable) {
        ProductListResponseDto productListResponseDto = productService.productFindAll(pageable);
        return ApiResTemplate.successResponse(SuccessCode.GET_SUCCESS, productListResponseDto);
    }

    // 회원 id를 통해 특정 제품 조회
    @GetMapping("/{productId}")
    public ApiResTemplate<ProductInfoResponseDto> productFindOne(@PathVariable("productId") Long productId) {
        ProductInfoResponseDto productInfoResponseDto = productService.productFindOne(productId);
        return ApiResTemplate.successResponse(SuccessCode.GET_SUCCESS, productInfoResponseDto);
    }

    // 회원 id를 통한 제품 수정
    @PatchMapping("/{productId}")
    public ApiResTemplate<String> productUpdate(
            @PathVariable("productId") Long productId,
            @RequestBody ProductUpdateRequestDto productUpdateRequestDto) {
        productService.productUpdate(productId, productUpdateRequestDto);
        return ApiResTemplate.successWithNoContent(SuccessCode.PRODUCT_UPDATE_SUCCESS);
    }

    // 회원 id를 통한 제품 삭제
    @DeleteMapping("/{productId}")
    public ApiResTemplate<String> productDelete(
            @PathVariable("productId") Long productId) {
        productService.productDelete(productId);
        return ApiResTemplate.successWithNoContent(SuccessCode.PRODUCT_DELETE_SUCCESS);
    }
}
