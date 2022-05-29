package com.marsox.movies.controller;

import com.marsox.movies.dto.FavoritesDto;
import com.marsox.movies.service.IFavoritesService;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping(value = "/api/v1", produces = "application/json")
public class FavoritesController {

    private final IFavoritesService userMoviesService;

    public FavoritesController(IFavoritesService userMoviesService) {
        this.userMoviesService = userMoviesService;
    }

    @GetMapping("/favorites")
    public FavoritesDto getUserFavorites(HttpServletRequest request) {
        return userMoviesService.getUserFavorites(request);
    }

    @PostMapping("/favorites/{movieId}")
    public FavoritesDto addMovieToFavorites(HttpServletRequest request, @PathVariable Long movieId) {
        return userMoviesService.addToFavorites(request, movieId);
    }
}
