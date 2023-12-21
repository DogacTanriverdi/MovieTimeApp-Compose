package com.dogactnrvrdi.movietimecompose.presentation.favorites

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dogactnrvrdi.movietimecompose.domain.use_case.favorites.GetAllMovies
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class FavoritesViewModel @Inject constructor(
    private val useCase: GetAllMovies
) : ViewModel() {

    private val _state = mutableStateOf(FavoritesState())
    val state: State<FavoritesState> get() = _state

    init {
        getAllMovies()
    }

    private fun getAllMovies() {
        useCase.invoke().onEach { movies ->
            _state.value = FavoritesState(movies)
        }.launchIn(viewModelScope)
    }
}
