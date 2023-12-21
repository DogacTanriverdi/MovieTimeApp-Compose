package com.dogactnrvrdi.movietimecompose.domain.use_case.favorites

import com.dogactnrvrdi.movietimecompose.domain.model.Movie
import com.dogactnrvrdi.movietimecompose.domain.repo.MovieRepository
import javax.inject.Inject

class DeleteMovie @Inject constructor(
    private val repo: MovieRepository
) {
    suspend operator fun invoke(movie: Movie) =
        repo.deleteMovie(movie)
}