package com.marsox.movies.movie;

import com.marsox.movies.actor.ActorDto;
import com.marsox.movies.director.DirectorDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor(staticName = "build")
public class MovieResponseDto {
    private Long id;
    private String name;
    private String description;
    private double imdbRating;
    private String trailerLink;
    private LocalDate releaseDate;
    private String genre;
    private double budget;
    private Set<MovieImagesDto> movieImages;
    private List<ActorDto> actors;
    private DirectorDto director;
}
