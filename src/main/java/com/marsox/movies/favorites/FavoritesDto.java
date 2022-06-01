package com.marsox.movies.favorites;

import com.marsox.movies.movie.MovieEssentialDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor(staticName = "build")
public class FavoritesDto {
    private String username;
    private Set<MovieEssentialDto> favorites;
}
