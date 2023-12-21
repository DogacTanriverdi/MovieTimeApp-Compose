package com.dogactnrvrdi.movietimecompose.domain.use_case.search

import com.dogactnrvrdi.movietimecompose.common.Resource
import com.dogactnrvrdi.movietimecompose.data.model.search.toMovieList
import com.dogactnrvrdi.movietimecompose.data.model.top_rated.toMovieList
import com.dogactnrvrdi.movietimecompose.domain.model.Movie
import com.dogactnrvrdi.movietimecompose.domain.repo.MovieRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOError
import javax.inject.Inject

class SearchMovies @Inject constructor(
    private val repo: MovieRepository
) {
    fun executeSearchMovies(searchQuery: String): Flow<Resource<List<Movie>>> = flow {
        try {
            emit(Resource.Loading())
            val searchMovies = repo.searchMovies(searchQuery)
            if (searchMovies.totalResults == 0)
                emit(Resource.Error(message = "No movies found!"))
            else {
                emit(Resource.Success(searchMovies.toMovieList()))
            }
        } catch (e: IOError) {
            emit(Resource.Error(message = "No internet connection!"))
        } catch (e: HttpException) {
            emit(Resource.Error(message = "Server connection failed!"))
        }
    }
}