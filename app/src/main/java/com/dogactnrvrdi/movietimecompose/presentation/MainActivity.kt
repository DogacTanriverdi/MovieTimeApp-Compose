package com.dogactnrvrdi.movietimecompose.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.dogactnrvrdi.movietimecompose.presentation.navigation.AppNavigation
import com.dogactnrvrdi.movietimecompose.ui.theme.MovieTimeComposeTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MovieTimeComposeTheme {
                // A surface container using the 'background' color from the theme
                AppNavigation()
            }
        }
    }
}