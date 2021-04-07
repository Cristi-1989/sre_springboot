export class MovieReview {
  id: number;
  movieId: number;
  review: string;
  rating: number;

  static fromHttp(review : MovieReview) {
    const newReview = new MovieReview();
    newReview.id = review.id;
    newReview.movieId = review.movieId;
    newReview.review = review.review;
    newReview.rating = review.rating

    return newReview;
  }

}
