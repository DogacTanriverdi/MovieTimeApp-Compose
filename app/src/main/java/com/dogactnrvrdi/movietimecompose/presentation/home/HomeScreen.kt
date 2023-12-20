package com.dogactnrvrdi.movietimecompose.presentation.home

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.dogactnrvrdi.movietimecompose.domain.model.Movie
import com.dogactnrvrdi.movietimecompose.presentation.custom_screen.HomeMovieListRow
import com.dogactnrvrdi.movietimecompose.presentation.custom_screen.HomeUpcomingMoviesListRow
import com.dogactnrvrdi.movietimecompose.presentation.navigation.Screen

@SuppressLint("SuspiciousIndentation")
@Composable
fun HomeScreen(
    navController: NavController,
    viewModel: HomeViewModel = hiltViewModel()
) {

    val topRatedState = viewModel.topRatedState.value
    val popularState = viewModel.popularState.value
    val upcomingState = viewModel.upcomingState.value

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {

        CustomText(text = "Top Rated Movies:")

        LazyRow() {
            items(topRatedState.movies) { movie ->
                HomeMovieListRow(movie = movie, onItemClick = {
                    navController.navigate(
                        route = Screen.DetailsScreen.route +
                        "?id=${movie.id}"
                    )
                })
            }
        }

        Spacer(modifier = Modifier.padding(20.dp))

        CustomText(text = "Popular Movies:")

        LazyRow() {
            items(popularState.movies) { movie ->
                HomeMovieListRow(movie = movie, onItemClick = {
                    navController.navigate(
                        route = Screen.DetailsScreen.route +
                                "?id=${movie.id}"
                    )
                })
            }
        }

        Spacer(modifier = Modifier.padding(20.dp))

        CustomText(text = "Upcoming Movies:")

        LazyRow() {
            items(upcomingState.movies) { movie ->
                HomeUpcomingMoviesListRow(movie = movie, onItemClick = {
                    navController.navigate(
                        route = Screen.DetailsScreen.route +
                                "?id=${movie.id}"
                    )
                })
            }
        }
    }
}

@Composable
fun CustomText(text: String) {
    Text(
        modifier = Modifier
            .padding(10.dp),
        text = text,
        fontSize = 25.sp,
        fontWeight = FontWeight.Bold
    )
}