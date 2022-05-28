package com.marsox.movies.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor(staticName = "build")
public class ActorDto {
    private String fullName;
    private LocalDate bornDate;
}
