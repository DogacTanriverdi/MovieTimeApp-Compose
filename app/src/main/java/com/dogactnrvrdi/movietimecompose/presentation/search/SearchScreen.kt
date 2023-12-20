package com.dogactnrvrdi.movietimecompose.presentation.search

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
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
        Column {
            MovieSearchBar(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp),
                hint = "Search something..."
            ) { searchQuery ->
                viewModel.onEvent(SearchEvent.Search(searchQuery))
            }

            LazyColumn(modifier = Modifier.fillMaxSize()) {
                items(state.movies) { movie ->
                    SearchMovieListRow(movie = movie) {
                        navigate(navController, movie)
                    }
                }
            }
        }
    }
}

private fun navigate(navController: NavController, movie: Movie) {
    navController.navigate(Screen.DetailsScreen.route + "/${movie.id}")
}