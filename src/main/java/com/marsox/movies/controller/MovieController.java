package com.marsox.movies.controller;

import com.marsox.movies.dto.MovieDto;
import com.marsox.movies.dto.MovieEssentialDto;
import com.marsox.movies.model.Movie;
import com.marsox.movies.service.IMovieService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/movies", produces = "application/json")
//@CrossOrigin(origins = "*")
public class MovieController {

    private final IMovieService movieService;

    public MovieController(IMovieService movieService) {
        this.movieService = movieService;
    }

    @Operation(summary = "Fetch all movies", security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully get all movies",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = MovieEssentialDto.class))}),
            @ApiResponse(responseCode = "403", description = "Request forbidden! Check your token.", content = {
                    @Content(mediaType = "application/json")
            })
    })
    @GetMapping
    public ResponseEntity<List<MovieEssentialDto>> getMovies(@RequestParam(required = false) String name) {
        if (name == null) {
            return new ResponseEntity<>(movieService.getAllMovies(), HttpStatus.OK);
        }
        return new ResponseEntity<>(movieService.getMoviesByName(name), HttpStatus.OK);
    }

    @Operation(summary = "Get movie by id", security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully find movie by id",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = MovieDto.class))}),
            @ApiResponse(responseCode = "400", description = "Unable to execute request!", content = {
                    @Content(mediaType = "application/json")
            }),
            @ApiResponse(responseCode = "403", description = "Request forbidden! Check your token.", content = {
                    @Content(mediaType = "application/json")
            })
    })
    @GetMapping("/{movieId}")
    public ResponseEntity<MovieDto> getMovieById(@PathVariable Long movieId) {
        return new ResponseEntity<>(movieService.getMovieById(movieId), HttpStatus.OK);
    }

    @Operation(summary = "Add new Movie", security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully added new movie",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = MovieDto.class))}),
            @ApiResponse(responseCode = "400", description = "Unable to execute request!", content = {
                    @Content(mediaType = "application/json")
            }),
            @ApiResponse(responseCode = "403", description = "Request forbidden! Check your token.", content = {
                    @Content(mediaType = "application/json")
            })
    })
    @PostMapping
    public ResponseEntity<MovieDto> addNewMovie(@RequestBody @Valid Movie m) {
        return new ResponseEntity<>(movieService.addNewMovie(m), HttpStatus.CREATED);
    }

    @Operation(summary = "Delete movie by id", security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully deleted movie",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "400", description = "Unable to execute request!", content = {
                    @Content(mediaType = "application/json")
            }),
            @ApiResponse(responseCode = "403", description = "Request forbidden! Check your token.", content = {
                    @Content(mediaType = "application/json")
            })
    })
    @DeleteMapping("/{movieId}")
    public void deleteMovie(@PathVariable("movieId") Long movieId) {
        movieService.deleteMovie(movieId);
    }

    @Operation(summary = "Update Movie by id", security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully updated movie",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = MovieDto.class))}),
            @ApiResponse(responseCode = "400", description = "Unable to execute request!", content = {
                    @Content(mediaType = "application/json")
            }),
            @ApiResponse(responseCode = "403", description = "Request forbidden! Check your token.", content = {
                    @Content(mediaType = "application/json")
            })
    })
    @PutMapping("/{movieId}")
    public ResponseEntity<MovieDto> updateMovie(@PathVariable("movieId") Long movieId, @RequestBody Movie m) {
        return new ResponseEntity<>(movieService.updateMovie(movieId, m), HttpStatus.CREATED);
    }

}
