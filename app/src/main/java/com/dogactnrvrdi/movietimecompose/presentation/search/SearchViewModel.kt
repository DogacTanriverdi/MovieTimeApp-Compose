package com.dogactnrvrdi.movietimecompose.presentation.search

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dogactnrvrdi.movietimecompose.common.Resource
import com.dogactnrvrdi.movietimecompose.domain.use_case.search.SearchMovies
import com.dogactnrvrdi.movietimecompose.presentation.home.HomeState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val useCase: SearchMovies
) : ViewModel() {

    private val _state = mutableStateOf(SearchState())
    val state: State<SearchState> get() = _state

    private var job: Job? = null

    fun getMovies(searchQuery: String) {
        job?.cancel()

        job = useCase.executeSearchMovies(searchQuery).onEach { movies ->

            when (movies) {

                is Resource.Success -> {
                    _state.value = SearchState(movies = movies.data ?: emptyList())
                }

                is Resource.Error -> {
                    _state.value = SearchState(error = movies.message ?: "Error!")
                }

                is Resource.Loading -> {
                    _state.value = SearchState(isLoading = true)
                }
            }

        }.launchIn(viewModelScope)
    }

    fun onEvent(event: SearchEvent) {
        when (event) {
            is SearchEvent.Search -> {
                getMovies(event.searchQuery)
            }
        }
    }
}