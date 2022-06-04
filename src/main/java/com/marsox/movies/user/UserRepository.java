package com.marsox.movies.user;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByUsername(String username);
    Boolean existsByUsernameOrEmail(String username, String email);
}
