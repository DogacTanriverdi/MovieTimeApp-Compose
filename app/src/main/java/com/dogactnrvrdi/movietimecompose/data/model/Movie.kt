package com.dogactnrvrdi.movietimecompose.data.model

import com.google.gson.annotations.SerializedName

data class Movie(
    @SerializedName("backdrop_path") val backdropPath: String,
    @SerializedName("genre_ids") val genreIds: List<Int>,
    @SerializedName("original_language") val originalLanguage: String,
    @SerializedName("original_title") val originalTitle: String,
    @SerializedName("poster_path") val posterPath: String,
    @SerializedName("release_date") val releaseDate: String,
    @SerializedName("vote_average") val voteAverage: Double,
    @SerializedName("vote_count") val voteCount: Int,
    val adult: Boolean,
    val id: Int,
    val overview: String,
    val popularity: Double,
    val title: String,
    val video: Boolean
)

fun Movie.toMovieDetails(): com.dogactnrvrdi.movietimecompose.domain.model.Movie =
    com.dogactnrvrdi.movietimecompose.domain.model.Movie(
        originalLanguage = originalLanguage,
        posterPath = posterPath,
        releaseDate = releaseDate,
        id = id,
        overview = overview,
        title = title
    )