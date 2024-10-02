package com.example.tmdb_api.ui.main.Detail

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SmallFloatingActionButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.example.tmdb_api.R
import com.example.tmdb_api.domain.models.ResponseLocalUI
import com.example.tmdb_api.domain.models.ResponseRemoteUI
import com.example.tmdb_api.ui.main.TopBar

@Composable
fun DetailScreen(
    id: String,
    detailViewModel: DetailViewModel,
) {
    val state by detailViewModel.state.collectAsState()

    when (state) {
        is DetailState.Success -> {
            val responseRepository = (state as DetailState.Success).data

            Scaffold(
                topBar = { TopBar() },
                content = { paddingValues ->
                    DetailComponents(
                        paddingValues,
                        responseRepository,
                        detailViewModel
                    )
                }
            )
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
    paddingValues: PaddingValues,
    responseRepository: ResponseRemoteUI?,
    detailViewModel: DetailViewModel,
) {
    var show by remember { mutableStateOf<ResponseLocalUI?>(null) }

    LaunchedEffect(responseRepository?.id) {
        show = detailViewModel.getShowById(responseRepository?.id.toString())
    }


    Box(
        modifier =
        Modifier
            .fillMaxSize()
            .background(colorResource(id = R.color.background))
            .padding(paddingValues),
    ) {
        LazyColumn {
            item {
                Box(
                    modifier =
                    Modifier
                        .background(colorResource(id = R.color.background))
                        .height(320.dp)
                ) {

                    Image(
                        painter =
                        rememberAsyncImagePainter(
                            model =
                            ImageRequest
                                .Builder(LocalContext.current)
                                .data(responseRepository?.imageSet?.horizontalPoster?.w480)
                                .apply { crossfade(true) }
                                .build(),
                        ),
                        contentDescription = "show image",
                        modifier = Modifier
                            .align(Alignment.BottomCenter)
                            .width(400.dp)
                            .height(280.dp),
                        contentScale = ContentScale.FillBounds,
                    )
                }
            }
            item {
                Text(
                    text = responseRepository?.title.toString(),
                    color = Color.White,
                    fontSize = 32.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(16.dp, 10.dp),
                )
            }
            item {
                Row(
                    modifier =
                    Modifier
                        .fillParentMaxWidth()
                        .padding(1.dp, 5.dp),
                ) {
                    SmallFloatingActionButton(
                        onClick = { /*TODO*/ },
                        containerColor = Color.Transparent,
                        modifier = Modifier.size(64.dp),
                    ) {
                        Box(
                            contentAlignment = Alignment.Center,
                            modifier = Modifier.size(64.dp),
                        ) {
                            Column(
                                horizontalAlignment = Alignment.CenterHorizontally,
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Star,
                                    contentDescription = "rating icon",
                                    tint = Color.Yellow,
                                    modifier = Modifier.size(32.dp),
                                )
                                Text(
                                    text = "${responseRepository?.rating}/100",
                                    color = Color.White,
                                    fontSize = 12.sp,
                                )
                            }
                        }
                    }

                    SmallFloatingActionButton(
                        onClick = {
                            if (show != null) {
                                detailViewModel.deleteShow(responseRepository?.id!!)
                            } else {
                                detailViewModel.insertShow(responseRepository)
                            }
                        },
                        containerColor = Color.Transparent,
                        modifier = Modifier.size(64.dp),
                    ) {
                        Box(
                            contentAlignment = Alignment.Center,
                            modifier =
                            Modifier
                                .size(64.dp),
                        ) {
                            Column(
                                horizontalAlignment = Alignment.CenterHorizontally,
                            ) {
                                if (show != null) {
                                    Icon(
                                        imageVector = Icons.Default.FavoriteBorder,
                                        contentDescription = "Favorite icon",
                                        tint = Color.Red,
                                        modifier =
                                        Modifier
                                            .size(36.dp),
                                    )
                                } else {
                                    Icon(
                                        imageVector = Icons.Default.Favorite,
                                        contentDescription = "Favorite icon",
                                        tint = Color.Red,
                                        modifier =
                                        Modifier
                                            .size(31.dp),
                                    )
                                }
                                Text(
                                    text = "Favorite",
                                    color = Color.White,
                                    fontSize = 12.sp,
                                )
                            }
                        }
                    }
                }
            }

            item {
                Text(
                    text = "Release Year",
                    color = Color.White,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(10.dp, 5.dp)
                )
            }

            item() {
                OutlinedButton(
                    onClick = { /*TODO*/ },
                    modifier =
                    Modifier
                        .padding(10.dp, 0.dp)
                        .height(30.dp),
                ) {
                    Text(
                        text = responseRepository?.releaseYear.toString(),
                        fontSize = 12.sp,
                        modifier = Modifier
                            .padding(horizontal = 8.dp),
                    )
                }
            }


            item {
                Text(
                    text = "Genres",
                    color = Color.White,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(10.dp, 5.dp),
                )
            }

            item {
                LazyRow(
                    modifier = Modifier.padding(10.dp, 0.dp)
                ) {
                    items(responseRepository?.genres!!) {
                        OutlinedButton(
                            onClick = { /*TODO*/ },
                            modifier =
                            Modifier
                                .padding(1.dp)
                                .height(30.dp),
                        ) {
                            Text(
                                text = it.name,
                                fontSize = 12.sp,
                                modifier = Modifier
                                    .padding(horizontal = 8.dp),
                            )
                        }
                    }
                }
            }

            item {
                Text(
                    text = "Directors",
                    color = Color.White,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(10.dp, 5.dp),
                )
            }
            item {

                LazyRow(
                    modifier = Modifier.padding(10.dp, 0.dp)
                ) {
                    items(responseRepository?.directors ?: emptyList()) { directorMember ->
                        OutlinedButton(
                            onClick = { /*TODO*/ },
                            modifier =
                            Modifier
                                .padding(1.dp)
                                .height(30.dp),
                        ) {
                            Text(
                                text = directorMember,
                                fontSize = 12.sp,
                                modifier = Modifier.padding(horizontal = 8.dp),
                            )
                        }
                    }
                }
            }
            item {
                Text(
                    text = "Lead Starring",
                    color = Color.White,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(10.dp, 5.dp),
                )
            }
            item {
                LazyRow(
                    modifier = Modifier.padding(10.dp, 0.dp)
                ) {
                    items(responseRepository?.cast ?: emptyList()) { castMember ->
                        OutlinedButton(
                            onClick = { /*TODO*/ },
                            modifier =
                            Modifier
                                .height(30.dp),
                        ) {
                            Text(
                                text = castMember,
                                fontSize = 12.sp,
                                modifier = Modifier.padding(horizontal = 8.dp),
                            )
                        }
                    }
                }
            }

            item {
                Text(
                    text = "Synopsis",
                    color = Color.White,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(10.dp, 5.dp),
                )
            }

            item {
                Text(
                    text = responseRepository?.overview.toString(),
                    color = Color.White,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(10.dp, 5.dp),
                )
            }


        }
    }
}

@Preview
@Composable
private fun DetailScreen_Preview() {
    // DetailComponents()
}
