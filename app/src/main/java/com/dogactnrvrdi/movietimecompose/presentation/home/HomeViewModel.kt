package com.dogactnrvrdi.movietimecompose.presentation.home

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dogactnrvrdi.movietimecompose.common.Resource
import com.dogactnrvrdi.movietimecompose.domain.use_case.popular.PopularMovies
import com.dogactnrvrdi.movietimecompose.domain.use_case.top_rated.TopRatedMovies
import com.dogactnrvrdi.movietimecompose.domain.use_case.upcoming.UpcomingMovies
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val topRatedMoviesUseCase: TopRatedMovies,
    private val popularMoviesUseCase: PopularMovies,
    private val upcomingMoviesUseCase: UpcomingMovies
) : ViewModel() {

    private val _topRatedState = mutableStateOf(HomeState())
    val topRatedState: State<HomeState> get() = _topRatedState

    private val _popularState = mutableStateOf(HomeState())
    val popularState: State<HomeState> get() = _popularState

    private val _upcomingState = mutableStateOf(HomeState())
    val upcomingState: State<HomeState> get() = _upcomingState

    init {
        getTopRatedMovies()
        getPopularMovies()
        getUpcomingMovies()
    }

    private fun getTopRatedMovies() {

        topRatedMoviesUseCase.executeTopRatedMovies().onEach { movies ->

            when (movies) {

                is Resource.Success -> {
                    _topRatedState.value = HomeState(movies = movies.data ?: emptyList())
                }

                is Resource.Error -> {
                    _topRatedState.value = HomeState(error = movies.message ?: "Error!")
                }

                is Resource.Loading -> {
                    _topRatedState.value = HomeState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }

    private fun getPopularMovies() {

        popularMoviesUseCase.executePopularMovies().onEach { movies ->

            when (movies) {

                is Resource.Success -> {
                    _popularState.value = HomeState(movies = movies.data ?: emptyList())
                }

                is Resource.Error -> {
                    _popularState.value = HomeState(error = movies.message ?: "Error!")
                }

                is Resource.Loading -> {
                    _popularState.value = HomeState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }

    private fun getUpcomingMovies() {

        upcomingMoviesUseCase.executeUpcomingMovies().onEach { movies ->

            when (movies) {

                is Resource.Success -> {
                    _upcomingState.value = HomeState(movies = movies.data ?: emptyList())
                }

                is Resource.Error -> {
                    _upcomingState.value = HomeState(error = movies.message ?: "Error!")
                }

                is Resource.Loading -> {
                    _upcomingState.value = HomeState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }
}