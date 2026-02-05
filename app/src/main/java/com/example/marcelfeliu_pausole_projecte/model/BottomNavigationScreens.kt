package com.example.marcelfeliu_pausole_projecte.model

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Settings
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.marcelfeliu_pausole_projecte.nav.Routes

sealed class BottomNavigationScreens(val route: String,
                                    val icon: ImageVector,
                                    val label: String
){
    object Home: BottomNavigationScreens(Routes.CharactersList.route, Icons.Filled.Home, "Home")
    object Captured: BottomNavigationScreens(Routes.CapturedList.route, Icons.Filled.Lock, "Captured")
}