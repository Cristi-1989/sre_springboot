import { Component, OnInit } from '@angular/core';
import {Router} from "@angular/router";

@Component({
  selector: 'app-menu',
  templateUrl: './menu.component.html',
  styleUrls: ['./menu.component.css']
})
export class MenuComponent implements OnInit {

  constructor(private router: Router) { }

  ngOnInit() {
  }


  navigateToHome() {
    this.router.navigate(['']);
  }

  navigateToMovies() {
    this.router.navigate(['movies']);
  }
}
