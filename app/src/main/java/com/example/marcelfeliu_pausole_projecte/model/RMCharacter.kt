package com.example.marcelfeliu_pausole_projecte.model

import androidx.annotation.DrawableRes

data class RMCharacter(
    var name: String,
    var status: String,
    var species: String,
    var gender: String,
    @DrawableRes var image: Int
)