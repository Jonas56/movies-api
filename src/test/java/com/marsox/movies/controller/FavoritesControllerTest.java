package com.marsox.movies.controller;

import com.marsox.movies.AbstractTest;
import com.marsox.movies.dto.FavoritesDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Map;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class FavoritesControllerTest extends AbstractTest {
    String accessToken;


    @Override
    @BeforeEach
    public void setUp() throws Exception {
        super.setUp();
        accessToken = super.authenticate();
    }

    @Test
    void shouldGetAllUserFavoriteMovies() throws Exception {
        // given
        String uri = "/api/v1/favorites";
        // when
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
                .header("Authorization", "Bearer " + accessToken)
        ).andReturn();

        //then
        int status = mvcResult.getResponse().getStatus();
        assertThat(status).isEqualTo(200);
        FavoritesDto favoritesDto = super.mapFromJson(mvcResult.getResponse().getContentAsString(), FavoritesDto.class);
        assertThat(favoritesDto).isNotNull();
    }

    @Test
    void shouldAddNewMovieToFavoritesList() throws Exception {
        // given
        int movieId = 2;
        String uri = "/api/v1/favorites/" + movieId;
        // when
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
                .header("Authorization", "Bearer " + accessToken)
        ).andReturn();
        // then
        int status = mvcResult.getResponse().getStatus();
        assertThat(status).isEqualTo(201);
        FavoritesDto favoritesDto = super.mapFromJson(mvcResult.getResponse().getContentAsString(), FavoritesDto.class);
        assertThat(favoritesDto).isNotNull();
    }

    @Test
    void shouldReturnErrorWhenAddingUnknownMovieToFavorites() throws Exception {
        // given
        int movieId = 5;
        String uri = "/api/v1/favorites/" + movieId;
        // when
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
                .header("Authorization", "Bearer " + accessToken)
        ).andReturn();
        // then
        int status = mvcResult.getResponse().getStatus();
        assertThat(status).isEqualTo(400);
        Map<String, String> responseBody = super.mapFromJson(mvcResult.getResponse().getContentAsString(), Map.class);
        assertThat(responseBody.get("message")).isEqualTo("Cannot find movie with id " + movieId);
    }
}