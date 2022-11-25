package com.sorabh.movieapp.screen.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.sorabh.movieapp.navigation.MovieScreens

@Composable
fun HomeScreen(navController: NavController) {
    Scaffold(modifier = Modifier
        .fillMaxSize()
        .padding(0.dp), topBar = {
        TopAppBar(backgroundColor = MaterialTheme.colors.surface, elevation = 6.dp) {
            Text(
                "Movies",
                modifier = Modifier.padding(start = 10.dp),
                style = MaterialTheme.typography.h6
            )
        }
    }) { innerPadding ->
        MainContent(
            navController = navController,
            Modifier.padding(paddingValues = innerPadding)
        )
    }
}

@Composable
fun MainContent(
    navController: NavController,
    modifier: Modifier = Modifier,
    movies: List<String> = listOf(
        "Faster Fintech",
        "Faster Digital platforms",
        "Faster Holdings",
        "Faster Edtech",
        "Faster FutureTech",
        "Faster CleanTech",
        "Faster IOT",
        "Faster Analytics"
    )
) {
    LazyColumn(modifier = modifier.fillMaxWidth()) {
        items(movies) { movie ->
            MovieCard(movie = movie) { data ->
                navController.navigate(MovieScreens.DetailsScreen.name+"/$data")
            }
        }
    }
}

@Composable
fun MovieCard(
    modifier: Modifier = Modifier,
    movie: String,
    onMovieClicked: (String) -> Unit = {}
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .heightIn(min = 120.dp)
            .padding(horizontal = 12.dp, vertical = 6.dp)
            .clickable { onMovieClicked(movie) },
        shape = RoundedCornerShape(corner = CornerSize(8.dp))
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {
            Surface(
                modifier = Modifier
                    .size(120.dp)
                    .padding(10.dp), elevation = 6.dp,
                shape = RoundedCornerShape(corner = CornerSize(4.dp))
            ) {
                Icon(imageVector = Icons.Default.AccountBox, contentDescription = "movie image")
            }
            Text(
                text = movie,
                modifier = Modifier.padding(start = 10.dp, top = 10.dp),
                fontStyle = MaterialTheme.typography.h6.fontStyle,
                fontSize = MaterialTheme.typography.h6.fontSize
            )
        }

    }
}


