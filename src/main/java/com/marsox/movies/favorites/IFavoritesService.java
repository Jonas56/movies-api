package com.marsox.movies.favorites;

import javax.servlet.http.HttpServletRequest;

public interface IFavoritesService {
    FavoritesDto getUserFavorites(HttpServletRequest request);
    FavoritesDto addToFavorites(HttpServletRequest request, Long movieId);
}
