package com.example.marcelfeliu_pausole_projecte.room

import android.app.Application
import androidx.room.Room
import com.example.marcelfeliu_pausole_projecte.model.DBCharacter

class RMApplication : Application() {
    // Creem un atribut estàtic de la classe
    companion object {
        lateinit var database: RMDatabase
    }

    override fun onCreate() {
        super.onCreate()
        // Aquí determinem el nom de la base de dades. En el nostre cas "PokemonDatabase"
        database = Room.databaseBuilder(this, RMDatabase::class.java,"RMDatabase").build()
    }
}