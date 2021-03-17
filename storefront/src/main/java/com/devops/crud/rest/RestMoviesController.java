package com.devops.crud.rest;

import com.devops.crud.entities.Movie;
import com.devops.crud.repositories.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/movies")
public class RestMoviesController {

    @Autowired
    private MovieRepository movieRepository;

    @GetMapping("/")
    public List<Movie> getAllMovies() {
        return movieRepository.findAll();
    }
}
