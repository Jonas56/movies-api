package com.marsox.movies.repository;

import com.marsox.movies.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {

//    @Query("SELECT m FROM Movie m WHERE m.name like %?1%")
    Optional<List<Movie>> findByName(String name);
}
