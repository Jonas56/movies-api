package com.marsox.movies.movie;

import com.marsox.movies.actor.Actor;
import com.marsox.movies.actor.ActorDto;
import com.marsox.movies.actor.ActorRepository;
import com.marsox.movies.director.Director;
import com.marsox.movies.director.DirectorDto;
import com.marsox.movies.director.DirectorRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MovieServiceImp implements IMovieService {

    private final MovieRepository movieRepository;
    private final ActorRepository actorRepository;
    private final DirectorRepository directorRepository;

    public MovieServiceImp(MovieRepository movieRepository, ActorRepository actorRepository, DirectorRepository directorRepository) {
        this.movieRepository = movieRepository;
        this.actorRepository = actorRepository;
        this.directorRepository = directorRepository;
    }

    public List<MovieEssentialDto> getAllMovies() {
        return movieRepository.findAll().stream()
                .map(this::convertEntityToMovieEssentialsDto).collect(Collectors.toList());
    }

    public MovieResponseDto addNewMovie(Movie m) {
        // TODO: Update logic
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

    public MovieResponseDto updateMovie(Long movieId, Movie m) {
        boolean exists = movieRepository.existsById(movieId);

        if (!exists) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Movie with id : " + movieId + " not found!"
            );
        }

        return convertEntityToMovieDto(movieRepository.save(m));
    }

    public MovieResponseDto getMovieById(Long id) {
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

    private MovieResponseDto convertEntityToMovieDto(Movie movie) {
        return MovieResponseDto.build(
                movie.getId(), movie.getName(), movie.getDescription(), movie.getImdbRating(), movie.getTrailerLink(),
                movie.getReleaseDate(), movie.getGenre(), movie.getBudget(),
                movie.getMovieImages().stream().map(this::convertEntityToMovieImagesDto).collect(Collectors.toSet()),
                movie.getActors().stream().map(this::convertEntityToActorDto).collect(Collectors.toList()),
                this.convertEntityToDirectorDto(movie.getDirector())
        );
    }
}
