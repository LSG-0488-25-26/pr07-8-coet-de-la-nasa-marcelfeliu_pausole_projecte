package com.example.marcelfeliu_pausole_projecte.model

data class Data(
    val info: Info,
    val results: List<Character>
)

data class Info(
    val count: Int,
    val next: Any,
    val pages: Int,
    val prev: Any
)



