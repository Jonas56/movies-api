package com.marsox.movies.controller;

import com.marsox.movies.dto.FavoritesDto;

import com.marsox.movies.service.IFavoritesService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping(value = "/api/v1", produces = "application/json")
public class FavoritesController {

    private final IFavoritesService userMoviesService;

    public FavoritesController(IFavoritesService userMoviesService) {
        this.userMoviesService = userMoviesService;
    }

    @GetMapping("/favorites")
    @Operation(summary = "Get user favorite movies", security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Request Accepted",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = FavoritesDto.class))}),
            @ApiResponse(responseCode = "400", description = "Unable to fetch data", content = {
                    @Content(mediaType = "application/json")
            }),
    })
    public FavoritesDto getUserFavorites(HttpServletRequest request) {
        return userMoviesService.getUserFavorites(request);
    }

    @PostMapping("/favorites/{movieId}")
    @Operation(summary = "Add Movie to favorites", security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Successfully added movie to favorites",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = FavoritesDto.class))}),
            @ApiResponse(responseCode = "400", description = "Unable to add movie", content = {
                    @Content(mediaType = "application/json")
            }),
    })
    public ResponseEntity<FavoritesDto> addMovieToFavorites(HttpServletRequest request, @PathVariable Long movieId) {
        return new ResponseEntity<>(userMoviesService.addToFavorites(request, movieId), HttpStatus.CREATED);
    }
}
