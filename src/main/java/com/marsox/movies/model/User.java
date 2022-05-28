package com.marsox.movies.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.io.Serializable;
import java.util.Set;

@Data
@Entity
@Table(name = "users")
@NoArgsConstructor
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String fullName;
    @Column(unique = true)
    private String username;
    @Column(unique = true)
    @Email(message = "should be a valid email field")
    private String email;
    private String password;

    @ManyToMany(targetEntity = Movie.class, fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "user_movies",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "movie_id", referencedColumnName = "id"))
    private Set<Movie> movies;


    public User(String fullName, String username, String email, String password) {
        this.fullName = fullName;
        this.username = username;
        this.email = email;
        this.password = password;
    }

}
