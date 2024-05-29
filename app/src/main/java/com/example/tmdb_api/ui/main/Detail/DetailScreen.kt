package com.example.tmdb_api.ui.main.Detail

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.SmallFloatingActionButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.example.tmdb_api.R
import com.example.tmdb_api.domain.models.ResponseRemoteUI

@Composable
fun DetailScreen(
    id: String,
    viewModel: DetailViewModel
) {
    val showId = id
    val state by viewModel.state.collectAsState()


    when (state) {
        is DetailState.Success -> {
            val responseRepository = (state as DetailState.Success).data

            DetailComponents(responseRepository)

        }

        is DetailState.Error -> {
            var error = (state as DetailState.Error).error
            Log.d("Error", error)
        }
        DetailState.Idle -> {
            Log.d("State", "Idle")
        }
    }


}

@Composable
fun DetailComponents(
    responseRepository: ResponseRemoteUI?
) {
    var favorite by remember {mutableStateOf(false)}
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(id = R.color.backgroundBars))
    ) {
        LazyColumn(
        ) {

            item() {
                Box(
                    modifier = Modifier
                        .fillParentMaxWidth()
                        .size(220.dp)
                        .background(colorResource(id = R.color.black))
                ) {
                    Image(
                        painter = rememberAsyncImagePainter(
                            model = ImageRequest.Builder(LocalContext.current)
                                .data(responseRepository?.imageSet?.horizontalPoster?.w360)
                                .apply { crossfade(true) }
                                .build()
                        ),
                        contentDescription = "show image",
                        modifier = Modifier.fillMaxSize(),
                        contentScale = ContentScale.FillWidth,
                    )
                }

            }
            item() {
                Row (
                    modifier = Modifier
                        .fillParentMaxWidth()
                        .padding(16.dp,10.dp)
                ){
                    SmallFloatingActionButton(
                        onClick = { /*TODO*/ },
                        containerColor = Color.Transparent,
                        modifier = Modifier.size(64.dp)
                    ) {
                        Box(
                            contentAlignment = Alignment.Center,
                            modifier = Modifier.size(64.dp)
                        ) {
                            Column(
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Star,
                                    contentDescription = "rating icon",
                                    tint = Color.Yellow,
                                    modifier = Modifier.size(36.dp)
                                )
                                Text(
                                        text = "${responseRepository?.rating.toString()}/100",
                                    color = Color.White,
                                    fontSize = 16.sp
                                )
                            }
                        }
                    }

                    SmallFloatingActionButton(
                        onClick = {
                            favorite = if(favorite){
                                false
                            }else{
                                true
                            }
                                                },
                        containerColor = Color.Transparent,
                        modifier = Modifier.size(64.dp)
                    ) {
                        Box(
                            contentAlignment = Alignment.Center,
                            modifier = Modifier
                                .size(64.dp)
                        ) {
                            Column(
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                if(favorite){
                                    Icon(
                                        imageVector = Icons.Default.Favorite,
                                        contentDescription = "Favorite icon",
                                        tint = Color.Red,
                                        modifier = Modifier
                                            .size(36.dp)
                                    )
                                }else{
                                    Icon(
                                        imageVector = Icons.Default.FavoriteBorder,
                                        contentDescription = "Favorite icon",
                                        tint = Color.Red,
                                        modifier = Modifier
                                            .size(36.dp)
                                    )
                                }
                                Text(
                                    text = "Favorite",
                                    color = Color.White,
                                    fontSize = 16.sp
                                )
                            }
                        }
                    }


                }
            }
            item(){
                Text(
                    text = responseRepository?.title.toString(),
                    color = Color.White,
                    fontSize = 32.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(16.dp,10.dp)
                )
            }
            item(){
                Text(
                    text = "Release Year: ${responseRepository?.releaseYear.toString()}",
                    color = Color.White,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(16.dp,10.dp)
                )

            }

            item(){
                LazyRow {
                    items(responseRepository?.genres!!){
                        OutlinedButton(onClick = { /*TODO*/ }) {
                            Text(text = it.name)
                        }
                    }
                }
            }
            item(){
                Text(
                    text = "Lead Starring",
                    color = Color.White,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(10.dp,5.dp)
                )
            }
            item {
                LazyRow {
                    items(responseRepository?.cast ?: emptyList()) { castMember ->
                        OutlinedButton(
                            onClick = { /*TODO*/ },
                            modifier = Modifier
                                .padding(1.dp) // Ajusta el padding entre botones si es necesario
                                .height(28.dp) // Ajusta la altura del botón
                        ) {
                            Text(
                                text = castMember,
                                fontSize = 12.sp, // Ajusta el tamaño del texto
                                modifier = Modifier.padding(horizontal = 8.dp) // Ajusta el padding dentro del botón
                            )
                        }
                    }
                }
            }


            item(){
                Text(
                    text = "Synopsis",
                    color = Color.White,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(10.dp,5.dp)
                    )
            }

            item(){
                Text(
                    text = responseRepository?.overview.toString(),
                    color = Color.White,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(10.dp,5.dp),
                )
            }



            item(){
                Text(
                    text = "Directors",
                    color = Color.White,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(10.dp,5.dp)
                )
            }
            item(){
                Text(
                    text = responseRepository?.directors.toString(),
                    color = Color.White,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(10.dp,5.dp),
                )

            }





        }

    }
}

@Preview
@Composable
private fun DetailScreen_Preview() {
    //DetailComponents()
}