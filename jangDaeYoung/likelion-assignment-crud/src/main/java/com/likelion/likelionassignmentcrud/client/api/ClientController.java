package com.likelion.likelionassignmentcrud.client.api;




import com.likelion.likelionassignmentcrud.client.api.dto.request.ClientSaveRequestDto;
import com.likelion.likelionassignmentcrud.client.api.dto.request.ClientUpdateRequestDto;
import com.likelion.likelionassignmentcrud.client.api.dto.response.ClientInfoResponseDto;
import com.likelion.likelionassignmentcrud.client.api.dto.response.ClientListResponseDto;
import com.likelion.likelionassignmentcrud.client.application.ClientService;
import com.likelion.likelionassignmentcrud.common.error.SuccessCode;
import com.likelion.likelionassignmentcrud.common.template.ApiResTemplate;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/client")
public class ClientController {

    private final ClientService clientService;

    // 고객 저장
    @PostMapping("/save")
    public ApiResTemplate<String> clientSave(@RequestBody @Valid ClientSaveRequestDto clientSaveRequestDto){
        clientService.clientSave(clientSaveRequestDto);
        return ApiResTemplate.successWithNoContent(SuccessCode.CLIENT_SAVE_SUCCESS);
    }

    // 전체 고객 조회
    //페이지네이션 구현
    @GetMapping("/all")
    public  ApiResTemplate<ClientListResponseDto> clientFindAll(
            @PageableDefault(size = 5, sort = "clientId", direction = Sort.Direction.ASC) Pageable pageable
            ){
        ClientListResponseDto clientListResponseDto = clientService.clientFindAll(pageable);
        return ApiResTemplate.successResponse(SuccessCode.GET_SUCCESS, clientListResponseDto);
    }


    // 고객id로 특정 고객 조회
    @GetMapping("/{clientId}")
    public  ApiResTemplate<ClientInfoResponseDto> clientFindById(@PathVariable("clientId") Long clientId){
        ClientInfoResponseDto clientInfoResponseDto = clientService.clientFindById(clientId);
        return ApiResTemplate.successResponse(SuccessCode.GET_SUCCESS, clientInfoResponseDto);
    }

    //고객 id로 고객 정보 수정
    @PatchMapping("/{clientId}")
    public ApiResTemplate<String> clientUpdate(
            @PathVariable("clientId") Long clientId,
            @RequestBody @Valid ClientUpdateRequestDto clientUpdateRequestDto){
        clientService.clientUpdate(clientId, clientUpdateRequestDto);
        return ApiResTemplate.successWithNoContent(SuccessCode.CLIENT_UPDATE_SUCCESS);
    }


    //고개 id를 통한 고객 삭제
    @DeleteMapping("/{clientId}")
    public ApiResTemplate<String> clientDelete(
            @PathVariable("clientId") Long clientId
    ){
        clientService.clientDelete(clientId);
        return ApiResTemplate.successWithNoContent(SuccessCode.CLIENT_DELETE_SUCCESS);
    }


}
