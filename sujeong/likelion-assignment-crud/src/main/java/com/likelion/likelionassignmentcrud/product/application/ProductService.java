package com.likelion.likelionassignmentcrud.product.application;

import java.util.List;

import com.likelion.likelionassignmentcrud.common.error.ErrorCode;
import com.likelion.likelionassignmentcrud.common.exception.BusinessException;
import com.likelion.likelionassignmentcrud.product.api.dto.request.ProductSaveRequestDto;
import com.likelion.likelionassignmentcrud.product.api.dto.request.ProductUpdateRequestDto;
import com.likelion.likelionassignmentcrud.product.api.dto.response.ProductInfoResponseDto;
import com.likelion.likelionassignmentcrud.product.api.dto.response.ProductListResponseDto;
import com.likelion.likelionassignmentcrud.product.domain.Product;
import com.likelion.likelionassignmentcrud.product.domain.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ProductService {
    private final ProductRepository productRepository;

    // 제품 정보 저장
    @Transactional
    public void productSave(ProductSaveRequestDto productSaveRequestDto) {
        Product product = Product.builder()
                .name(productSaveRequestDto.name())
                .price(productSaveRequestDto.price())
                .part(productSaveRequestDto.part())
                .build();
        productRepository.save(product);
    }

    // 제품 모두 조회
    public ProductListResponseDto productFindAll(Pageable pageable) {
        Page<Product> products = productRepository.findAll(pageable); // 기존 List -> Page
        List<ProductInfoResponseDto> productInfoResponseDtoList = products.stream()
                .map(ProductInfoResponseDto::from)
                .toList();
        return ProductListResponseDto.from(productInfoResponseDtoList);
    }

    // 단일 제품 조회
    public ProductInfoResponseDto productFindOne(Long productId) {
        Product product = productRepository
                .findById(productId)
                .orElseThrow(
                        () -> new BusinessException(ErrorCode.PRODUCT_NOT_FOUND_EXCEPTION,
                                ErrorCode.PRODUCT_NOT_FOUND_EXCEPTION.getMessage() + productId)
                );
        return ProductInfoResponseDto.from(product);
    }

    // 제품 정보 수정
    @Transactional
    public void productUpdate(Long productId,
                              ProductUpdateRequestDto productUpdateRequestDto)
            {Product product = productRepository.findById(productId)
                    .orElseThrow(
                            () -> new BusinessException(ErrorCode.PRODUCT_NOT_FOUND_EXCEPTION,
                                ErrorCode.PRODUCT_NOT_FOUND_EXCEPTION.getMessage() + productId)
                    );
            product.update(productUpdateRequestDto);
    }

    // 제품 정보 삭제
    @Transactional
    public void productDelete(Long productId) {
        Product product = productRepository.findById(productId)
                    .orElseThrow(
                            () -> new BusinessException(ErrorCode.PRODUCT_NOT_FOUND_EXCEPTION,
                                ErrorCode.PRODUCT_NOT_FOUND_EXCEPTION.getMessage() + productId)
                    );
        productRepository.delete(product);
    }
}
