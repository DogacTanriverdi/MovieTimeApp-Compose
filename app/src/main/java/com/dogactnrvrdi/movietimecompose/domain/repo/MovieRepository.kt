package com.dogactnrvrdi.movietimecompose.domain.repo

import com.dogactnrvrdi.movietimecompose.data.model.Movie
import com.dogactnrvrdi.movietimecompose.data.model.popular.PopularMovies
import com.dogactnrvrdi.movietimecompose.data.model.search.SearchMovies
import com.dogactnrvrdi.movietimecompose.data.model.top_rated.TopRatedMovies
import com.dogactnrvrdi.movietimecompose.data.model.upcoming.UpcomingMovies

interface MovieRepository {

    suspend fun getTopRatedMovies(): TopRatedMovies
    suspend fun getPopularMovies(): PopularMovies
    suspend fun getUpcomingMovies(): UpcomingMovies
    suspend fun searchMovies(searchQuery: String): SearchMovies
    suspend fun getMovieDetails(movieId: String): Movie
}