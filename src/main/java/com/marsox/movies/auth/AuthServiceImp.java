package com.marsox.movies.auth;

import com.marsox.movies.user.User;
import com.marsox.movies.user.UserDto;
import com.marsox.movies.user.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashSet;

@Service
public class AuthServiceImp implements IAuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthServiceImp(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public UserDto addNewUser(User user) {
        boolean userExists = userRepository.existsByUsernameOrEmail(user.getUsername(), user.getEmail());
        if (userExists){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User already exists");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return convertEntityToDto(userRepository.save(user));
    }

    private UserDto convertEntityToDto(User user) {
        return UserDto.build(
                user.getFullName(), user.getUsername(), user.getEmail(), new HashSet<>()
        );
    }
}
