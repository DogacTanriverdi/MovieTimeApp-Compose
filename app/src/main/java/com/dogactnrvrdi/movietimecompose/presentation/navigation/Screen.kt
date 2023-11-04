package com.dogactnrvrdi.movietimecompose.presentation.navigation

sealed class Screen(val route: String) {

    data object HomeScreen: Screen("home_screen")
    data object SearchScreen: Screen("search_screen")
    data object FavoritesScreen: Screen("favorites_screen")
    data object DetailsScreen: Screen("details_screen")
}