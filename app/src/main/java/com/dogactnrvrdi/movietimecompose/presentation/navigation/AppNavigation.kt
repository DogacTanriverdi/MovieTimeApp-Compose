package com.dogactnrvrdi.movietimecompose.presentation.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.dogactnrvrdi.movietimecompose.presentation.details.DetailsScreen
import com.dogactnrvrdi.movietimecompose.presentation.favorites.FavoritesScreen
import com.dogactnrvrdi.movietimecompose.presentation.home.HomeScreen
import com.dogactnrvrdi.movietimecompose.presentation.search.SearchScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppNavigation() {

    val navController = rememberNavController()

    Scaffold(
        bottomBar = {
            NavigationBar {
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentDestination = navBackStackEntry?.destination

                navItemsList.forEach { navItem ->
                    NavigationBarItem(
                        selected = currentDestination?.hierarchy?.any {
                            it.route == navItem.route
                        } == true,
                        onClick = {
                            navController.navigate(navItem.route) {
                                popUpTo(navController.graph.startDestinationId) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        },
                        icon = {
                            Icon(
                                imageVector = navItem.icon,
                                contentDescription = navItem.contentDescription
                            )
                        },
                        label = {
                            Text(text = navItem.label)
                        }
                    )
                }
            }
        }
    ) { paddingValues ->
        NavHost(
            navController = navController,
            startDestination = Screen.HomeScreen.route,
            modifier = Modifier.padding(paddingValues)
        ) {
            composable(route = Screen.HomeScreen.route) { HomeScreen(navController) }
            composable(route = Screen.SearchScreen.route) { SearchScreen(navController) }
            composable(route = Screen.FavoritesScreen.route) { FavoritesScreen() }
            composable(route = Screen.DetailsScreen.route + "?id={id}",
                arguments = listOf(
                    navArgument(name = "id") {
                        NavType.IntType
                    }
                )
            ) {
                DetailsScreen()
            }


        }
    }
}