package com.example.marcelfeliu_pausole_projecte.model


data class Character(
    val created: String,
    val episode: List<String>,
    val gender: String,
    val id: Int,
    val image: String,
    val location: Location,
    val name: String,
    val origin: Origin,
    val species: String,
    val status: String,
    val type: String,
    val url: String
)

data class Location(
    val name: String,
    val url: String
)

data class Origin(
    val name: String,
    val url: String
)

fun Character.toDBCharacter(isCaptured: Boolean = false): DBCharacter {
    return DBCharacter(
        id = this.id,
        name = this.name,
        isCaptured = isCaptured
    )
}