package com.marsox.movies.auth;

import com.marsox.movies.user.User;
import com.marsox.movies.user.UserDto;

public interface IAuthService {
    UserDto addNewUser(User user);
}
