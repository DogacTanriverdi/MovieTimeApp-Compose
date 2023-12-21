package com.dogactnrvrdi.movietimecompose.presentation.search

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.dogactnrvrdi.movietimecompose.domain.model.Movie
import com.dogactnrvrdi.movietimecompose.presentation.custom_screen.MovieSearchBar
import com.dogactnrvrdi.movietimecompose.presentation.custom_screen.SearchMovieListRow
import com.dogactnrvrdi.movietimecompose.presentation.navigation.Screen

@Composable
fun SearchScreen(
    navController: NavController,
    viewModel: SearchViewModel = hiltViewModel()
) {

    val state = viewModel.state.value

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp),
                horizontalArrangement = Arrangement.Start
            ) {

                IconButton(onClick = {
                    viewModel.onEvent(SearchEvent.Back(navController))
                }
                ) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "Back button",
                        modifier = Modifier.fillMaxSize()
                    )
                }

                Text(
                    modifier = Modifier.padding(horizontal = 20.dp, vertical = 3.dp),
                    text = "Search",
                    fontSize = 30.sp,
                    color = Color.Red,
                    fontWeight = FontWeight.Bold
                )
            }

            MovieSearchBar(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp),
                hint = "Search something..."
            ) { searchQuery ->
                viewModel.onEvent(SearchEvent.Search(searchQuery))
            }

            if (state.movies != null) {
                LazyColumn(modifier = Modifier.fillMaxSize()) {

                    items(state.movies!!) { movie ->
                        SearchMovieListRow(movie = movie) {
                            navigate(navController, movie)
                        }
                    }
                }
            } else if (state.isLoading) {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.CenterHorizontally))
            } else if (state.error.isNotBlank()) {
                Text(
                    modifier = Modifier.align(Alignment.CenterHorizontally).padding(top = 10.dp),
                    text = state.error,
                    textAlign = TextAlign.Center,
                    fontSize = 20.sp,
                    color = Color.Red
                )
            }
        }
    }
}

private fun navigate(navController: NavController, movie: Movie) {
    navController.navigate(Screen.DetailsScreen.route + "?id=${movie.id}")
}