package com.dogactnrvrdi.movietimecompose.presentation.home

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.dogactnrvrdi.movietimecompose.common.Constants.BASE_IMAGE_URL
import com.dogactnrvrdi.movietimecompose.domain.model.Movie
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
                .padding(10.dp),
            text = "Top Rated Movies:",
            fontSize = 25.sp,
            fontWeight = FontWeight.Bold
        )

        LazyRow() {
            items(topRatedState.movies) { movie ->
                HomeMovieListRow(movie = movie, onItemClick = {
                    //navController.navigate(Screen.DetailsScreen.route+"id")
                })
            }
        }

        Spacer(modifier = Modifier.padding(20.dp))

        Text(
            modifier = Modifier
                .padding(10.dp),
            text = "Popular Movies:",
            fontSize = 25.sp,
            fontWeight = FontWeight.Bold
        )

        LazyRow() {
            items(popularState.movies) { movie ->
                HomeMovieListRow(movie = movie, onItemClick = {
                    //navController.navigate(Screen.DetailsScreen.route+"id")
                })
            }
        }

        Spacer(modifier = Modifier.padding(20.dp))

        Text(
            modifier = Modifier
                .padding(10.dp),
            text = "Upcoming Movies:",
            fontSize = 25.sp,
            fontWeight = FontWeight.Bold
        )

        LazyRow() {
            items(upcomingState.movies) { movie ->
                HomeUpcomingMoviesListRow(movie = movie, onItemClick = {
                    //navController.navigate(Screen.DetailsScreen.route+"id")
                })
            }
        }
    }
}

@OptIn(ExperimentalCoilApi::class)
@Composable
fun HomeMovieListRow(
    movie: Movie,
    onItemClick: (Movie) -> Unit
) {
    Column(modifier = Modifier
        .clickable {
            onItemClick(movie)
        }
        .padding(10.dp),
        verticalArrangement = Arrangement.SpaceBetween
    ) {

        Image(
            modifier = Modifier
                .padding(bottom = 5.dp)
                .size(200.dp)
                .clip(RectangleShape),
            painter = rememberImagePainter(data = BASE_IMAGE_URL + movie.posterPath),
            contentDescription = "${movie.title} poster"
        )

        Text(
            modifier = Modifier
                .align(Alignment.CenterHorizontally),
            text = movie.title,
            fontSize = 20.sp,
            overflow = TextOverflow.Clip,
            color = Color.Black,
            textAlign = TextAlign.Center
        )
    }
}

@OptIn(ExperimentalCoilApi::class)
@Composable
fun HomeUpcomingMoviesListRow(
    movie: Movie,
    onItemClick: (Movie) -> Unit
) {
    Column(modifier = Modifier
        .clickable {
            onItemClick(movie)
        }
        .padding(10.dp),
        verticalArrangement = Arrangement.SpaceBetween
    ) {

        Image(
            modifier = Modifier
                .padding(bottom = 5.dp)
                .size(200.dp)
                .clip(RectangleShape),
            painter = rememberImagePainter(data = BASE_IMAGE_URL + movie.posterPath),
            contentDescription = "${movie.title} poster"
        )

        Text(
            modifier = Modifier
                .align(Alignment.CenterHorizontally),
            text = movie.title,
            fontSize = 20.sp,
            overflow = TextOverflow.Clip,
            color = Color.Black,
            textAlign = TextAlign.Center
        )

        Text(
            modifier = Modifier
                .align(Alignment.CenterHorizontally),
            text = movie.releaseDate,
            fontSize = 20.sp,
            overflow = TextOverflow.Clip,
            color = Color.Magenta,
            textAlign = TextAlign.Center
        )
    }
}