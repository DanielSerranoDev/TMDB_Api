package com.example.tmdb_api.ui.main.Favorites

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp

@Composable
fun FavoritesShowsScreen() {
    Text(
        text = "Favorites Shows",
        fontSize = 20.sp,
    )
}

@Preview
@Composable
private fun NewScreen_Preview() {
    FavoritesShowsScreen()
}
