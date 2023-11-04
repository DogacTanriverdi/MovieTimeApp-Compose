package com.dogactnrvrdi.movietimecompose.data.model.top_rated

import com.dogactnrvrdi.movietimecompose.data.model.Movie
import com.dogactnrvrdi.movietimecompose.data.model.popular.PopularMovies
import com.google.gson.annotations.SerializedName

data class TopRatedMovies(
    val page: Int,
    val results: List<Movie>,
    @SerializedName("total_pages") val totalPages: Int,
    @SerializedName("total_results") val totalResults: Int
)

fun TopRatedMovies.toMovieList(): List<com.dogactnrvrdi.movietimecompose.domain.model.Movie> {
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