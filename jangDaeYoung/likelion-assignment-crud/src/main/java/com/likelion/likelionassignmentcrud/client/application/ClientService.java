package com.likelion.likelionassignmentcrud.client.application;


import com.likelion.likelionassignmentcrud.client.api.dto.request.ClientSaveRequestDto;
import com.likelion.likelionassignmentcrud.client.api.dto.request.ClientUpdateRequestDto;
import com.likelion.likelionassignmentcrud.client.api.dto.response.ClientInfoResponseDto;
import com.likelion.likelionassignmentcrud.client.api.dto.response.ClientListResponseDto;
import com.likelion.likelionassignmentcrud.client.domain.Client;
import com.likelion.likelionassignmentcrud.client.domain.repository.ClientRepository;
import com.likelion.likelionassignmentcrud.common.error.ErrorCode;
import com.likelion.likelionassignmentcrud.common.exception.BusinessException;
import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ClientService {

    private final ClientRepository clientRepository;

    //고객 정보 저장
    @Transactional
    public void clientSave(ClientSaveRequestDto clientSaveRequestDto){
        Client client = Client.builder()
                .name(clientSaveRequestDto.name())
                .age(clientSaveRequestDto.age())
                .payment(clientSaveRequestDto.payment())
                .build();

        clientRepository.save(client);
    }

    //전체 고객 조회
    public ClientListResponseDto clientFindAll(Pageable pageable){
        Page<Client> clients = clientRepository.findAll(pageable);
        List<ClientInfoResponseDto> clientInfoResponseDtoList = clients.stream()
                .map(ClientInfoResponseDto::from)
                .toList();
        return ClientListResponseDto.from(clientInfoResponseDtoList);
    }

    //단일 고객 조회
    public ClientInfoResponseDto clientFindById(Long clientId){
        Client client = clientRepository.findById(clientId)
                .orElseThrow(()-> new BusinessException(ErrorCode.CLIENT_NOT_FOUND_EXCEPTION, ErrorCode.CLIENT_NOT_FOUND_EXCEPTION.getMessage() + clientId));
        return ClientInfoResponseDto.from(client);
    }

    //고객 정보 수정
    @Transactional
    public void clientUpdate(Long clientId, ClientUpdateRequestDto clientUpdateRequestDto){
        Client client = clientRepository.findById(clientId) .orElseThrow(IllegalArgumentException::new);
        client.update(clientUpdateRequestDto);

    }

    // 고객 정보 삭제
    @Transactional
    public void clientDelete(Long memberId) {
        Client client = clientRepository.findById(memberId)
                .orElseThrow(IllegalArgumentException::new);

        clientRepository.delete(client);
    }



}
