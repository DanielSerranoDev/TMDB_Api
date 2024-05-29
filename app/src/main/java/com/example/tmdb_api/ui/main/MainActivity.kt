package com.example.tmdb_api.ui.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.tmdb_api.ui.main.Detail.DetailScreen
import com.example.tmdb_api.ui.main.Detail.DetailViewModel
import com.example.tmdb_api.ui.main.Home.HomeViewModel
import com.example.tmdb_api.ui.theme.TMDB_ApiTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val homeViewModel by viewModels<HomeViewModel>()
    private val detailViewModel by viewModels<DetailViewModel>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        homeViewModel.getRespositoryData()

        enableEdgeToEdge()
        setContent {
            TMDB_ApiTheme {
                val navController = rememberNavController()

                NavHost(navController = navController, startDestination = "home") {
                    composable("home") {

                        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                            MainScreen(
                                modifier = Modifier.padding(innerPadding),
                                homeViewModel,
                                navController
                            )
                        }
                    }

                    composable("detail/{id}",
                        arguments = listOf(
                            navArgument("id") {
                                this.type = NavType.StringType
                                nullable = false
                            })
                        ) {
                        val id = it.arguments?.getString("id")
                        val response = detailViewModel.getDetail(id!!)
                        DetailScreen(
                            id,
                            viewModel = detailViewModel,
                        )
                    }

                }


            }
        }
    }
}

