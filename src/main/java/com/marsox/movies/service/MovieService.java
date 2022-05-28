package com.marsox.movies.service;

import com.marsox.movies.model.Actor;
import com.marsox.movies.model.Director;
import com.marsox.movies.model.Movie;
import com.marsox.movies.repository.ActorRepository;
import com.marsox.movies.repository.DirectorRepository;
import com.marsox.movies.repository.MovieRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

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

    public List<Movie> getAllMovies() {
        return movieRepository.findAll();
    }

    public Movie addNewMovie(Movie m) {
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

        return movieRepository.save(m);
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

    public List<Movie> getMoviesByName(String movieName) {
        return movieRepository
                .findByName(movieName)
                .orElseThrow(() ->
                        new ResponseStatusException(HttpStatus.BAD_REQUEST,
                                "Movie with name : " + movieName + " not found!"
                        )
                );
    }

    public Movie updateMovie(Long movieId, Movie m) {
        boolean exists = movieRepository.existsById(movieId);

        if (!exists) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Movie with id : " + movieId + " not found!"
            );
        }

        return movieRepository.save(m);
    }

    public Movie getMovieById(Long id){
        return movieRepository.findById(id).orElseThrow(()->
            new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Movie with id : " + id + " not found!"
            )
        );
    }
}
