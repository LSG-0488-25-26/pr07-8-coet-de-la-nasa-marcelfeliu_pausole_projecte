package com.example.marcelfeliu_pausole_projecte.room

import androidx.room.Database
import androidx.room.RoomDatabase

import com.example.marcelfeliu_pausole_projecte.model.DBCharacter


@Database(entities = arrayOf(DBCharacter::class), version = 2)
// La classe CharacterDatabase hereta de RoomDatabase
abstract class RMDatabase: RoomDatabase() {
    abstract fun rmDao(): RMDao
}