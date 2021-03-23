package com.sre.ratings;

import com.sre.ratings.model.MovieRating;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.stream.Collectors;

@Service
public class RatingCalcService {
    public Mono<Double> getUpdatedRating(Mono<MovieRating> existingRating, Mono<MovieRating> newRating) {
        return Flux.concat(existingRating, newRating)
                .collect(Collectors.averagingDouble(rating -> rating.getRating()));

    }
}
