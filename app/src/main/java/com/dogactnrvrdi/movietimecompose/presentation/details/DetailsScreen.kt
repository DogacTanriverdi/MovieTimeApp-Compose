package com.dogactnrvrdi.movietimecompose.presentation.details

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.MarqueeAnimationMode
import androidx.compose.foundation.background
import androidx.compose.foundation.basicMarquee
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Alignment.Companion.TopCenter
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.dogactnrvrdi.movietimecompose.common.Constants.BASE_IMAGE_URL

@OptIn(ExperimentalCoilApi::class, ExperimentalFoundationApi::class)
@Composable
fun DetailsScreen(
    navController: NavController,
    viewModel: DetailsViewModel = hiltViewModel()
) {

    val state = viewModel.state.value

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        contentAlignment = TopCenter
    ) {

        if (state.movie != null) {
            state.movie.let { movie ->

                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(10.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {

                        IconButton(onClick = {
                            viewModel.onEvent(DetailsEvent.Back(navController))
                        }
                        ) {
                            Icon(
                                imageVector = Icons.Default.ArrowBack,
                                contentDescription = "Back button",
                                modifier = Modifier.fillMaxSize()
                            )
                        }

                        Text(
                            modifier = Modifier
                                .padding(7.dp)
                                .width(250.dp)
                                .basicMarquee(
                                    animationMode = MarqueeAnimationMode.Immediately,
                                    delayMillis = 1000
                                ),
                            text = movie.title ?: "",
                            fontSize = 23.sp,
                            textAlign = TextAlign.Center,
                            color = Color.Red
                        )

                        IconButton(onClick = {
                            viewModel.onEvent(DetailsEvent.AddFavorites(movie = movie))
                        }
                        ) {
                            Icon(
                                imageVector = Icons.Default.FavoriteBorder,
                                contentDescription = "Add favorite button",
                                modifier = Modifier.fillMaxSize()
                            )
                        }
                    }

                    Image(
                        modifier = Modifier
                            .padding(16.dp)
                            .size(300.dp)
                            .clip(RectangleShape)
                            .align(CenterHorizontally),
                        painter = rememberImagePainter(
                            data =
                            BASE_IMAGE_URL + movie.posterPath
                        ),
                        contentDescription = "${movie.title} poster"
                    )

                    CustomText(text = movie.releaseDate ?: "")

                    CustomText(text = movie.overview ?: "")
                }
            }
        }

        if (state.error.isNotBlank()) {
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(14.dp)
                    .align(Center),
                text = state.error,
                color = MaterialTheme.colorScheme.error,
                textAlign = TextAlign.Center
            )
        }

        if (state.isLoading) {
            CircularProgressIndicator(modifier = Modifier.align(Center))
        }
    }
}

@Composable
fun CustomText(text: String) {
    Text(
        modifier = Modifier.padding(14.dp),
        text = text,
        textAlign = TextAlign.Center,
        color = Color.Black
    )
}