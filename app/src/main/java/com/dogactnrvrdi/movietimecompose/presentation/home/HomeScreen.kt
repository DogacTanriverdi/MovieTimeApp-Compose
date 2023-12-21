package com.dogactnrvrdi.movietimecompose.presentation.home

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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

        Text(
            modifier = Modifier
                .padding(20.dp),
            text = "Home",
            fontSize = 30.sp,
            color = Color.Red,
            fontWeight = FontWeight.Bold
        )

        if (topRatedState.movies != null) {
            topRatedState.movies!!.let { movies ->
                CustomText(text = "Top Rated Movies:")

                LazyRow() {
                    items(movies) { movie ->
                        HomeMovieListRow(movie = movie, onItemClick = {
                            navigate(navController, movie)
                        })
                    }
                }

                Spacer(modifier = Modifier.padding(10.dp))
            }
        }

        if (popularState.movies != null) {
            popularState.movies!!.let { movies ->
                CustomText(text = "Popular Movies:")

                LazyRow() {
                    items(movies) { movie ->
                        HomeMovieListRow(movie = movie, onItemClick = {
                            navigate(navController, movie)
                        })
                    }
                }

                Spacer(modifier = Modifier.padding(20.dp))
            }
        } else if (topRatedState.isLoading) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.CenterHorizontally))
        } else if (topRatedState.error.isNotBlank()) {
            CustomError()
        }

        if (upcomingState.movies != null) {
            upcomingState.movies!!.let { movies ->
                CustomText(text = "Upcoming Movies:")

                LazyRow() {
                    items(movies) { movie ->
                        HomeUpcomingMoviesListRow(movie = movie, onItemClick = {
                            navigate(navController, movie)
                        })
                    }
                }
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

@Composable
fun CustomError() {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            modifier = Modifier
                .padding(20.dp),
            text = "Error!",
            fontSize = 35.sp,
            color = Color.Red,
            fontWeight = FontWeight.Bold
        )
    }
}

private fun navigate(navController: NavController, movie: Movie) {
    navController.navigate(Screen.DetailsScreen.route + "?id=${movie.id}")
}