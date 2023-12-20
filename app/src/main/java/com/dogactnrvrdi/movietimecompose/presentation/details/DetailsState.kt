package com.dogactnrvrdi.movietimecompose.presentation.details

import com.dogactnrvrdi.movietimecompose.domain.model.Movie

data class DetailsState(
    val isLoading: Boolean = false,
    val movie: Movie? = null,
    val error: String = ""
)