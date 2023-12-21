package com.dogactnrvrdi.movietimecompose.presentation.details

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dogactnrvrdi.movietimecompose.common.Constants.MOVIE_ID
import com.dogactnrvrdi.movietimecompose.common.Resource
import com.dogactnrvrdi.movietimecompose.domain.model.Movie
import com.dogactnrvrdi.movietimecompose.domain.use_case.details.MovieDetails
import com.dogactnrvrdi.movietimecompose.domain.use_case.favorites.AddMovie
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val detailsUseCase: MovieDetails,
    private val addMovieUseCase: AddMovie,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _state = mutableStateOf(DetailsState())
    val state: State<DetailsState> get() = _state

    init {
        savedStateHandle.get<String>(MOVIE_ID)?.let { movieId ->
            getMovieDetails(movieId)
        }
    }

    private fun getMovieDetails(movieId: String) {
        detailsUseCase.executeMovieDetails(movieId).onEach { movie ->
            when (movie) {

                is Resource.Success -> {
                    _state.value = DetailsState(movie = movie.data)
                }

                is Resource.Error -> {
                    _state.value = DetailsState(error = movie.message ?: "Error!")
                }

                is Resource.Loading -> {
                    _state.value = DetailsState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }

    private fun insertMovie(movie: Movie) = viewModelScope.launch {
        addMovieUseCase.invoke(movie)
    }

    fun onEvent(event: DetailsEvent) {
        when (event) {

            is DetailsEvent.Back -> {
                event.navController.navigateUp()
            }

            is DetailsEvent.AddFavorites -> {
                insertMovie(event.movie)
            }
        }
    }
}