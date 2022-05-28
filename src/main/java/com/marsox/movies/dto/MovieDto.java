package com.marsox.movies.dto;

import com.marsox.movies.model.Director;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor(staticName = "build")
public class MovieDto {
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
