package com.sre.ratings.repository;

import com.sre.ratings.model.MovieRating;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface MovieRatingRepository extends R2dbcRepository<MovieRating, Long> {
    Mono<MovieRating> findByMovieId(long movieId);
}
