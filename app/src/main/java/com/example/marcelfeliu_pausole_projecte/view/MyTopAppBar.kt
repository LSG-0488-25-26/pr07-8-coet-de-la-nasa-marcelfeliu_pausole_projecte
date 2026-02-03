package com.example.marcelfeliu_pausole_projecte.view

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
import androidx.navigation.NavHostController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyTopAppBar(navController: NavHostController){
    TopAppBar(
        title = { Text(text = "Superior Bar") },
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
//        actions = {
//            IconButton(onClick = { navController.navigate(Routes.Search.route) }) {
//                Icon(imageVector = Icons.Filled.Search, contentDescription = "Buscar")
//            }
//            IconButton(onClick = { navController.navigate(Routes.Add.route) }) {
//                Icon(imageVector = Icons.Filled.Add, contentDescription = "Afegir")
//            }
//        }
    )
}