package com.marsox.movies.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

@Data
@NoArgsConstructor
@AllArgsConstructor(staticName = "build")
public class UserRegistrationDto {
    @NotEmpty(message = "full name cannot be empty!")
    private String fullName;
    @NotEmpty(message = "username cannot be empty!")
    private String username;
    @Email(message = "email cannot be empty!")
    @NotEmpty(message = "email cannot be empty!")
    private String email;
    @NotEmpty(message = "password cannot be empty!")
    @Pattern(regexp = "^(?=.*?[A-Z])(?=(.*[a-z])+)(?=(.*[\\d])+)(?=(.*[\\W])+)(?!.*\\s).{8,}$",
            message = "Password must contain minimum eight characters. \n " +
                    "At least: one uppercase letter. \n " +
                    "One lowercase letter. \n " +
                    "One number and one special character"
    )
    private String password;
}
