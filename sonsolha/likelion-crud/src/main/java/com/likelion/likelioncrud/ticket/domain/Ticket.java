package com.likelion.likelioncrud.ticket.domain;

import com.likelion.likelioncrud.movie.domain.Movie;
import com.likelion.likelioncrud.ticket.api.dto.request.TicketUpdateRequestDto;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ticket_id")
    private Long id;

    private String customerName;

    private String seatNumber;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "movie_id")
    private Movie movie;

    @Builder
    private Ticket(String customerName, String seatNumber, Movie movie) {
        this.customerName = customerName;
        this.seatNumber = seatNumber;
        this.movie = movie;
    }

    public void update(TicketUpdateRequestDto dto) {
        this.customerName = dto.customerName();
        this.seatNumber = dto.seatNumber();
    }
}
