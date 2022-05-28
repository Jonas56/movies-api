package com.marsox.movies.service;

import com.marsox.movies.model.Movie;
import com.marsox.movies.model.User;
import com.marsox.movies.repository.MovieRepository;
import com.marsox.movies.repository.UserRepository;
import com.marsox.movies.utils.JwtUtil;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@Service
public class UserMoviesService {

    private final UserRepository userRepository;
    private final MovieRepository movieRepository;
    private final JwtUtil jwtUtil = new JwtUtil();

    public UserMoviesService(UserRepository userRepository, MovieRepository movieRepository) {
        this.userRepository = userRepository;
        this.movieRepository = movieRepository;
    }

    public Set<Movie> getAllFavorites(HttpServletRequest request) {
        Jws<Claims> claimsJWTs = jwtUtil.extractClaimsFromToken(request);
        Claims body = claimsJWTs.getBody();
        String username = body.getSubject();
        return userRepository.findByUsername(username)
                .getMovies();
    }

    public Set<Movie> addToFavorites(HttpServletRequest request, Long movieId) {
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
        return userRepository.save(user).getMovies();
    }
}
