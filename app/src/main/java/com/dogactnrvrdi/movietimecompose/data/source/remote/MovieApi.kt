package com.dogactnrvrdi.movietimecompose.data.source.remote

import com.dogactnrvrdi.movietimecompose.common.Constants.API_KEY
import com.dogactnrvrdi.movietimecompose.common.Constants.API_KEY_QUERY
import com.dogactnrvrdi.movietimecompose.common.Constants.MOVIE_DETAILS
import com.dogactnrvrdi.movietimecompose.common.Constants.POPULAR_MOVIES
import com.dogactnrvrdi.movietimecompose.common.Constants.SEARCH_MOVIES
import com.dogactnrvrdi.movietimecompose.common.Constants.TOP_RATED_MOVIES
import com.dogactnrvrdi.movietimecompose.common.Constants.UPCOMING_MOVIES
import com.dogactnrvrdi.movietimecompose.data.model.Movie
import com.dogactnrvrdi.movietimecompose.data.model.popular.PopularMovies
import com.dogactnrvrdi.movietimecompose.data.model.search.SearchMovies
import com.dogactnrvrdi.movietimecompose.data.model.top_rated.TopRatedMovies
import com.dogactnrvrdi.movietimecompose.data.model.upcoming.UpcomingMovies
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieApi {

    @GET(TOP_RATED_MOVIES)
    suspend fun getTopRatedMovies(
        @Query(API_KEY_QUERY) apiKey: String = API_KEY
    ): TopRatedMovies

    @GET(POPULAR_MOVIES)
    suspend fun getPopularMovies(
        @Query(API_KEY_QUERY) apiKey: String = API_KEY
    ): PopularMovies

    @GET(UPCOMING_MOVIES)
    suspend fun getUpcomingMovies(
        @Query(API_KEY_QUERY) apiKey: String = API_KEY
    ): UpcomingMovies

    @GET(SEARCH_MOVIES)
    suspend fun searchMovies(
        @Query("query") searchQuery: String,
        @Query(API_KEY_QUERY) apiKey: String = API_KEY
    ): SearchMovies

    @GET(MOVIE_DETAILS)
    suspend fun getMovieDetails(
        @Path("id") movieId: String,
        @Query(API_KEY_QUERY) apiKey: String = API_KEY
    ): Movie
}