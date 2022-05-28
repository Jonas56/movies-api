package com.marsox.movies.controller;

import com.marsox.movies.model.Movie;
import com.marsox.movies.service.UserMoviesService;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Set;

@RestController
@RequestMapping(value = "/api/v1", produces = "application/json")
public class UserMoviesController {

    private final UserMoviesService userMoviesService;

    public UserMoviesController(UserMoviesService userMoviesService) {
        this.userMoviesService = userMoviesService;
    }

    @GetMapping("/favorites")
    public Set<Movie> getAllFavorites(HttpServletRequest request) {
        return userMoviesService.getAllFavorites(request);
    }

    @PostMapping("/favorites/{movieId}")
    public Set<Movie> addMovieToFavorites(HttpServletRequest request, @PathVariable Long movieId) {
        return userMoviesService.addToFavorites(request, movieId);
    }
}
