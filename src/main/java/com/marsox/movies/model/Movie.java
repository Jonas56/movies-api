package com.marsox.movies.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "movies")
@NoArgsConstructor
@Getter
@Setter
public class Movie implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull(message = "Movie name cannot be empty!")
    private String name;

    @NotEmpty(message = "Movie description cannot be empty")
    private String description;

    private double imdbRating;

    private String trailerLink;

    @NotNull(message = "Release date cannot be null")
    private LocalDate releaseDate;

    private String genre;

    private double budget;

    private Date createdAt;

    @Size(min=1, message = "You should provide at least one image")
    @OneToMany(targetEntity = MovieImage.class, cascade = CascadeType.ALL)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "movie_id", referencedColumnName = "id")
    private Set<MovieImage> movieImages;

    @NotNull(message = "You should provide at least one actor!")
    @ManyToMany(targetEntity = Actor.class, fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinTable(name = "actors_movies",
            joinColumns = @JoinColumn(name = "movie_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "actor_id", referencedColumnName = "id"))
    private List<Actor> actors;

    @NotNull(message = "Director field cannot be null!")
    @ManyToOne(targetEntity = Director.class, fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "director_id", referencedColumnName = "id")
    private Director director;


    public Movie(String name, String description, double imdbRating, String trailerLink, LocalDate realeaseDate, String genre, double budget) {
        this.name = name;
        this.description = description;
        this.imdbRating = imdbRating;
        this.trailerLink = trailerLink;
        this.releaseDate = realeaseDate;
        this.genre = genre;
        this.budget = budget;
    }

    public Movie(Long id) {
        this.id = id;
    }

    @PrePersist
    public void setCreatedAt() {
        this.createdAt = new Date();
    }


    @Override
    public String toString() {
        return "Movie{" +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", imdbRating=" + imdbRating +
                ", trailerLink='" + trailerLink + '\'' +
                ", releaseDate=" + releaseDate +
                ", genre='" + genre + '\'' +
                ", budget=" + budget +
                ", createdAt=" + createdAt +
                ", movieImages=" + movieImages +
                ", actors=" + actors +
                ", director=" + director +
                '}';
    }
}
