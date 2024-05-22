package com.example.tmdb_api.ui.main

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.example.tmdb_api.R
import com.example.tmdb_api.domain.models.Show


@Composable
fun HomeScreen(viewModel: HomeViewModel) {
    val state by viewModel.state.collectAsState()

    when (state) {
        is HomeState.Success -> {
            val responseRepository = (state as HomeState.Success).data
            var movies = responseRepository.sciFiMovies
            var series = responseRepository.comedySeries
            var moviesAction = responseRepository.actionMovies

            HomeScreenComponents(movies, series, moviesAction)

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
fun HomeScreenComponents(movies: List<Show>, series: List<Show>, moviesAction: List<Show>) {

    Box(
        modifier = Modifier
            .background(colorResource(id = R.color.black))

    ) {
        LazyColumn(modifier = Modifier.fillMaxSize()) {

            item() {
                Text(
                    text = "Peliculas de Sci-Fi",
                    fontSize = 20.sp,
                    color = Color.White,
                    modifier = Modifier.padding(16.dp, 0.dp),

                    )

                LazyRow(modifier = Modifier.padding(8.dp)) {

                    items(movies) {

                        //Text(text = it.title.toString())
                        Image(
                            painter = rememberAsyncImagePainter(
                                model = ImageRequest.Builder(
                                    LocalContext.current
                                ).data(
                                    it.imageSet?.verticalPoster?.w240
                                ).apply(block = fun ImageRequest.Builder.() {
                                    crossfade(true)
                                    placeholder(R.drawable.ic_launcher_foreground)
                                }).build()
                            ),
                            contentDescription = "Film image",
                            modifier = Modifier
                                .background(colorResource(id = R.color.black))
                                .size(190.dp, 250.dp)
                                .padding(8.dp),
                            contentScale = ContentScale.Crop,
                        )


                    }
                }


            }
            item(series) {
                Text(
                    text = "Series de Comedia recientemente añadidas",
                    fontSize = 20.sp,
                    color = Color.White,
                    modifier = Modifier.padding(16.dp, 0.dp),

                    )

                LazyRow(modifier = Modifier.padding(8.dp)) {

                    items(series) {

                        //Text(text = it.title.toString())
                        Image(
                            painter = rememberAsyncImagePainter(
                                model = ImageRequest.Builder(
                                    LocalContext.current
                                ).data(
                                    it.imageSet?.verticalPoster?.w240
                                ).apply(block = fun ImageRequest.Builder.() {
                                    crossfade(true)
                                    placeholder(R.drawable.ic_launcher_foreground)
                                }).build()
                            ),
                            contentDescription = "Film image",
                            modifier = Modifier
                                .background(colorResource(id = R.color.black))
                                .size(190.dp, 250.dp)
                                .padding(8.dp),
                            contentScale = ContentScale.Crop,
                        )
                    }
                }
            }

            item() {
                Text(
                    text = "Las mejores peliculas de Misterio & Suspense",
                    fontSize = 20.sp,
                    color = Color.White,
                    modifier = Modifier.padding(16.dp, 0.dp),
                )

                LazyRow(modifier = Modifier.padding(8.dp)) {

                    items(moviesAction) {

                        //Text(text = it.title.toString())
                        Image(
                            painter = rememberAsyncImagePainter(
                                model = ImageRequest.Builder(
                                    LocalContext.current
                                ).data(
                                    it.imageSet?.verticalPoster?.w240
                                ).apply(block = fun ImageRequest.Builder.() {
                                    crossfade(true)
                                    placeholder(R.drawable.ic_launcher_foreground)
                                }).build()
                            ),
                            contentDescription = "Film image",
                            modifier = Modifier
                                .background(colorResource(id = R.color.black))
                                .size(190.dp, 250.dp)
                                .padding(8.dp),
                            contentScale = ContentScale.Crop,
                        )
                    }
                }


            }


        }
    }


}


@Preview
@Composable
private fun HomeScreen_Preview() {
    // HomeScreen()
}
