package com.example.note.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import com.example.note.NoteApplication
import com.example.note.data.NoteDao
import com.example.note.model.Note
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HomeViewModel(val noteDao: NoteDao) : ViewModel() {
    val notes: LiveData<List<Note>> = noteDao.getAllNotes()


    fun importDummyData() {
        val data = getDummyData()
        viewModelScope.launch(Dispatchers.IO) {
            noteDao.addAllNotes(data)
        }
    }

    fun deleteAllNotes() {
        viewModelScope.launch(Dispatchers.IO) {
            noteDao.deleteAllNotes()
        }
    }


    private fun getDummyData() = listOf(
        Note("Work and study", "Learn Java and Kotlin"),
        Note("Work and study", "Study Android"),
        Note("Work and study", "Build a hello world app"),
        Note("Life", "have a family dinner"),
        Note("Life", "hangout with friends"),
        Note("Life", "watch movie"),
        Note("Health and well", "go for a run"),
        Note("Health and well", "walk with dog"),
        Note("Health and well", "go gym"),
    )



    companion object {
        // FIXME: use DI library like hilt/dagger to inject dependencies

        val Factory: ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(
                modelClass: Class<T>,
                extras: CreationExtras
            ): T {
                // Get the Application object from extras
                val application = checkNotNull(extras[APPLICATION_KEY]) as NoteApplication
                return HomeViewModel(application.database.noteDao()) as T
            }
        }
    }
}