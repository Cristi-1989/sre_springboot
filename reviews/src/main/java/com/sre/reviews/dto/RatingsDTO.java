package com.sre.reviews.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor(access = AccessLevel.PUBLIC)
@Getter
@Setter
public class RatingsDTO {
    private long movieId;

    private double rating;
}
