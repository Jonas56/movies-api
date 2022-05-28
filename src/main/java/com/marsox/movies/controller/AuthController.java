package com.marsox.movies.controller;

import com.marsox.movies.dto.AuthDto;
import com.marsox.movies.dto.UserDto;
import com.marsox.movies.model.User;
import com.marsox.movies.service.IAuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@RestController
@RequestMapping(value = "/api/v1", produces = "application/json")
public class AuthController {

    private final IAuthService authService;

    public AuthController(IAuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/signup")
    public ResponseEntity<UserDto> register(@RequestBody @Valid User user) {
        return new ResponseEntity<>(authService.addNewUser(user), HttpStatus.CREATED);
    }

    @GetMapping("/refreshToken")
    public AuthDto refreshToken(HttpServletRequest request, HttpServletResponse response) {
        // TODO: Implement refresh token endpoint
//        String authorization = request.getHeader("Authorization");
//        if (!Strings.isEmpty(authorization) || authorization.startsWith("Bearer")) {
//            try {
//                String token = authorization.replace("Bearer ", "");
//                String secretKey = "mySecretsecretmySecretsecretmySecretsecretmySecretsecret";
//                Jws<Claims> claimsJwts = Jwts.parserBuilder()
//                        .setSigningKey(Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8)))
//                        .build()
//                        .parseClaimsJws(token);
//                Claims body = claimsJwts.getBody();
//                String username = body.getSubject();
//                Authentication authentication = new UsernamePasswordAuthenticationToken(username, null, new ArrayList<>());
//            } catch (JwtException e) {
//                throw new IllegalStateException("Token cannot be trusted");
//            }
//        }
        return null;
    }
}
