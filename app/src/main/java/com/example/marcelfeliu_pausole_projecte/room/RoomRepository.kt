package com.example.marcelfeliu_pausole_projecte.room

import com.example.marcelfeliu_pausole_projecte.model.Character
import com.example.marcelfeliu_pausole_projecte.model.DBCharacter
import com.example.marcelfeliu_pausole_projecte.model.toDBCharacter

class RoomRepository {
    val daoInterface = RMApplication.database.rmDao()

    suspend fun getCaptured(): MutableList<DBCharacter> = daoInterface.getCaptured()
    suspend fun findByName(character: Character): Boolean {
        return daoInterface.findByName(character.name).isNotEmpty()
    }
    suspend fun isCaptured(name: String): Boolean = daoInterface.isCaptured(name)
    suspend fun captureCharacter(freeCharacter: Character) {
        val char = freeCharacter.toDBCharacter(isCaptured = true)
        daoInterface.captureCharacter(char)
    }
    suspend fun freeCharacter(capturedChar: Character) {
        val char = capturedChar.toDBCharacter(isCaptured = false)
        daoInterface.freeCharacter(char)
    }
    suspend fun updateCapturedStatus(name: String, isCaptured: Boolean) = daoInterface.updateCapturedStatus(name, isCaptured)

}