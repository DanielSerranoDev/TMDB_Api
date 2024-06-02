package com.example.tmdb_api.ui.main

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccessTimeFilled
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.tmdb_api.R
import com.example.tmdb_api.ui.main.Home.HomeScreen
import com.example.tmdb_api.ui.main.Home.HomeViewModel
import com.example.tmdb_api.ui.main.Favorites.FavoritesShowsScreen
import com.example.tmdb_api.ui.main.NewShows.NewShowsScreen
import com.example.tmdb_api.ui.main.NewShows.NewShowsViewModel

@Composable
fun MainScreen(
    modifier: Modifier = Modifier,
    homeViewModel: HomeViewModel,
    newShowsViewModel: NewShowsViewModel,
    navController: NavController
) {
    var selectedScreen by remember { mutableStateOf("HomeScreen") }

    Scaffold(
        topBar = { TopBar() },

        content = { paddingValues ->

            Content(
                paddingValues = paddingValues,
                homeViewModel = homeViewModel,
                newShowsViewModel = newShowsViewModel,
                navController,
                selectedScreen
            )


        },
        bottomBar = { BottomBar() { screen -> selectedScreen = screen } }
    )

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar() {
    TopAppBar(
        title = { Text(text = "TMDB_API", fontSize = 30.sp, color = Color.White) },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = colorResource(id = R.color.backgroundBars),
        )
    )


}


@Composable
fun Content(
    paddingValues: PaddingValues,
    homeViewModel: HomeViewModel,
    newShowsViewModel: NewShowsViewModel,
    navController: NavController,
    selectedScreen: String
) {

    Box(modifier = Modifier.padding(paddingValues)) {

        when (selectedScreen) {

            "HomeScreen" -> {
                HomeScreen(homeViewModel, navController)
                homeViewModel.getRepositoryData()

            }
            "NewShowsScreen" -> {
                NewShowsScreen(
                    newShowsViewModel, navController)
                newShowsViewModel.getRepositoryData()
                Log.w("Repository", "NewShowsScreen")
            }
            "FavoritesShowsScreen" -> FavoritesShowsScreen()
            "SettingsScreen" -> FavoritesShowsScreen()

        }
    }


}


@Composable
fun BottomBar(
    onScreenSelected: (String) -> Unit
) {
    BottomAppBar(
        containerColor = colorResource(id = R.color.backgroundBars),
        contentColor = Color.White

    ) {
        NavigationBarItem(
            selected = false,
            onClick = { onScreenSelected("HomeScreen") },
            icon = {
                Column(
                    modifier = Modifier,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    Icon(
                        imageVector = Icons.Default.Home,
                        contentDescription = null,
                        modifier = Modifier.size(24.dp)

                    )
                    Text(text = "Home")
                }

            })
        NavigationBarItem(
            selected = false,
            onClick = { onScreenSelected("NewShowsScreen")},
            icon = {
                Column(
                    modifier = Modifier,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    Icon(
                        imageVector = Icons.Default.AccessTimeFilled,
                        contentDescription = null,
                        modifier = Modifier.size(24.dp)

                    )
                    Text(text = "Nuevo")
                }
            })
        NavigationBarItem(
            selected = false,
            onClick = { onScreenSelected("FavoritesShowsScreen")},
            icon = {
                Column(
                    modifier = Modifier,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    Icon(
                        imageVector = Icons.Default.Favorite,
                        contentDescription = null,
                        modifier = Modifier.size(24.dp)

                    )
                    Text(text = "Favorites")
                }
            })
        NavigationBarItem(
            selected = false,
            onClick = { onScreenSelected("SettingsScreen")},
            icon = {
                Column(
                    modifier = Modifier,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    Icon(
                        imageVector = Icons.Default.Settings,
                        contentDescription = null,
                        modifier = Modifier.size(24.dp)

                    )
                    Text(text = "Settings")
                }
            })


    }


}


@Preview
@Composable
private fun MainScreen_Preview() {
    //MainScreen()
}