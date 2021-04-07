import { Injectable } from '@angular/core';
import {Observable, of} from "rxjs";
import {Movie} from "./model/Movie";
import {HttpClient} from '@angular/common/http';
import {environment} from "../environments/environment.prod";
import {map} from "rxjs/operators";
import {MovieReview} from "./model/MovieReview";
import {MovieRating} from "./model/MovieRating";

@Injectable({
  providedIn: 'root'
})
export class DataService {

  movies: Array<Movie>;
  movieReviews: Array<MovieReview>;

  constructor(private http: HttpClient) {
    const movie1 = new Movie();
    movie1.name = "Scary movie";
    movie1.description = "Parody, stupid comedy, blabla";
    movie1.id = 1;

    const movie2 = new Movie();
    movie2.name = "Upgrade";
    movie2.description = "Science fiction with Stem, a nanobot, the central character; He takes over control over a tetraplegic patient...";
    movie2.id = 2;
    this.movies = new Array<Movie>();
    this.movies.push(movie1);
    this.movies.push(movie2);

    const review1 = new MovieReview();
    review1.id = 1;
    review1.movieId = 1;
    review1.review = "Extraordinay, I would watch it 1000 times over again";
    review1.rating = 9;

    const review2 = new MovieReview();
    review2.id = 2;
    review2.movieId = 1;
    review2.review = "Superb, no wonder it's not on netflix, it s too good";
    review2.rating = 8;

    const review3 = new MovieReview();
    review3.id = 3;
    review3.movieId = 2;
    review3.review = "Piece of crap, it s very overrated. I wouldn't pay a movie theater ticket to go watch it";
    review3.rating = 5.5;

    this.movieReviews = new Array<MovieReview>();
    this.movieReviews.push(review1);
    this.movieReviews.push(review2);
    this.movieReviews.push(review3);

    console.log(environment.moviesServiceUrl);
  }


  getMovieReviews(movieId: number): Observable<Array<MovieReview>> {
    return this.http.get<Array<MovieReview>>(environment.movieReviewServiceUrl + "?movieId=" + movieId)
      .pipe(
        map( data => {
          const reviews = new Array<MovieReview>();
          for (const review of data) {
            reviews.push(MovieReview.fromHttp(review));
          }
          this.movieReviews = reviews;
          return reviews;
        })
      );

    // return of(this.movieReviews.filter(movieReview => movieReview.movieId == movieId));
  }

  getMovieRating(movieId: number): Observable<MovieRating> {
    return this.http.get<MovieRating>(environment.movieRatingServiceUrl + "/" + movieId)
      .pipe(
        map(data => {
          return MovieRating.fromHttp(data);
        })
      )
    // const movieRating: MovieRating = new MovieRating();
    // movieRating.movieId = movieId;
    // movieRating.rating = Math.trunc(Math.random() * Math.floor(10));
    // console.log("Calculating rating... {}", movieRating.rating);
    // return of(movieRating);
  }

  getMovies(): Observable<Array<Movie>> {
    console.log("Movies service URL is: " + environment.moviesServiceUrl);
    return this.http.get<Array<Movie>>(environment.moviesServiceUrl)
      .pipe(
        map( data => {
          const movies = new Array<Movie>();
          for (const movie of data) {
            movies.push(Movie.fromHttp(movie));
          }
          this.movies = movies;
          return movies;
        })
      );
  }

  getMovie(movieId: number) : Observable<Movie> {
    return of(this.movies.find(movie => movie.id === movieId));
  }
  addRating(movieReview: MovieReview) {
    const fullMovieReview = {movieId: movieReview.movieId, review: movieReview.review, rating: movieReview.rating};
    console.log(movieReview);
    return this.http.post<MovieReview>(environment.movieReviewServiceUrl, fullMovieReview)
      .subscribe(
        (data) => {

        },
        (error) => {
          console.log(error);
          // get the status as error.status
        })

    // const maxId = Math.max(...this.movieReviews
    //   .map(review => review.rating)
    //   );
    //
    // movieReview.id = maxId + 1;
    // this.movieReviews.push(movieReview);
  }
}
