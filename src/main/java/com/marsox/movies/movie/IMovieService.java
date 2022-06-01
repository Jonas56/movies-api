package com.marsox.movies.movie;

import java.util.List;

public interface IMovieService {

    List<MovieEssentialDto> getAllMovies();

    MovieResponseDto addNewMovie(Movie m);

    void deleteMovie(Long movieId);

    List<MovieEssentialDto> getMoviesByName(String movieName);

    MovieResponseDto updateMovie(Long movieId, Movie m);

    MovieResponseDto getMovieById(Long id);
}
