package com.marsox.movies.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor(staticName = "build")
public class MovieEssentialDto {
    private String name;
    private double imdbRating;
    private String trailerLink;
    private String genre;
    private Set<MovieImagesDto> movieImages;
}
