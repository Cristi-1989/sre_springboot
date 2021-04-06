package com.sre.reviews.services;

import com.sre.reviews.controllers.MoviewReviewController;
import com.sre.reviews.dto.RatingsDTO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;

@Service
public class RatingsService {

    Logger logger = LogManager.getLogger(RatingsService.class);

    @Autowired
    private RestTemplate ratingsRestClient;

    @Value("${ratings.service.url}")
    private String ratingsServiceUri;

    @PostConstruct
    void printRatingsRemoteUrl() {
        logger.info("Ratings service is located at url: {}", ratingsServiceUri);
    }

    public void saveRating(RatingsDTO rating) {
        logger.info("Adding rating {} for movieId {}", rating.getRating(), rating.getMovieId());
        ratingsRestClient.put(ratingsServiceUri + "/api/ratings/" + rating.getMovieId(), rating, RatingsDTO.class);
    }
}
