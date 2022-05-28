package com.marsox.movies.service;

import com.marsox.movies.dto.*;
import com.marsox.movies.model.Actor;
import com.marsox.movies.model.Director;
import com.marsox.movies.model.Movie;
import com.marsox.movies.model.MovieImage;
import com.marsox.movies.repository.ActorRepository;
import com.marsox.movies.repository.DirectorRepository;
import com.marsox.movies.repository.MovieRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MovieService {

    private final MovieRepository movieRepository;
    private final ActorRepository actorRepository;
    private final DirectorRepository directorRepository;

    public MovieService(MovieRepository movieRepository, ActorRepository actorRepository, DirectorRepository directorRepository) {
        this.movieRepository = movieRepository;
        this.actorRepository = actorRepository;
        this.directorRepository = directorRepository;
    }

    public List<MovieEssentialDto> getAllMovies() {
        return movieRepository.findAll().stream()
                .map(this::convertEntityToMovieEssentialsDto).collect(Collectors.toList());
    }

    public MovieDto addNewMovie(Movie m) {
        for (int i = 0; i < m.getActors().size(); i++) {
            Actor actor = m.getActors().get(i);
            Optional<Actor> actorExist =
                    actorRepository.findActorByFullName(
                            actor.getFullName()
                    );
            int finalI = i;
            actorExist.ifPresent(value -> {
                m.getActors().set(finalI, actorExist.get());
            });
        }
        Director director = m.getDirector();
        directorRepository.findByFullName(director.getFullName())
                .ifPresent(m::setDirector);

        return convertEntityToMovieDto(movieRepository.save(m));
    }

    public void deleteMovie(Long movieId) {
        boolean exists = movieRepository.existsById(movieId);
        if (!exists) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Movie with id : " + movieId + " not found!"
            );
        }
        movieRepository.deleteById(movieId);
    }

    public List<MovieEssentialDto> getMoviesByName(String movieName) {
        return movieRepository
                .findByName(movieName)
                .orElseThrow(() ->
                        new ResponseStatusException(HttpStatus.BAD_REQUEST,
                                "Movie with name : " + movieName + " not found!"
                        )
                ).stream().map(this::convertEntityToMovieEssentialsDto).collect(Collectors.toList());
    }

    public MovieDto updateMovie(Long movieId, Movie m) {
        boolean exists = movieRepository.existsById(movieId);

        if (!exists) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Movie with id : " + movieId + " not found!"
            );
        }

        return convertEntityToMovieDto(movieRepository.save(m));
    }

    public MovieDto getMovieById(Long id) {
        return convertEntityToMovieDto(movieRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.BAD_REQUEST,
                        "Movie with id : " + id + " not found!"
                )
        ));
    }

    private MovieEssentialDto convertEntityToMovieEssentialsDto(Movie movie) {
        return MovieEssentialDto.build(
                movie.getName(), movie.getImdbRating(),
                movie.getTrailerLink(), movie.getGenre(),
                movie.getMovieImages().stream().map(this::convertEntityToMovieImagesDto).collect(Collectors.toSet())
        );
    }

    private MovieImagesDto convertEntityToMovieImagesDto(MovieImage movieImage) {
        return MovieImagesDto.build(movieImage.getLink());
    }

    private ActorDto convertEntityToActorDto(Actor actor) {
        return ActorDto.build(actor.getFullName(), actor.getBornDate());
    }

    private DirectorDto convertEntityToDirectorDto(Director director) {
        return DirectorDto.build(director.getFullName(), director.getBornDate());
    }

    private MovieDto convertEntityToMovieDto(Movie movie) {
        return MovieDto.build(
                movie.getId(), movie.getName(), movie.getDescription(), movie.getImdbRating(), movie.getTrailerLink(),
                movie.getReleaseDate(), movie.getGenre(), movie.getBudget(),
                movie.getMovieImages().stream().map(this::convertEntityToMovieImagesDto).collect(Collectors.toSet()),
                movie.getActors().stream().map(this::convertEntityToActorDto).collect(Collectors.toList()),
                this.convertEntityToDirectorDto(movie.getDirector())
        );
    }
}
