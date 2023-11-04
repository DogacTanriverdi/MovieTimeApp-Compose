package com.dogactnrvrdi.movietimecompose.presentation.search

import com.dogactnrvrdi.movietimecompose.domain.model.Movie

data class SearchState(
    val isLoading: Boolean = false,
    val movies: List<Movie> = listOf(),
    val error: String = "",
    val searchString: String = ""
)