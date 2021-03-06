package com.marsox.movies.user;


import com.marsox.movies.movie.MovieEssentialDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor(staticName = "build")
public class UserDto {
    private String fullName;
    private String username;
    private String email;
    private Set<MovieEssentialDto> movies;
}
