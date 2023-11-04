package com.dogactnrvrdi.movietimecompose.presentation.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.ui.graphics.vector.ImageVector

data class NavItem(
    val label: String,
    val icon: ImageVector,
    val route: String,
    val contentDescription: String
)

val navItemsList: List<NavItem> = listOf(

    NavItem(
        label = "Home",
        icon = Icons.Default.Home,
        route = Screen.HomeScreen.route,
        contentDescription = "Home button"
    ),

    NavItem(
        label = "Search",
        icon = Icons.Default.Search,
        route = Screen.SearchScreen.route,
        contentDescription = "Search button"
    ),

    NavItem(
        label = "Favorites",
        icon = Icons.Default.Favorite,
        route = Screen.FavoritesScreen.route,
        contentDescription = "Favorites button"
    )
)
