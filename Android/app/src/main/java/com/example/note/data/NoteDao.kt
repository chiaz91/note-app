package com.example.note.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.note.model.Note

@Dao
interface NoteDao {
    @Insert
    suspend fun addNote(note: Note)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addAllNotes(items: List<Note>)

    @Query("SELECT * FROM notes ORDER BY category ASC, created DESC")
    fun getAllNotes(): LiveData<List<Note>>

    @Query("SELECT * FROM notes GROUP BY category ORDER BY created DESC")
    fun getNotesSummary(): LiveData<List<Note>>

    @Query("DELETE FROM notes")
    suspend fun deleteAllNotes()

    // TODO: add remaining CRUD operations like update & delete later
}