package com.example.marcelfeliu_pausole_projecte.nav

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.android_studio_scaffold.view.MyBottomBar
import com.example.marcelfeliu_pausole_projecte.view.DetailScreen
import com.example.marcelfeliu_pausole_projecte.view.LazyColumnCharacters
import com.example.marcelfeliu_pausole_projecte.view.MyTopAppBar
import com.example.marcelfeliu_pausole_projecte.viewmodel.RickAndMortyViewModel

@Composable
fun EntryPoint(navigationController: NavHostController, viewModel: RickAndMortyViewModel)
{
    Scaffold (
        topBar = { MyTopAppBar(navigationController) },
        bottomBar = { MyBottomBar(viewModel, navigationController) }
    )
    { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            NavHost(
                navController = navigationController,
                startDestination = Routes.CharactersList.route
            ) {
                composable(Routes.CharactersList.route) {
                    LazyColumnCharacters(
                        navigationController,
                        viewModel
                    )
                }
                composable(Routes.DetailScreen.route) {
                    DetailScreen(
                        navigationController,
                        viewModel
                    )
                }
            }
        }
    }
}
