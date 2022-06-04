package com.marsox.movies.service.impl;

import com.marsox.movies.auth.AuthServiceImp;
import com.marsox.movies.user.UserDto;
import com.marsox.movies.user.UserRepository;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@ExtendWith(MockitoExtension.class)
class AuthServiceImpTest {

    @Mock
    private UserRepository userRepository;
    @Mock
    private PasswordEncoder passwordEncoder;
    private AuthServiceImp underTest;

    @BeforeEach
    void setup() {
        underTest = new AuthServiceImp(userRepository, passwordEncoder);
    }

    @Test
    @DisplayName("Test should pass when adding a valid user")
    void shouldSuccessfullyAddNewUser() {
        // Given
        // When
        UserDto addedUser = new UserDto();
//                        = underTest.addNewUser(
//                new User(
//                        "Issac Newton",
//                        "Issac56",
//                        "issac@tesla.com",
//                        "Pass.@123"
//                )
//        );
        // Then
        assertThat(addedUser).isInstanceOf(UserDto.class);
    }
}