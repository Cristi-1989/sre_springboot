import {EventEmitter, Component, Input, OnInit, Output} from '@angular/core';
import {DataService} from "../data.service";
import {MovieReview} from "../model/MovieReview";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";

@Component({
  selector: 'app-reviews',
  templateUrl: './reviews.component.html',
  styleUrls: ['./reviews.component.css']
})
export class ReviewsComponent implements OnInit {

  @Input()
  movieId: number;
  reviewForm : FormGroup;
  reviews: Array<MovieReview>;
  newReview = new MovieReview();

  @Output()
  dataChangedEvent = new EventEmitter<number>();

  constructor(private formBuilder: FormBuilder,
              private dataService: DataService) { }

  ngOnInit(): void {
    this.loadMovieReviews(this.movieId);
    this.initializeForm();
  }

  loadMovieReviews(movieId: number) {
      this.dataService.getMovieReviews(movieId)
        .subscribe(
          next => {
            this.reviews = next;
          }
        )
    }

  initializeForm() {
    this.reviewForm = this.formBuilder.group(
      {
        review: ['', Validators.required],
        rating: [10 , [Validators.required, Validators.min(0), Validators.max(10)]]
      }
    );
  }

  onSubmit() {
    let newReview = new MovieReview();
    newReview.review = this.reviewForm.controls['review'].value;
    newReview.rating = this.reviewForm.controls['rating'].value;
    newReview.movieId = this.movieId;
    this.dataService.addRating(newReview);
    this.loadMovieReviews(this.movieId);
    let movieId = this.movieId;
    this.dataChangedEvent.emit(movieId);
    this.initializeForm();
  }
}
