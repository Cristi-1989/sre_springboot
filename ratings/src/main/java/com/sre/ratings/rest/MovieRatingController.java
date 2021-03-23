package com.sre.ratings.rest;

import com.sre.ratings.RatingCalcService;
import com.sre.ratings.model.MovieRating;
import com.sre.ratings.repository.MovieRatingRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/ratings")
public class MovieRatingController {
    Logger logger = LogManager.getLogger(MovieRatingController.class);

    @Autowired
    MovieRatingRepository movieRatingRepository;

    @Autowired
    RatingCalcService ratingCalcService;



    @PostMapping
    public void addMovieRating(@RequestBody MovieRating movieRating) {
        Mono<MovieRating> movieRatings = movieRatingRepository.findByMovieId(movieRating.getMovieId());
        ratingCalcService.getUpdatedRating(movieRatings, Mono.just(movieRating))
                .flatMap(newRating -> {
                    MovieRating mr = new MovieRating();
                    mr.setMovieId(movieRating.getMovieId());
                    mr.setRating(newRating);
                    return  movieRatingRepository.save(mr);

                })
                .subscribe(res -> logger.info("Calculated a new rating for movie id {}, rating {}", res.getMovieId(), res.getRating()));
    }

    @GetMapping("{movieId}")
    Mono<MovieRating> getRating(@PathVariable("movieId") int movieId) {
        return movieRatingRepository.findByMovieId(movieId);
    }
}
