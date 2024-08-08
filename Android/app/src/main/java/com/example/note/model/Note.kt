package com.example.note.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity(tableName = "notes")
data class Note(
    val category: String,
    val content: String,
    val created: Date = Date(),
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
)