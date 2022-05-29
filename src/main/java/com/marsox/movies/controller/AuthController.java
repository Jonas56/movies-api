package com.marsox.movies.controller;

import com.marsox.movies.dto.AuthDto;
import com.marsox.movies.dto.UserDto;
import com.marsox.movies.dto.UserRegistrationDto;
import com.marsox.movies.model.User;
import com.marsox.movies.service.IAuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.modelmapper.ModelMapper;
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
    private final ModelMapper modelMapper;

    public AuthController(IAuthService authService, ModelMapper modelMapper) {
        this.authService = authService;
        this.modelMapper = modelMapper;
    }

    @PostMapping("/signup")
    @Operation(summary = "Register new User")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Successfully registered",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = UserDto.class))}),
            @ApiResponse(responseCode = "400", description = "Unable to signup", content = {
                    @Content(mediaType = "application/json")
            }),
    })
    public ResponseEntity<UserDto> register(@RequestBody @Valid UserRegistrationDto userRegistrationDto) {
        User user = modelMapper.map(userRegistrationDto, User.class);
        return new ResponseEntity<>(authService.addNewUser(user), HttpStatus.CREATED);
    }

    @Operation(summary = "Refresh Token", security = @SecurityRequirement(name = "bearerAuth"))
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
