package com.marsox.movies.repository;

import com.marsox.movies.model.Director;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DirectorRepository extends JpaRepository<Director, Long> {

    Optional<Director> findByFullName(String fullName);
}
