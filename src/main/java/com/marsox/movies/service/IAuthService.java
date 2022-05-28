package com.marsox.movies.service;

import com.marsox.movies.dto.UserDto;
import com.marsox.movies.model.User;

public interface IAuthService {
    UserDto addNewUser(User user);
}
