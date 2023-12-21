package com.dogactnrvrdi.movietimecompose.presentation.details

import androidx.navigation.NavController
import com.dogactnrvrdi.movietimecompose.domain.model.Movie

sealed class DetailsEvent {
    data class Back(val navController: NavController) : DetailsEvent()
    data class AddFavorites(val movie: Movie) : DetailsEvent()
}