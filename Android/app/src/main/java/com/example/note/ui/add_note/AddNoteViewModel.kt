package com.example.note.ui.add_note

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
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

class AddNoteViewModel(private val noteDao: NoteDao) : ViewModel() {
    private val _showLoading = MutableLiveData(false)
    val showLoading: LiveData<Boolean> = _showLoading


    fun saveNote(newNote: Note) {
        _showLoading.value = true
        viewModelScope.launch(Dispatchers.IO ){
            noteDao.addNote(newNote)
        }
        _showLoading.value = false
    }


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
                return AddNoteViewModel(application.database.noteDao()) as T
            }
        }
    }
}