package com.marsox.movies.movie;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

@Entity
@Table(name = "movie_images")
@NoArgsConstructor
@Getter
@Setter
public class MovieImage implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotEmpty
    private String link;

    public MovieImage(String link) {
        this.link = link;
    }


}
