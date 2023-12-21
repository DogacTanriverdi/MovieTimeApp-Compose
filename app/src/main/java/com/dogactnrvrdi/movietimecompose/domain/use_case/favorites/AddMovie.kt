package com.dogactnrvrdi.movietimecompose.domain.use_case.favorites

import com.dogactnrvrdi.movietimecompose.domain.model.Movie
import com.dogactnrvrdi.movietimecompose.domain.repo.MovieRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class AddMovie @Inject constructor(
    private val repo: MovieRepository
) {
    suspend operator fun invoke(movie: Movie) =
        repo.insertMovie(movie)
}