package com.marsox.movies.actor;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ActorRepository extends JpaRepository<Actor, Long> {

    Optional<Actor> findActorByFullName(String fullName);
}
