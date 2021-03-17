export class MovieRating {
  movieId: number;
  rating: number;

  static fromHttp(rating: MovieRating): MovieRating {
    const movieRating = new MovieRating();
    movieRating.movieId = rating.movieId;
    movieRating.rating = rating.rating;

    return movieRating;
  }
}
