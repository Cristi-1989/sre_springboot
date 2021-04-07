package com.sre.reviews.controllers;

import com.sre.reviews.dto.RatingsDTO;
import com.sre.reviews.model.MovieReview;
import com.sre.reviews.repo.MovieReviewRepository;
import com.sre.reviews.services.RatingsService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/reviews")
@CrossOrigin(origins = "http://localhost:4200")
public class MoviewReviewController {

    Logger logger = LogManager.getLogger(MoviewReviewController.class);

    @Autowired
    MovieReviewRepository movieReviewRepository;

    @Autowired
    RatingsService ratingsService;

    @GetMapping
    public List<MovieReview> getAllReviews(@RequestParam Optional<Long> movieId) {
        if (movieId.isPresent()) {
            return movieReviewRepository.findByMovieId(movieId.get());
        }
        return movieReviewRepository.findAll();
    }

    @GetMapping("/{id}")
    public MovieReview getReview(@PathVariable("id") long id) {
        return movieReviewRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid movieReview Id:" + id));
    }

//    @PutMapping("/{id}")
//    public void updateReview(@PathVariable("id") long id, @RequestBody MovieReview review) {
//        MovieReview movieReview = movieReviewRepository.findById(id)
//                .orElseThrow(() -> new IllegalArgumentException("Invalid movieReview Id:" + id));
//
//        movieReview.setMovieId(review.getMovieId());
//        movieReview.setRating(review.getRating());
//        movieReview.setReview(review.getReview());
//
//        movieReviewRepository.save(movieReview);
//    }

    @PostMapping
    public void createReview(@RequestBody MovieReview movieReview) {
        logger.info("Received new movie review: {}", movieReview);
        movieReviewRepository.save(movieReview);
        RatingsDTO ratingsDTO = new RatingsDTO(movieReview.getMovieId(), movieReview.getRating());
        ratingsService.saveRating(ratingsDTO);
    }


}
