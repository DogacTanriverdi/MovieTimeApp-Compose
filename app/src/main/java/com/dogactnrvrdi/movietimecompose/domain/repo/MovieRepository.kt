package com.dogactnrvrdi.movietimecompose.domain.repo

import com.dogactnrvrdi.movietimecompose.data.model.Movie
import com.dogactnrvrdi.movietimecompose.data.model.popular.PopularMovies
import com.dogactnrvrdi.movietimecompose.data.model.search.SearchMovies
import com.dogactnrvrdi.movietimecompose.data.model.top_rated.TopRatedMovies
import com.dogactnrvrdi.movietimecompose.data.model.upcoming.UpcomingMovies
import kotlinx.coroutines.flow.Flow

interface MovieRepository {

    suspend fun getTopRatedMovies(): TopRatedMovies

    suspend fun getPopularMovies(): PopularMovies

    suspend fun getUpcomingMovies(): UpcomingMovies

    suspend fun searchMovies(searchQuery: String): SearchMovies

    suspend fun getMovieDetails(movieId: String): Movie

    suspend fun insertMovie(movie: com.dogactnrvrdi.movietimecompose.domain.model.Movie)

    suspend fun deleteMovie(movie: com.dogactnrvrdi.movietimecompose.domain.model.Movie)

    fun getALlMovies(): Flow<List<com.dogactnrvrdi.movietimecompose.domain.model.Movie>>
}