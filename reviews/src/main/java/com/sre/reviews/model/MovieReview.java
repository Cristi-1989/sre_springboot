package com.sre.reviews.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
public class MovieReview {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull(message = "movieId is mandatory")
    private long movieId;

    @NotNull(message = "rating is mandatory")
    private double rating;

    @NotBlank(message = "review is mandatory")
    private String review;

    public MovieReview( @NotBlank(message = "movieId is mandatory") long movieId, @NotBlank(message = "review is mandatory") String review,  @NotBlank(message = "rating is mandatory") double rating) {
        this.movieId = movieId;
        this.review = review;
        this.rating = rating;
    }

    public MovieReview() { }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public long getMovieId() {
        return movieId;
    }

    public void setMovieId(long movieId) {
        this.movieId = movieId;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }
}
