package com.marsox.movies.service;

import com.marsox.movies.dto.FavoritesDto;
import com.marsox.movies.dto.MovieEssentialDto;
import com.marsox.movies.dto.MovieImagesDto;
import com.marsox.movies.model.Movie;
import com.marsox.movies.model.MovieImage;
import com.marsox.movies.model.User;
import com.marsox.movies.repository.MovieRepository;
import com.marsox.movies.repository.UserRepository;
import com.marsox.movies.utils.JwtUtil;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserMoviesService {

    private final UserRepository userRepository;
    private final MovieRepository movieRepository;
    private final JwtUtil jwtUtil = new JwtUtil();

    public UserMoviesService(UserRepository userRepository, MovieRepository movieRepository) {
        this.userRepository = userRepository;
        this.movieRepository = movieRepository;
    }

    public FavoritesDto getUserFavorites(HttpServletRequest request) {
        Jws<Claims> claimsJWTs = jwtUtil.extractClaimsFromToken(request);
        Claims body = claimsJWTs.getBody();
        String username = body.getSubject();
        return convertEntityToFavoritesDto(userRepository.findByUsername(username));
    }

    public FavoritesDto addToFavorites(HttpServletRequest request, Long movieId) {
        Jws<Claims> claimsJWTs = jwtUtil.extractClaimsFromToken(request);
        Claims body = claimsJWTs.getBody();
        String username = body.getSubject();
        User user = userRepository.findByUsername(username);
        Movie movie = movieRepository.findById(movieId)
                .orElseThrow(()-> new IllegalStateException("Cannot find movie with id " + movieId));
        Set<Movie> movies = new HashSet<>() {{
            add(movie);
            addAll(user.getMovies());
        }};
        user.getMovies().add(new Movie(movieId));
        user.setMovies(movies);
        return convertEntityToFavoritesDto(userRepository.save(user));
    }

    private FavoritesDto convertEntityToFavoritesDto(User user){
        return FavoritesDto.build(
                user.getUsername(),
                user.getMovies().stream().map(this::convertEntityToMovieEssentialsDto).collect(Collectors.toSet())
        );
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
}
