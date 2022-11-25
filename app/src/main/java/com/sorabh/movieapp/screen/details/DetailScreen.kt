package com.sorabh.movieapp.screen.details

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun DetailScreen(navController: NavController, company: String?) {
    Scaffold(modifier = Modifier.fillMaxSize(), topBar = {
        TopAppBar(backgroundColor = MaterialTheme.colors.surface, elevation = 6.dp) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 10.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Arrow back",
                    modifier = Modifier.clickable { navController.popBackStack() })
                Text(
                    company.toString(),
                    modifier = Modifier.padding(start = 10.dp),
                    style = MaterialTheme.typography.h6
                )
            }
        }
    }) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = company.toString(), style = MaterialTheme.typography.h3)
        }
    }
}