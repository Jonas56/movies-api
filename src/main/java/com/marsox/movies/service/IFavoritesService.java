package com.marsox.movies.service;

import com.marsox.movies.dto.FavoritesDto;

import javax.servlet.http.HttpServletRequest;

public interface IFavoritesService {
    FavoritesDto getUserFavorites(HttpServletRequest request);
    FavoritesDto addToFavorites(HttpServletRequest request, Long movieId);
}
