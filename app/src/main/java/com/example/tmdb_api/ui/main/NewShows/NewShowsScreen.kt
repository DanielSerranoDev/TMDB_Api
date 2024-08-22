package com.example.tmdb_api.ui.main.NewShows

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.example.tmdb_api.R
import com.example.tmdb_api.domain.models.ResponseRemoteUI
import com.example.tmdb_api.ui.main.RatingFloatingButton
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Composable
fun NewShowsScreen(
    newShowsViewModel: NewShowsViewModel,
    navController: NavController,
) {
    val state by newShowsViewModel.state.collectAsState()

    LaunchedEffect(Unit) {
        newShowsViewModel.getRepositoryData()
    }

    when (state) {
        is NewShowsStates.Success -> {
            val responseRepository = (state as NewShowsStates.Success).data
            Log.d("Success", responseRepository.toString())
            NewShowsScreenComponents(responseRepository, navController)
        }

        is NewShowsStates.Error -> {
            val error = (state as NewShowsStates.Error).error
            Log.d("Error", error)
        }

        NewShowsStates.Idle -> {
            Log.d("State", "Idle")
        }
    }
}

@Composable
fun NewShowsScreenComponents(
    responseRepository: List<ResponseRemoteUI>,
    navController: NavController,
) {
    // Box
    val sizeBoxWidth = 250.dp
    val sizeBoxHeight = 350.dp
    //
    // FloatingActionButton Ratings
    val sizeWidth = 50
    val sizeHeight = 22
    val paddingStart = 8
    val paddingBottom = 8
    val fontSize = 14
    val fontColor = Color.White

    Box(
        modifier =
        Modifier
            .background(colorResource(id = R.color.black)),
    ) {
        LazyColumn(
            modifier =
            Modifier
                .fillMaxSize(),
        ) {
            // Items from responseRepository
            items(responseRepository) { item ->
                Column(
                    modifier =
                    Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                ) {
                    Box(
                        modifier =
                        Modifier
                            .padding(8.dp)
                            .size(sizeBoxWidth, sizeBoxHeight)
                            .clip(RoundedCornerShape(16.dp))
                            .align(Alignment.CenterHorizontally), // Centering the box within the column
                    ) {
                        Image(
                            painter =
                            rememberAsyncImagePainter(
                                model =
                                ImageRequest
                                    .Builder(LocalContext.current)
                                    .data(item.imageSet?.verticalPoster?.w240)
                                    .apply { crossfade(true) }
                                    .build(),
                            ),
                            contentDescription = "Film image",
                            modifier =
                            Modifier
                                .fillMaxSize()
                                .background(colorResource(id = R.color.black))
                                .clickable {
                                    navController.navigate("detail/${item.id}")
                                },
                            contentScale = ContentScale.FillBounds,
                        )
                        RatingFloatingButton(
                            text = item.rating.toString(),
                            sizeWidth = sizeWidth,
                            sizeHeight = sizeHeight,
                            paddingStart = paddingStart,
                            paddingBottom = paddingBottom,
                            fontSize = fontSize,
                            fontColor = fontColor,
                            modifier = Modifier.align(Alignment.BottomStart),
                        )
                    }

                    Column(
                        modifier =
                        Modifier
                            .fillMaxWidth()
                            .padding(64.dp, 0.dp)
                            .align(Alignment.CenterHorizontally),
                    ) {
                        item.streamingOptions.us.forEach { streamingOption ->
                            Text(
                                text = "Platform: ${streamingOption.service.name}",
                            )

                            if (streamingOption.type != "null" &&
                                streamingOption.price
                                    .toString()
                                    .isNotEmpty()
                            ) {
                                Text(
                                    text = "Service: ${streamingOption.type}",
                                )
                            }
                            if (streamingOption.price != null) {
                                Text(
                                    text = "Amount: ${streamingOption.price?.amount}",
                                )
                            }
                            Text(
                                text = "Date: ${convertTimestampToReadableDate(streamingOption.availableSince)}",
                            )
                            Text(
                                text = "--------------------------------",
                            )
                        }
                    }
                }
            }
        }
    }
}

fun convertTimestampToReadableDate(timestamp: Long): String {
    val date = Date(timestamp * 1000)
    val format = SimpleDateFormat("dd-MM-yyyy", Locale.getDefault())
    return format.format(date)
}

@Preview
@Composable
private fun NewShowsScreen_Preview() {
    // NewShowsScreen()
}
