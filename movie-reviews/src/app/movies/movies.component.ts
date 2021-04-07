import { Component, OnInit } from '@angular/core';
import {Movie} from "../model/Movie";
import {DataService} from "../data.service";
import {MovieReview} from "../model/MovieReview";
import {ResetFormService} from "../reset-form.service";
import {MovieRating} from "../model/MovieRating";

@Component({
  selector: 'app-movies',
  templateUrl: './movies.component.html',
  styleUrls: ['./movies.component.css']
})
export class MoviesComponent implements OnInit {

  movies: Array<Movie>;
  movieRatings = new Map<number, MovieRating>();

  message = 'Please wait... getting the list of rooms';
  loadingData = true;

  constructor(private dataService: DataService,
              private resetFormService: ResetFormService) { }

  ngOnInit(): void {
    this.loadData();
  }

  reloadData(movieId: number) {
    let movieRating = this.movieRatings.get(movieId);
    this.dataService.getMovieRating(movieId).subscribe(
      rating => movieRating = rating
    );

    console.log('movie id is: ' + movieId);
    this.movieRatings.set(movieId, movieRating);
}

  loadData(): void {
    this.dataService.getMovies().subscribe(
      (next) => {
        this.movies = next;
        this.loadingData = false;
      },
      (error) => {
        this.message = "Error loading movies...";
      }
    )

    for (let movie of this.movies) {
      let movieRating: MovieRating;
      this.dataService.getMovieRating(movie.id).subscribe(
        rating => movieRating = rating
      );
      this.movieRatings.set(movie.id, movieRating);
    }
  }

  // loadMovieReviews(movieId: number) {
  //   if (!this.movieReviews.has(movieId)) {
  //     this.dataService.getMovieReviews(movieId)
  //       .subscribe(
  //         next => {
  //           // this.movieReviews.set(movieId, next);
  //           this.resetFormService.resetReviewFormEvent.emit(new MovieReview());
  //         }
  //       )
  //   }
  // }




}
