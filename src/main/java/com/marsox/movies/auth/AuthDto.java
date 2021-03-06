package com.marsox.movies.auth;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor(staticName = "build")
public class AuthDto {
    private String access_token;
    private String refresh_token;
}
