package com.example.tmdb_api.ui.main.Settings

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp

@Composable
fun SettingsScreen() {

    Text(
        text = "Settings Screen",
        fontSize = 20.sp
    )
}

@Preview
@Composable
private fun PopularShowsScreen_Preview() {
    SettingsScreen()
}
