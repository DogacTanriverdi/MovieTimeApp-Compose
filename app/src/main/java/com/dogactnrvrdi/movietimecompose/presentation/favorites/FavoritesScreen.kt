package com.dogactnrvrdi.movietimecompose.presentation.favorites

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.dogactnrvrdi.movietimecompose.presentation.custom_screen.FavoritesMovieListRow
import com.dogactnrvrdi.movietimecompose.presentation.navigation.Screen

@Composable
fun FavoritesScreen(
    navController: NavController,
    viewModel: FavoritesViewModel = hiltViewModel()
) {

    val state = viewModel.state.value

    Column(modifier = Modifier.fillMaxSize()) {

        Text(
            modifier = Modifier
                .padding(20.dp),
            text = "Favorites",
            fontSize = 30.sp,
            color = Color.Red,
            fontWeight = FontWeight.Bold
        )

            LazyColumn {
                state.movies.let { movies ->
                    items(movies) { movie ->
                        FavoritesMovieListRow(movie = movie, onItemClick = {
                            navController.navigate(Screen.DetailsScreen.route + "?id=${movie.id}")
                        })
                    }
                }
            }
    }
}