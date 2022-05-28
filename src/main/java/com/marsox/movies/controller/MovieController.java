package com.marsox.movies.controller;

import com.marsox.movies.model.Movie;
import com.marsox.movies.service.MovieService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/movies", produces = "application/json")
//@CrossOrigin(origins = "*")
public class MovieController {

    private final MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping
    public ResponseEntity<List<Movie>> getMovies(@RequestParam(required = false) String name) {
        if (name == null) {
            return new ResponseEntity<>(movieService.getAllMovies(), HttpStatus.ACCEPTED);
        }
        return new ResponseEntity<>(movieService.getMoviesByName(name), HttpStatus.ACCEPTED);
    }

    @GetMapping("/{movieId}")
    public ResponseEntity<Movie> getMovieById(@PathVariable Long movieId) {
        return new ResponseEntity<>(movieService.getMovieById(movieId), HttpStatus.ACCEPTED);
    }

    @PostMapping
    public ResponseEntity<Movie> addNewMovie(@RequestBody @Valid Movie m) {
        return new ResponseEntity<>(movieService.addNewMovie(m), HttpStatus.CREATED);
    }

    @DeleteMapping("/{movieId}")
    public void deleteMovie(@PathVariable("movieId") Long movieId) {
        movieService.deleteMovie(movieId);
    }

    @PutMapping("/{movieId}")
    public Movie updateMovie(@PathVariable("movieId") Long movieId, @RequestBody Movie m) {
        return movieService.updateMovie(movieId, m);
    }

}
