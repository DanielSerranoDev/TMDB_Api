package com.example.tmdb_api.ui.main

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccessTimeFilled
import androidx.compose.material.icons.filled.Bookmarks
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.LocalFireDepartment
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tmdb_api.R

@Composable
fun MainScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel
) {

    Scaffold(
        topBar = { Topbar() },

        content = { paddingValues ->
            Content(paddingValues = paddingValues, homeViewModel = viewModel)
        },
        bottomBar = { BottomBar() }
    )

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Topbar() {
    TopAppBar(
        title = { Text(text = "TMDB_API", fontSize = 30.sp, color = Color.White) },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = colorResource(id = R.color.backgroundBars),
        )
    )


}


@Composable
fun Content(paddingValues: PaddingValues, homeViewModel: HomeViewModel) {

    Box(modifier = Modifier.padding(paddingValues)) {
        HomeScreen(homeViewModel)
    }

}

@Composable
fun BottomBar() {
    BottomAppBar(
        containerColor = colorResource(id = R.color.backgroundBars),
        contentColor = Color.White

    ) {
        NavigationBarItem(
            selected = false,
            onClick = { /*TODO*/ },
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
            onClick = { /*TODO*/ },
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
            onClick = { /*TODO*/ },
            icon = {
                Column(
                    modifier = Modifier,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    Icon(
                        imageVector = Icons.Default.LocalFireDepartment,
                        contentDescription = null,
                        modifier = Modifier.size(24.dp)

                    )
                    Text(text = "Popular")
                }
            })
        NavigationBarItem(
            selected = false,
            onClick = { /*TODO*/ },
            icon = {
                Column(
                    modifier = Modifier,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    Icon(
                        imageVector = Icons.Default.Bookmarks,
                        contentDescription = null,
                        modifier = Modifier.size(24.dp)

                    )
                    Text(text = "List")
                }
            })


    }


}


@Preview
@Composable
private fun MainScreen_Preview() {
    //MainScreen()
}