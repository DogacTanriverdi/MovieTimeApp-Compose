package com.dogactnrvrdi.movietimecompose.domain.model

import com.google.gson.annotations.SerializedName

data class Movie(
    val originalLanguage: String,
    val posterPath: String,
    val releaseDate: String,
    val id: Int,
    val overview: String,
    val title: String
)