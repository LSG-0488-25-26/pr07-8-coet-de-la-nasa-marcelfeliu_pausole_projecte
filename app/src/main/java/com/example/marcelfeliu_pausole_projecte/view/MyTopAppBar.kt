package com.example.marcelfeliu_pausole_projecte.view

import android.content.res.Configuration
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.navigation.NavHostController
import com.example.marcelfeliu_pausole_projecte.viewmodel.RickAndMortyViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyTopAppBar(navController: NavHostController, viewModel: RickAndMortyViewModel){
    val configuration = LocalConfiguration.current

    TopAppBar(
        title = { Text(text = "Rick and Morty") },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color.LightGray,
            titleContentColor = Color.Black,
            navigationIconContentColor = Color.Black,
            actionIconContentColor = Color.Black
        ),
        navigationIcon = {
            IconButton(onClick = { navController.popBackStack() }) {
                Icon(imageVector = Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Enrere")
            }
        },
        actions = {
            if (configuration.orientation == Configuration.ORIENTATION_PORTRAIT) {
                IconButton(onClick = { viewModel.toggleSearchBar() }) {
                    Icon(imageVector = Icons.Filled.Search, contentDescription = "Buscar")
                }
            }
        }
    )
}