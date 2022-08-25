package com.chavesdev.mvvmsamplecode

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.chavesdev.mvvmsamplecode.movies.core.presentation.Screen
import com.chavesdev.mvvmsamplecode.movies.details.presentation.ui.MovieDetailScreen
import com.chavesdev.mvvmsamplecode.movies.popular.presentation.ui.PopularMovieListScreen
import com.chavesdev.mvvmsamplecode.movies.popular.presentation.viewmodel.MoviesListViewModel
import com.chavesdev.mvvmsamplecode.ui.theme.MVVMSampleCodeTheme
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : ComponentActivity() {

    private val moviesListViewModel: MoviesListViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MVVMSampleCodeTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = Screen.MovieListScreen.route
                    ) {

                        composable(
                            Screen.MovieListScreen.route
                        ) {
                            PopularMovieListScreen(navController = navController, moviesListViewModel)
                        }

                        composable(
                            Screen.MovieDetailScreen.route +"/{movieId}"
                        ){ backStackEntry ->
                            backStackEntry.arguments?.getString("movieId")?.let { movieId ->
                                backStackEntry.savedStateHandle["movieId"] = movieId
                                MovieDetailScreen(backStackEntry.savedStateHandle)
                            }

                        }

                    }
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MVVMSampleCodeTheme {
        Greeting("Android")
    }
}