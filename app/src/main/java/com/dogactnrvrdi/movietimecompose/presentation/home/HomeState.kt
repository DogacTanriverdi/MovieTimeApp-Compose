package com.dogactnrvrdi.movietimecompose.presentation.home

import com.dogactnrvrdi.movietimecompose.domain.model.Movie

data class HomeState(
    var isLoading: Boolean = false,
    var movies: List<Movie>?= null,
    var error: String = ""
)
