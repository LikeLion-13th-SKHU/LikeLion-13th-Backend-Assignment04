package com.likelion.likelionassignmentcrud.delivery.application;

import com.likelion.likelionassignmentcrud.client.domain.Client;
import com.likelion.likelionassignmentcrud.client.domain.repository.ClientRepository;
import com.likelion.likelionassignmentcrud.common.error.ErrorCode;
import com.likelion.likelionassignmentcrud.common.exception.BusinessException;
import com.likelion.likelionassignmentcrud.delivery.api.dto.request.DeliverySaveRequestDto;
import com.likelion.likelionassignmentcrud.delivery.api.dto.request.DeliveryUpdateRequestDto;
import com.likelion.likelionassignmentcrud.delivery.api.dto.response.DeliveryInfoResponseDto;
import com.likelion.likelionassignmentcrud.delivery.api.dto.response.DeliveryListResponseDto;
import com.likelion.likelionassignmentcrud.delivery.domain.Delivery;
import com.likelion.likelionassignmentcrud.delivery.domain.repository.DeliveryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class DeliveryService {

    private final ClientRepository clientRepository;
    private final DeliveryRepository deliveryRepository;

    //주문저장
    @Transactional
    public void deliverySave(DeliverySaveRequestDto deliverySaveRequestDto){
        Client client = clientRepository.findById(deliverySaveRequestDto.clientId())
                .orElseThrow(IllegalArgumentException::new);

        Delivery delivery = Delivery.builder()
                .itemName(deliverySaveRequestDto.itemName())
                .deliveryStatus(deliverySaveRequestDto.deliveryStatus())
                .client(client)
                .build();

        deliveryRepository.save(delivery);

    }

    //고객 번호로 주문 조회
    public DeliveryListResponseDto deliveryFindByClientId(Long clientId){
        Client client = clientRepository.findById(clientId).orElseThrow( () -> new BusinessException(ErrorCode.DELIVERY_NOT_FOUND_EXCEPTION,
                ErrorCode.DELIVERY_NOT_FOUND_EXCEPTION.getMessage() + clientId));

        List<Delivery> deliveries = deliveryRepository.findByClient(client);
        List<DeliveryInfoResponseDto> deliveryListResponseDtos = deliveries.stream()
                .map(DeliveryInfoResponseDto::from)
                .toList();

        return DeliveryListResponseDto.from(deliveryListResponseDtos);

    }


    //주문 수정
    @Transactional
    public void deliveryUpdate(Long deliveryId, DeliveryUpdateRequestDto deliveryUpdateRequestDto) {
        Delivery delivery = deliveryRepository.findById(deliveryId)
                .orElseThrow(IllegalArgumentException::new);

        delivery.update(deliveryUpdateRequestDto);
    }


    // 주문 삭제
    @Transactional
    public void deliveryDelete(Long deliveryId) {
        Delivery delivery = deliveryRepository.findById(deliveryId)
                .orElseThrow(IllegalArgumentException::new);

        deliveryRepository.delete(delivery);
    }




}
