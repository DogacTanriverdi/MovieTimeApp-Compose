package com.dogactnrvrdi.movietimecompose.data.repo

import com.dogactnrvrdi.movietimecompose.data.model.Movie
import com.dogactnrvrdi.movietimecompose.data.model.popular.PopularMovies
import com.dogactnrvrdi.movietimecompose.data.model.search.SearchMovies
import com.dogactnrvrdi.movietimecompose.data.model.top_rated.TopRatedMovies
import com.dogactnrvrdi.movietimecompose.data.model.upcoming.UpcomingMovies
import com.dogactnrvrdi.movietimecompose.data.source.local.MovieDao
import com.dogactnrvrdi.movietimecompose.data.source.remote.MovieApi
import com.dogactnrvrdi.movietimecompose.domain.repo.MovieRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val api: MovieApi,
    private val dao: MovieDao
) : MovieRepository {

    override suspend fun getTopRatedMovies(): TopRatedMovies = api.getTopRatedMovies()

    override suspend fun getPopularMovies(): PopularMovies = api.getPopularMovies()

    override suspend fun getUpcomingMovies(): UpcomingMovies = api.getUpcomingMovies()

    override suspend fun searchMovies(searchQuery: String): SearchMovies =
        api.searchMovies(searchQuery)

    override suspend fun getMovieDetails(movieId: String): Movie = api.getMovieDetails(movieId)

    override suspend fun insertMovie(movie: com.dogactnrvrdi.movietimecompose.domain.model.Movie) =
        dao.insertMovie(movie)

    override suspend fun deleteMovie(movie: com.dogactnrvrdi.movietimecompose.domain.model.Movie) =
        dao.deleteMovie(movie)

    override fun getALlMovies(): Flow<List<com.dogactnrvrdi.movietimecompose.domain.model.Movie>> =
        dao.getALlMovies()
}