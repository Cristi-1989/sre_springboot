package com.devops.crud.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class MovieReviewService {
    @Autowired
    @Qualifier("reviewsRestTemplate")
    RestTemplate reviewsRestTemplate;

    @Value("${reviewsServiceUri}")
    private String reviewsServiceUri;

    public void saveReview(MovieReviewDTO reviewDTO) {
        ResponseEntity<MovieReviewDTO> result = reviewsRestTemplate.postForEntity(reviewsServiceUri + "/createReview", reviewDTO, MovieReviewDTO.class);
    }

    public Optional<List<MovieReviewDTO>> getMovieReviews(long movieId) {
        ResponseEntity<MovieReviewDTO[]> result = reviewsRestTemplate.getForEntity(reviewsServiceUri + "/reviews?movieId=" + movieId, MovieReviewDTO[].class);

        return Optional.ofNullable(Arrays.asList(result.getBody()));
    }

    public Optional<MovieReviewDTO> getMovieReviewById(long reviewId) {
        ResponseEntity<MovieReviewDTO> result = reviewsRestTemplate.getForEntity(reviewsServiceUri + "/review/" + reviewId, MovieReviewDTO.class);
        return Optional.ofNullable(result.getBody());
    }

    public void updateReview(MovieReviewDTO reviewDTO) {
        HttpEntity<MovieReviewDTO> entity = new HttpEntity<>(reviewDTO);
        reviewsRestTemplate.put(reviewsServiceUri + "/review/" + reviewDTO.getId(), entity, MovieReviewDTO.class);
    }

    public void deleteReview(MovieReviewDTO reviewDTO) {
        HttpEntity<MovieReviewDTO> entity = new HttpEntity<>(reviewDTO);
        reviewsRestTemplate.delete(reviewsServiceUri + "/review/" + reviewDTO.getId(), entity, MovieReviewDTO.class);
    }


}
