package com.example.tmdb_api.ui.main.NewShows

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
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
import androidx.compose.ui.res.painterResource
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
    navController: NavController
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
    navController: NavController
) {
    // Box
    val sizeBoxWidth = 190.dp
    val sizeBoxHeight = 250.dp
    //
    // FloatingActionButton Ratings
    val sizeWidth = 45
    val sizeHeight = 22
    val paddingStart = 8
    val paddingBottom = 8
    val fontSize = 14
    val fontColor = Color.White

    Box(
        modifier = Modifier.background(colorResource(id = R.color.black))
    ) {
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            // First item
            // Items from responseRepository
            items(responseRepository) { item ->
                Box(
                    modifier = Modifier
                        .padding(8.dp)
                        .size(sizeBoxWidth, sizeBoxHeight)
                        .clip(RoundedCornerShape(16.dp))
                ) {

                    Image(
                        painter = rememberAsyncImagePainter(
                            model = ImageRequest.Builder(LocalContext.current)
                                .data(item.imageSet?.verticalPoster?.w240)
                                .apply { crossfade(true) }
                                .build()
                        ),
                        contentDescription = "Film image",
                        modifier = Modifier
                            .fillMaxSize()
                            .background(colorResource(id = R.color.black))
                            .clickable {
                                navController.navigate("detail/${item.id}")
                            },
                        contentScale = ContentScale.Crop,
                    )
                    RatingFloatingButton(
                        text = item.rating.toString(),
                        sizeWidth = sizeWidth,
                        sizeHeight = sizeHeight,
                        paddingStart = paddingStart,
                        paddingBottom = paddingBottom,
                        fontSize = fontSize,
                        fontColor = fontColor,
                        modifier = Modifier.align(Alignment.BottomStart)
                    )
                }

                Column {
                    item.streamingOptions.us.forEach { streamingOption ->
                        Text(text = streamingOption.service.name)
                        Row(){
                            Text(text = streamingOption.type.toString())
                            Text(text = streamingOption.price?.amount.toString())
                            Text(text = convertTimestampToReadableDate(streamingOption.availableSince))
                        }


                    }
                }
            }
        }
    }
}


fun convertTimestampToReadableDate(timestamp: Long): String {
    val date = Date(timestamp * 1000)
    val format = SimpleDateFormat("dd-MM-yyyy HH:mm:ss", Locale.getDefault())
    return format.format(date)
}

@Preview
@Composable
private fun NewShowsScreen_Preview() {
    // NewShowsScreen()
}
