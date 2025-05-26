package com.likelion.likelioncrud.movie.domain.repository;

import com.likelion.likelioncrud.movie.domain.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieRepository extends JpaRepository<Movie, Long> {
}