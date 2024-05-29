package com.example.tmdb_api.ui.main

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.SmallFloatingActionButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun RatingFloatingButton(
    text: String,
    sizeWidth: Int,
    sizeHeight: Int,
    paddingStart: Int,
    paddingBottom: Int,
    fontSize: Int,
    fontColor: Color,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .padding(start = paddingStart.dp, bottom = paddingBottom.dp)
    ) {
        SmallFloatingActionButton(
            onClick = { /*TODO*/ },
            modifier = Modifier.size(sizeWidth.dp, sizeHeight.dp),
            containerColor = Color.Black,
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,

            ){
                Icon(
                    imageVector= Icons.Default.Star,
                    contentDescription = "start rating",
                    modifier = Modifier,
                    tint = Color.Yellow
                )
                Text(
                    text = text,
                    color = fontColor,
                    fontWeight = FontWeight.Bold,
                    fontSize = fontSize.sp,
                )

            }

        }
    }
}

@Preview
@Composable
private fun Components_Preview() {
    RatingFloatingButton(
        text = "50",
        sizeWidth = 50,
        sizeHeight = 50,
        paddingStart = 16,
        paddingBottom = 16,
        fontSize = 12,
        fontColor = Color.White,
    )
}