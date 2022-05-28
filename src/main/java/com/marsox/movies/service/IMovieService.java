package com.marsox.movies.service;

import com.marsox.movies.dto.MovieDto;
import com.marsox.movies.dto.MovieEssentialDto;
import com.marsox.movies.model.Movie;

import java.util.List;

public interface IMovieService {

    List<MovieEssentialDto> getAllMovies();

    MovieDto addNewMovie(Movie m);

    void deleteMovie(Long movieId);

    List<MovieEssentialDto> getMoviesByName(String movieName);

    MovieDto updateMovie(Long movieId, Movie m);

    MovieDto getMovieById(Long id);
}
