package com.sorabh.movieapp.screen.home

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.sorabh.movieapp.model.Movie
import com.sorabh.movieapp.model.getMovies
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
            modifier = Modifier.padding(paddingValues = innerPadding),
            movies = getMovies()
        )
    }
}

@Composable
fun MainContent(
    navController: NavController,
    modifier: Modifier = Modifier,
    movies: List<Movie> = listOf()
) {
    LazyColumn(modifier = modifier.fillMaxWidth()) {
        items(movies) { movie ->
            MovieCard(movie = movie) { movieId ->
                navController.navigate(MovieScreens.DetailsScreen.name + "/$movieId")
            }
        }
    }
}

@Preview
@Composable
fun MovieCard(
    modifier: Modifier = Modifier,
    movie: Movie = Movie(
        id = "tt0499549",
        title = "Avatar",
        year = "2009",
        genre = "Action, Adventure, Fantasy",
        director = "James Cameron",
        actors = "Sam Worthington, Zoe Saldana, Sigourney Weaver, Stephen Lang",
        plot = "A paraplegic marine dispatched to the moon Pandora on a unique mission becomes torn between following his orders and protecting the world he feels is his home.",
        poster = "http://ia.media-imdb.com/images/M/MV5BMTYwOTEwNjAzMl5BMl5BanBnXkFtZTcwODc5MTUwMw@@._V1_SX300.jpg",
        images = listOf(
            "https://images-na.ssl-images-amazon.com/images/M/MV5BMjEyOTYyMzUxNl5BMl5BanBnXkFtZTcwNTg0MTUzNA@@._V1_SX1500_CR0,0,1500,999_AL_.jpg",
            "https://images-na.ssl-images-amazon.com/images/M/MV5BNzM2MDk3MTcyMV5BMl5BanBnXkFtZTcwNjg0MTUzNA@@._V1_SX1777_CR0,0,1777,999_AL_.jpg",
            "https://images-na.ssl-images-amazon.com/images/M/MV5BMTY2ODQ3NjMyMl5BMl5BanBnXkFtZTcwODg0MTUzNA@@._V1_SX1777_CR0,0,1777,999_AL_.jpg",
            "https://images-na.ssl-images-amazon.com/images/M/MV5BMTMxOTEwNDcxN15BMl5BanBnXkFtZTcwOTg0MTUzNA@@._V1_SX1777_CR0,0,1777,999_AL_.jpg",
            "https://images-na.ssl-images-amazon.com/images/M/MV5BMTYxMDg1Nzk1MV5BMl5BanBnXkFtZTcwMDk0MTUzNA@@._V1_SX1500_CR0,0,1500,999_AL_.jpg"
        ),
        rating = "7.9"
    ),
    onMovieClicked: (String) -> Unit = {}
) {
    var expanded by rememberSaveable { mutableStateOf(false) }
    Card(
        modifier = modifier
            .fillMaxWidth()
            .heightIn(min = 120.dp)
            .padding(horizontal = 12.dp, vertical = 6.dp)
            .clickable { onMovieClicked(movie.id) },
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
                AsyncImage(
                    model = movie.images[0],
                    contentScale = ContentScale.Crop,
                    contentDescription = "content description"
                )
            }
            Column(
                modifier = Modifier
                    .padding(all = 10.dp)
            ) {
                Text(
                    text = movie.title,
                    style = MaterialTheme.typography.h6
                )
                Text(
                    text = "Director : ${movie.director}",
                    modifier = Modifier.padding(top = 10.dp),
                    style = MaterialTheme.typography.subtitle1
                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "Released : ${movie.year}",
                        style = MaterialTheme.typography.body1
                    )

                    IconButton(
                        onClick = { expanded = !expanded },
                    ) {
                        Icon(
                            imageVector = if (expanded) Icons.Default.KeyboardArrowUp else Icons.Default.KeyboardArrowDown,
                            contentDescription = "",
                            modifier = Modifier.size(25.dp)
                        )
                    }
                }
                AnimatedVisibility(visible = expanded) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 10.dp)
                    ) {
                        Text(
                            text = "Genre : ${movie.genre}",
                            style = MaterialTheme.typography.subtitle1
                        )
                        Text(
                            text = "Rating : ${movie.rating}",
                            modifier = Modifier.padding(top = 10.dp),
                            style = MaterialTheme.typography.subtitle1
                        )
                    }
                }
            }
        }
    }
}


