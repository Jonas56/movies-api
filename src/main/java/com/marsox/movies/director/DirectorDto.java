package com.marsox.movies.director;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor(staticName = "build")
@NoArgsConstructor
public class DirectorDto {
    private String fullName;
    private LocalDate bornDate;
}
