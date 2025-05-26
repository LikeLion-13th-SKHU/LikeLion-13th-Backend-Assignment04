package com.likelion.likelioncrud.ticket.api;

import com.likelion.likelioncrud.common.error.SuccessCode;
import com.likelion.likelioncrud.common.template.ApiResTemplate;
import com.likelion.likelioncrud.ticket.api.dto.request.TicketSaveRequestDto;
import com.likelion.likelioncrud.ticket.api.dto.request.TicketUpdateRequestDto;
import com.likelion.likelioncrud.ticket.api.dto.response.TicketListResponseDto;
import com.likelion.likelioncrud.ticket.application.TicketService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/ticket")
public class TicketController {

    private final TicketService ticketService;

    // 예매 등록
    @PostMapping("/save")
    public ApiResTemplate<String> saveTicket(@RequestBody @Valid TicketSaveRequestDto ticketSaveRequestDto) {
        ticketService.saveTicket(ticketSaveRequestDto);
        return ApiResTemplate.successWithNoContent(SuccessCode.TICKET_SAVE_SUCCESS);
    }

    // 영화 ID 기준 예매 전체 조회
    @GetMapping("/{movieId}")
    public ApiResTemplate<TicketListResponseDto> getTicketsByMovie(@PathVariable("movieId") Long movieId) {
        TicketListResponseDto ticketList = ticketService.findTicketsByMovie(movieId);
        return ApiResTemplate.successResponse(SuccessCode.GET_SUCCESS, ticketList);
    }

    // 예매 수정
    @PatchMapping("/{ticketId}")
    public ApiResTemplate<String> updateTicket(
            @PathVariable("ticketId") Long ticketId,
            @RequestBody @Valid TicketUpdateRequestDto updateDto) {
        ticketService.updateTicket(ticketId, updateDto);
        return ApiResTemplate.successWithNoContent(SuccessCode.TICKET_UPDATE_SUCCESS);
    }

    // 예매 취소
    @DeleteMapping("/{ticketId}")
    public ApiResTemplate<String> deleteTicket(@PathVariable("ticketId") Long ticketId) {
        ticketService.deleteTicket(ticketId);
        return ApiResTemplate.successWithNoContent(SuccessCode.TICKET_DELETE_SUCCESS);
    }
}
