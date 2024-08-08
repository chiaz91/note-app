package com.example.note.model

import java.util.Date

data class Note(
    val category: String,
    val content: String,
    val created: Date = Date()
)