import { Injectable } from '@angular/core';
import {EventEmitter} from '@angular/core';
import {MovieReview} from "./model/MovieReview";

@Injectable({
  providedIn: 'root'
})
export class ResetFormService {

  constructor() { }

  resetReviewFormEvent = new EventEmitter<MovieReview>();
}
