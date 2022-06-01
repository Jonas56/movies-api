package com.marsox.movies.user;

import com.marsox.movies.movie.Movie;
import com.marsox.movies.movie.MovieEssentialDto;
import com.marsox.movies.movie.MovieImage;
import com.marsox.movies.movie.MovieImagesDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImp implements IUserService {

    private final UserRepository userRepository;

    public UserServiceImp(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<UserDto> getAllUsers() {
        return userRepository.findAll().stream().map(
                this::convertEntityToDto
        ).collect(Collectors.toList());
    }

    private UserDto convertEntityToDto(User user) {
        return UserDto.build(
                user.getFullName(), user.getUsername(), user.getEmail(),
                user.getMovies().stream().map(this::convertEntityToMovieEssentialsDto).collect((Collectors.toSet()))
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
