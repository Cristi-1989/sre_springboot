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
@CrossOrigin(origins = "http://localhost:4200")
public class MovieRatingController {
    Logger logger = LogManager.getLogger(MovieRatingController.class);

    @Autowired
    MovieRatingRepository movieRatingRepository;

    @Autowired
    RatingCalcService ratingCalcService;

    @PutMapping("{movieId}")
    public void addMovieRating(@PathVariable int movieId, @RequestBody MovieRating movieRating) {
        movieRatingRepository.findByMovieId(movieId)
                .flatMap(old -> {
                    movieRating.setRating(ratingCalcService.getUpdatedRating(old, movieRating));
                    movieRating.setMovieId(movieId);
                    movieRating.setId(old.getId());
                    return movieRatingRepository.save(movieRating);
                })
                .switchIfEmpty(movieRatingRepository.save(new MovieRating(movieId, movieRating.getRating())))
                .subscribe(res -> logger.info("Calculated a new rating for movie id {}, rating {}", res.getMovieId(), res.getRating()));
    }

    @GetMapping("{movieId}")
    Mono<MovieRating> getRating(@PathVariable("movieId") int movieId) {
        return movieRatingRepository.findByMovieId(movieId);
    }
}
