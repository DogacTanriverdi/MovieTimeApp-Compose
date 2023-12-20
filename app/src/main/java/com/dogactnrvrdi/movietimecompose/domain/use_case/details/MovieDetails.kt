package com.dogactnrvrdi.movietimecompose.domain.use_case.details

import com.dogactnrvrdi.movietimecompose.common.Resource
import com.dogactnrvrdi.movietimecompose.data.model.toMovieDetails
import com.dogactnrvrdi.movietimecompose.domain.model.Movie
import com.dogactnrvrdi.movietimecompose.domain.repo.MovieRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOError
import javax.inject.Inject

class MovieDetails @Inject constructor(
    private val repo: MovieRepository
) {

    fun executeMovieDetails(movieId: String): Flow<Resource<Movie>> = flow {
        try {
            emit(Resource.Loading())
            val movieDetails = repo.getMovieDetails(movieId)
            emit(Resource.Success(movieDetails.toMovieDetails()))
        } catch (e: IOError) {
            emit(Resource.Error(message = "No internet connection!"))
        } catch (e: HttpException) {
            emit(Resource.Error(message = "Server connection failed!"))
        }
    }
}