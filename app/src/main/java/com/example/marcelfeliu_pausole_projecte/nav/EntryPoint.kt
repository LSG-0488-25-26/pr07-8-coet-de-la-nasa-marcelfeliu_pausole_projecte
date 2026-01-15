package com.example.marcelfeliu_pausole_projecte.nav

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.marcelfeliu_pausole_projecte.view.DetailScreen
import com.example.marcelfeliu_pausole_projecte.view.LazyColumnCharacters
import com.example.marcelfeliu_pausole_projecte.viewmodel.RickAndMortyViewModel

@Composable
fun EntryPoint(navigationController: NavController, viewModel: RickAndMortyViewModel)
{
    NavHost(
        navController = navigationController as NavHostController,
        startDestination = Routes.CharactersList.route
    ) {
        composable (Routes.CharactersList.route) { LazyColumnCharacters(navigationController, viewModel) }
        composable (Routes.DetailScreen.route) { DetailScreen(navigationController, viewModel) }

    }
}
