package com.marsox.movies.config;

import com.marsox.movies.actor.Actor;
import com.marsox.movies.auth.IAuthService;
import com.marsox.movies.director.Director;
import com.marsox.movies.movie.IMovieService;
import com.marsox.movies.movie.Movie;
import com.marsox.movies.movie.MovieImage;
import com.marsox.movies.user.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;

@Configuration
public class LoadDatabase {

    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

    @Bean
    @Profile("!prod")
    CommandLineRunner initDatabase(IAuthService authService, IMovieService movieService) {
        Movie m = new Movie();
        m.setName("Reality Bites");
        m.setDescription("A romantic comedy centred on an aspiring videographer working on a documentary, about the disenfranchised lives of her friends, room-mates and herself.");
        m.setBudget(33.4);
        m.setGenre("Romance/Drama");
        LocalDate releaseDate = LocalDate.of(1994, 4, 13);
        m.setReleaseDate(releaseDate);
        m.setMovieImages(new HashSet<>(){{
            add(new MovieImage("image_link"));
        }});
        Actor actor = new Actor();
        actor.setFullName("Winona Ryder");
        actor.setBornDate(LocalDate.of(1990, 4, 13));
        actor.setBiography("Biography");
        m.setActors(List.of(actor));
        Director director = new Director("Ben Stiller","Biography",LocalDate.of(1965, 11, 30));
        m.setDirector(director);

        return args -> {
            log.info("Preloading " + authService.addNewUser(new User("Jonas", "Jonas56", "jonas@tesla.com", "Jonas.@123")));
            log.info("Preloading " + movieService.addNewMovie(m));
        };
    }
}
