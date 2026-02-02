package com.example.marcelfeliu_pausole_projecte.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters

@Entity(tableName = "rmcharacters")
data class DBCharacter(
    @PrimaryKey val id: Int,
    val name: String,
    @ColumnInfo(name = "is_captured") var isCaptured: Boolean = false
)