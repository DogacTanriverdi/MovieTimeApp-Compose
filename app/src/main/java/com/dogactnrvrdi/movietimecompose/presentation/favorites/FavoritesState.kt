package com.dogactnrvrdi.movietimecompose.presentation.favorites

import com.dogactnrvrdi.movietimecompose.domain.model.Movie

data class FavoritesState(
    val movies: List<Movie> = emptyList(),
)