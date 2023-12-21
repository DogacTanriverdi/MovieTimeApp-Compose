package com.dogactnrvrdi.movietimecompose.presentation.search

import androidx.navigation.NavController
import com.dogactnrvrdi.movietimecompose.presentation.details.DetailsEvent

sealed class SearchEvent {
    data class Search(val searchQuery: String) : SearchEvent()
    data class Back(val navController: NavController) : SearchEvent()
}