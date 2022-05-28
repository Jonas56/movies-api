package com.marsox.movies.service;

import com.marsox.movies.dto.UserDto;

import java.util.List;

public interface IUserService {
    List<UserDto> getAllUsers();
}
