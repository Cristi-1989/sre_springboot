package com.sre.reviews.controllers;

import com.sre.reviews.model.MovieReview;
import com.sre.reviews.repo.MovieReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class MoviewReviewController {

    @Autowired
    MovieReviewRepository movieReviewRepository;

    @GetMapping("/reviews")
    public List<MovieReview> getAllReviews(@RequestParam Optional<Long> movieId) {
        if (movieId.isPresent()) {
            return movieReviewRepository.findByMovieId(movieId.get());
        }
        return movieReviewRepository.findAll();
    }

    @GetMapping("/reviews/{id}")
    public MovieReview getReview(@PathVariable("id") long id) {
        return movieReviewRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid movieReview Id:" + id));
    }

    @PutMapping("/reviews/{id}")
    public void updateReview(@PathVariable("id") long id, @RequestBody MovieReview review) {
        MovieReview movieReview = movieReviewRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid movieReview Id:" + id));

        movieReview.setMovieId(review.getMovieId());
        movieReview.setRating(review.getRating());
        movieReview.setReview(review.getReview());

        movieReviewRepository.save(movieReview);
    }

    @PostMapping("/createReview")
    public void createReview(@RequestBody MovieReview movieReview) {
        movieReviewRepository.save(movieReview);
    }


}
