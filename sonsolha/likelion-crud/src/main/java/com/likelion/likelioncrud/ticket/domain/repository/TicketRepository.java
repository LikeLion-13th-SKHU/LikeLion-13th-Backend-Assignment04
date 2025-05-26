package com.likelion.likelioncrud.ticket.domain.repository;

import com.likelion.likelioncrud.movie.domain.Movie;
import com.likelion.likelioncrud.ticket.domain.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TicketRepository extends JpaRepository<Ticket, Long> {
    List<Ticket> findByMovie(Movie movie);
}
