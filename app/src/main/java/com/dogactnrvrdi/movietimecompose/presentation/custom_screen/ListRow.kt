package com.dogactnrvrdi.movietimecompose.presentation.custom_screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.dogactnrvrdi.movietimecompose.common.Constants
import com.dogactnrvrdi.movietimecompose.domain.model.Movie

@OptIn(ExperimentalCoilApi::class)
@Composable
fun HomeMovieListRow(
    movie: Movie,
    onItemClick: (Movie) -> Unit
) {
    Column(modifier = Modifier.width(200.dp)
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
            painter = rememberImagePainter(data = Constants.BASE_IMAGE_URL + movie.posterPath),
            contentDescription = "${movie.title} poster"
        )

        Text(
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .width(200.dp),
            text = movie.title ?: "",
            fontSize = 20.sp,
            maxLines = 1,
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
    Column(modifier = Modifier.width(200.dp)
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
            painter = rememberImagePainter(data = Constants.BASE_IMAGE_URL + movie.posterPath),
            contentDescription = "${movie.title} poster"
        )

        Text(
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .width(200.dp),
            text = movie.title ?: "",
            maxLines = 1,
            fontSize = 20.sp,
            overflow = TextOverflow.Clip,
            color = Color.Black,
            textAlign = TextAlign.Center
        )

        Text(
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .width(200.dp),
            text = movie.releaseDate ?: "",
            maxLines = 1,
            fontSize = 20.sp,
            overflow = TextOverflow.Clip,
            color = Color.Magenta,
            textAlign = TextAlign.Center
        )
    }
}

@OptIn(ExperimentalCoilApi::class)
@Composable
fun SearchMovieListRow(
    movie: Movie,
    onItemClick: (Movie) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                onItemClick(movie)
            }
            .padding(10.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Image(
            modifier = Modifier
                .padding(16.dp)
                .size(200.dp, 200.dp)
                .clip(RectangleShape),
            painter = rememberImagePainter(data = Constants.BASE_IMAGE_URL + movie.posterPath),
            contentDescription = movie.title
        )

        Column(
            modifier = Modifier
                .align(Alignment.CenterVertically),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = movie.title ?: "",
                style = MaterialTheme.typography.headlineMedium,
                overflow = TextOverflow.Ellipsis,
                maxLines = 4,
                color = Color.Black,
                textAlign = TextAlign.Center
            )

            Text(
                text = movie.releaseDate ?: "",
                style = MaterialTheme.typography.headlineSmall,
                overflow = TextOverflow.Ellipsis,
                color = Color.Black,
                textAlign = TextAlign.Center
            )
        }
    }
}

@OptIn(ExperimentalCoilApi::class)
@Composable
fun FavoritesMovieListRow(
    movie: Movie,
    onItemClick: (Movie) -> Unit
) {
    Row(modifier = Modifier.fillMaxWidth()
        .clickable {
            onItemClick(movie)
        }
        .padding(10.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {

        Image(
            modifier = Modifier
                .padding(bottom = 5.dp)
                .size(200.dp)
                .clip(RectangleShape),
            painter = rememberImagePainter(data = Constants.BASE_IMAGE_URL + movie.posterPath),
            contentDescription = "${movie.title} poster"
        )

        Text(
            modifier = Modifier
                .align(Alignment.CenterVertically)
                .width(200.dp),
            text = movie.title ?: "",
            fontSize = 20.sp,
            maxLines = 5,
            overflow = TextOverflow.Clip,
            color = Color.Black,
            textAlign = TextAlign.Center
        )
    }
}