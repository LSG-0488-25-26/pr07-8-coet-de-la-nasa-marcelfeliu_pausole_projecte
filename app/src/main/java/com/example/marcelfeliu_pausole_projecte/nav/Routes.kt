package com.example.marcelfeliu_pausole_projecte.nav 

sealed class Routes(val route: String){
    object CharactersList: Routes("RMCharactersList")
    object DetailScreen: Routes("DetailScreen")
    object CapturedList: Routes("CapturedList")


}