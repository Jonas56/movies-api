package com.marsox.movies.service;

import com.marsox.movies.dto.UserDto;
import com.marsox.movies.model.User;
import com.marsox.movies.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public UserDto addNewUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return convertEntityToDto(userRepository.save(user));
    }

    private UserDto convertEntityToDto(User user) {
        return UserDto.build(
                user.getFullName(), user.getUsername(), user.getEmail(), user.getMovies()
        );
    }
}
