package com.dogactnrvrdi.movietimecompose.data.model.popular

import com.dogactnrvrdi.movietimecompose.data.model.Movie
import com.google.gson.annotations.SerializedName

data class PopularMovies(
    val page: Int,
    val results: List<Movie>,
    @SerializedName("total_pages") val totalPages: Int,
    @SerializedName("total_results") val totalResults: Int
)

fun PopularMovies.toMovieList(): List<com.dogactnrvrdi.movietimecompose.domain.model.Movie> {
    return results.map { movie ->
        com.dogactnrvrdi.movietimecompose.domain.model.Movie(
            originalLanguage = movie.originalLanguage,
            posterPath = movie.posterPath,
            releaseDate = movie.releaseDate,
            id = movie.id,
            overview = movie.overview,
            title = movie.title
        )
    }
}