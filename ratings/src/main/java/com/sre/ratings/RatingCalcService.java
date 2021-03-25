package com.sre.ratings;

import com.sre.ratings.model.MovieRating;
import org.springframework.stereotype.Service;

@Service
public class RatingCalcService {

    public Double getUpdatedRating(MovieRating oldRating, MovieRating newRating) {
        return (oldRating.getRating() + newRating.getRating()) / 2;
    }
}
