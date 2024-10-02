package com.example.tmdb_api.ui.main.Home

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.example.tmdb_api.R
import com.example.tmdb_api.domain.models.ResponseRemoteUI
import com.example.tmdb_api.ui.main.RatingFloatingButton


@Composable
fun HomeScreen(
    viewModel: HomeViewModel,
    navController: NavController,
) {
    val state by viewModel.state.collectAsState()

    when (state) {
        is HomeState.Success -> {
            val responseRepository = (state as HomeState.Success).data
            var netflixRating = responseRepository.netflixRatings
            var amazonPrimeRating = responseRepository.amazonPrimeRatings
            var hboMaxRating = responseRepository.hboMaxRatings

            HomeScreenComponents(netflixRating, amazonPrimeRating, hboMaxRating, navController)
        }

        is HomeState.Error -> {
            var error = (state as HomeState.Error).error
            Log.d("Error", error)
        }

        HomeState.Idle -> {
            Log.d("State", "Idle")
        }
    }

}

@Composable
fun HomeScreenComponents(
    movies: List<ResponseRemoteUI>,
    series: List<ResponseRemoteUI>,
    moviesAction: List<ResponseRemoteUI>,
    navController: NavController,
) {
    //Box
    val sizeBoxWidth = 170.dp
    val sizeBoxHeight = 240.dp
    //
    //FloatingActionButton Ratings
    val sizeWidth = 45
    val sizeHeight = 22
    val paddingStart = 6
    val paddingBottom = 6
    val fontSize = 14
    val fontColor = Color.White
    val paddingBehindElements = 4


    Box(
        modifier = Modifier.background(colorResource(id = R.color.background))
    ) {
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            //First item
            item {

                Row {
                    Text(
                        text = "The best ratings from ",
                        color = Color.White,
                        fontWeight = FontWeight.Bold,
                        style = TextStyle(
                            fontWeight = FontWeight.Bold,
                            fontStyle = FontStyle.Normal,
                            fontSize = 19.sp
                        ),
                        modifier = Modifier
                            .align(Alignment.CenterVertically)
                            .padding(16.dp)
                    )

                    Image(
                        painter = painterResource(id = R.drawable.netflix),
                        contentDescription = "Netflix",
                        modifier = Modifier
                            .clickable { }
                            .size(128.dp, 64.dp),
                    )

                }
                LazyRow(modifier = Modifier.padding(8.dp)) {
                    items(movies) {
                        Box(
                            modifier =
                            Modifier
                                .padding(paddingBehindElements.dp)
                                .size(sizeBoxWidth, sizeBoxHeight)
                                .clip(RoundedCornerShape(16.dp)),
                        ) {
                            Image(
                                painter =
                                rememberAsyncImagePainter(
                                    model =
                                    ImageRequest
                                        .Builder(LocalContext.current)
                                        .data(it.imageSet?.verticalPoster?.w240)
                                        .apply { crossfade(true) }
                                        .build(),
                                ),
                                contentDescription = "Film image",
                                modifier =
                                Modifier
                                    .fillMaxSize()
                                    .background(colorResource(id = R.color.black))
                                    .clickable {
                                        navController.navigate("detail/${it.id}")
                                    },
                                contentScale = ContentScale.Crop,
                            )
                            RatingFloatingButton(
                                text = it.rating.toString(),
                                sizeWidth = sizeWidth,
                                sizeHeight = sizeHeight,
                                paddingStart = paddingStart,
                                paddingBottom = paddingBottom,
                                fontSize = fontSize,
                                fontColor = fontColor,
                                modifier = Modifier.align(Alignment.BottomStart)
                            )
                        }
                    }
                }
            }
            //Second item
            item {

                Row {
                    Text(
                        text = "The selections from   ",
                        color = Color.White,
                        fontWeight = FontWeight.Bold,
                        style = TextStyle(
                            fontWeight = FontWeight.Bold,
                            fontStyle = FontStyle.Normal,
                            fontSize = 19.sp
                        ),
                        modifier = Modifier
                            .align(Alignment.CenterVertically)
                            .padding(16.dp)
                    )

                    Image(
                        painter = painterResource(id = R.drawable.amazon),
                        contentDescription = "Amazon",
                        modifier = Modifier
                            .clickable {  }
                            .size(128.dp,  64.dp),
                    )

                }


                LazyRow(modifier = Modifier.padding(8.dp)) {
                    items(series) {
                        Box(
                            modifier = Modifier
                                .padding(paddingBehindElements.dp)
                                .size(sizeBoxWidth, sizeBoxHeight)
                                .clip(RoundedCornerShape(16.dp))
                        ) {
                            Image(
                                painter = rememberAsyncImagePainter(
                                    model = ImageRequest.Builder(LocalContext.current)
                                        .data(it.imageSet?.verticalPoster?.w240)
                                        .apply { crossfade(true) }
                                        .build()
                                ),
                                contentDescription = "Film image",
                                modifier = Modifier
                                    .fillMaxSize()
                                    .background(colorResource(id = R.color.black))
                                    .clickable {
                                        navController.navigate("detail/${it.id}")
                                    },
                                contentScale = ContentScale.Crop,
                            )
                            RatingFloatingButton(
                                text = it.rating.toString(),
                                sizeWidth = sizeWidth,
                                sizeHeight = sizeHeight,
                                paddingStart = paddingStart,
                                paddingBottom = paddingBottom,
                                fontSize = fontSize,
                                fontColor = fontColor,
                                modifier = Modifier.align(Alignment.BottomStart)
                            )
                        }
                    }
                }
            }
            //First third
            item {

                Row {
                    Text(
                        text = "The top from               ",
                        color = Color.White,
                        fontWeight = FontWeight.Bold,
                        style = TextStyle(
                            fontWeight = FontWeight.Bold,
                            fontStyle = FontStyle.Normal,
                            fontSize = 19.sp
                        ),
                        modifier = Modifier
                            .align(Alignment.CenterVertically)
                            .padding(16.dp)
                    )

                    Image(
                        painter = painterResource(id = R.drawable.hbologo),
                        contentDescription = "HboMax",
                        modifier = Modifier
                            .clickable {  }
                            .size(100.dp, 64.dp)
                            .padding(38.dp, 8.dp,0.dp,0.dp)
                    )

                }

                LazyRow(modifier = Modifier.padding(8.dp)) {
                    items(moviesAction) {
                        Box(
                            modifier = Modifier
                                .padding(paddingBehindElements.dp)
                                .size(sizeBoxWidth, sizeBoxHeight)
                                .clip(RoundedCornerShape(16.dp))
                        ) {
                            Image(
                                painter = rememberAsyncImagePainter(
                                    model = ImageRequest.Builder(LocalContext.current)
                                        .data(it.imageSet?.verticalPoster?.w240)
                                        .apply { crossfade(true) }
                                        .build()
                                ),
                                contentDescription = "Film image",
                                modifier = Modifier
                                    .fillMaxSize()
                                    .background(colorResource(id = R.color.black))
                                    .clickable {
                                        navController.navigate("detail/${it.id}")
                                    },
                                contentScale = ContentScale.Crop,
                            )
                            RatingFloatingButton(
                                text = it.rating.toString(),
                                sizeWidth = sizeWidth,
                                sizeHeight = sizeHeight,
                                paddingStart = paddingStart,
                                paddingBottom = paddingBottom,
                                fontSize = fontSize,
                                fontColor = fontColor,
                                modifier = Modifier.align(Alignment.BottomStart)
                            )
                        }
                    }
                }
            }
        }
    }
}

@Preview
@Composable
private fun HomeScreen_Preview() {
    // HomeScreenComponents()
}