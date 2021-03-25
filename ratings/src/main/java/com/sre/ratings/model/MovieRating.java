package com.sre.ratings.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import javax.validation.constraints.NotNull;

@AllArgsConstructor(access = AccessLevel.PUBLIC)
@Getter
@Setter
public class MovieRating {
    @Id
    private long id;

    @NotNull(message = "movieId is mandatory")
    private long movieId;

    @NotNull(message = "rating is mandatory")
    private double rating;

    public MovieRating() {}

    public MovieRating(long movieId, double rating) {
        this.setMovieId(movieId);
        this.setRating(rating);
    }
}
