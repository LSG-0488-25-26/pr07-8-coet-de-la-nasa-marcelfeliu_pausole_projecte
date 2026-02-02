package com.example.marcelfeliu_pausole_projecte.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.marcelfeliu_pausole_projecte.model.Character
import com.example.marcelfeliu_pausole_projecte.model.DBCharacter

@Dao
interface RMDao {
    @Query("SELECT * FROM rmcharacters WHERE is_captured = 1")
    fun getCaptured(): MutableList<DBCharacter>

    @Query("SELECT * FROM rmcharacters WHERE name = :name")
    fun findByName(name: String): MutableList<DBCharacter?>

    @Query("SELECT is_captured FROM rmcharacters WHERE name = :name")
    fun isCaptured(name: String): Boolean

    @Insert
    fun captureCharacter(freeCharacter: DBCharacter)

    @Delete
    fun freeCharacter(capturedCharacter: DBCharacter)

    @Query("UPDATE rmcharacters SET is_captured = :isCaptured WHERE name = :name")
    fun updateCapturedStatus(name: String, isCaptured: Boolean)
}