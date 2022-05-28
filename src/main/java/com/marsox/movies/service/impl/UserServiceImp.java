package com.marsox.movies.service.impl;

import com.marsox.movies.dto.UserDto;
import com.marsox.movies.model.User;
import com.marsox.movies.repository.UserRepository;
import com.marsox.movies.service.IUserService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImp implements IUserService {

    private final UserRepository userRepository;

    public UserServiceImp(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<UserDto> getAllUsers() {
        return userRepository.findAll().stream().map(
                this::convertEntityToDto
        ).collect(Collectors.toList());
    }

    private UserDto convertEntityToDto(User user) {
        return UserDto.build(
                user.getFullName(), user.getUsername(), user.getEmail(), user.getMovies()
        );
    }
}
