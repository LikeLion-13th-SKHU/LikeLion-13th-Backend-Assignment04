package com.likelion.likelioncrud.ticket.application;

import com.likelion.likelioncrud.common.error.ErrorCode;
import com.likelion.likelioncrud.common.exception.BusinessException;
import com.likelion.likelioncrud.movie.domain.Movie;
import com.likelion.likelioncrud.movie.domain.repository.MovieRepository;
import com.likelion.likelioncrud.ticket.api.dto.request.TicketSaveRequestDto;
import com.likelion.likelioncrud.ticket.api.dto.request.TicketUpdateRequestDto;
import com.likelion.likelioncrud.ticket.api.dto.response.TicketInfoResponseDto;
import com.likelion.likelioncrud.ticket.api.dto.response.TicketListResponseDto;
import com.likelion.likelioncrud.ticket.domain.Ticket;
import com.likelion.likelioncrud.ticket.domain.repository.TicketRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class TicketService {

    private final MovieRepository movieRepository;
    private final TicketRepository ticketRepository;

    // 예매 등록
    @Transactional
    public void saveTicket(TicketSaveRequestDto dto) {
        Movie movie = movieRepository.findById(dto.movieId())
                .orElseThrow(() -> new BusinessException(ErrorCode.MOVIE_NOT_FOUND_EXCEPTION,
                        "영화 ID가 존재하지 않습니다: " + dto.movieId()));

        Ticket ticket = Ticket.builder()
                .movie(movie)
                .customerName(dto.customerName())
                .seatNumber(dto.seatNumber())
                .build();

        ticketRepository.save(ticket);
    }

    // 영화 기준 예매 조회
    public TicketListResponseDto findTicketsByMovie(Long movieId) {
        Movie movie = movieRepository.findById(movieId)
                .orElseThrow(() -> new BusinessException(ErrorCode.MOVIE_NOT_FOUND_EXCEPTION,
                        "영화 ID가 존재하지 않습니다: " + movieId));

        List<Ticket> tickets = ticketRepository.findByMovie(movie);
        List<TicketInfoResponseDto> responseDtos = tickets.stream()
                .map(TicketInfoResponseDto::from)
                .toList();

        return TicketListResponseDto.from(responseDtos);
    }

    // 예매 수정
    @Transactional
    public void updateTicket(Long ticketId, TicketUpdateRequestDto dto) {
        Ticket ticket = ticketRepository.findById(ticketId)
                .orElseThrow(() -> new BusinessException(ErrorCode.TICKET_NOT_FOUND_EXCEPTION,
                        "예매 ID가 존재하지 않습니다: " + ticketId));

        ticket.update(dto);
    }

    // 예매 삭제
    @Transactional
    public void deleteTicket(Long ticketId) {
        Ticket ticket = ticketRepository.findById(ticketId)
                .orElseThrow(() -> new BusinessException(ErrorCode.TICKET_NOT_FOUND_EXCEPTION,
                        "예매 ID가 존재하지 않습니다: " + ticketId));

        ticketRepository.delete(ticket);
    }
}
