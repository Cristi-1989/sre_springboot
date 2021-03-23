package com.sre.ratings.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import javax.validation.constraints.NotNull;

@AllArgsConstructor(access = AccessLevel.PUBLIC)
@NoArgsConstructor
@Getter
@Setter
public class MovieRating {
    @Id
    @NotNull(message = "movieId is mandatory")
    private long movieId;

    @NotNull(message = "rating is mandatory")
    private double rating;
}
