package com.example.note.ui.settings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import com.example.note.NoteApplication
import com.example.note.R
import com.example.note.data.NoteDao
import com.example.note.model.SettingMenu
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SettingsViewModel(private val noteDao: NoteDao) : ViewModel() {

    fun getMenus() = listOf(
        SettingMenu(R.drawable.headphones, R.string.online_customer),
        SettingMenu(R.drawable.form, R.string.user_agreement),
        SettingMenu(R.drawable.book, R.string.privacy_policy),
        SettingMenu(R.drawable.info, R.string.about_us),
    )

    fun deleteNotes() {
        viewModelScope.launch(Dispatchers.IO) {
            noteDao.deleteAllNotes()
        }
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
                return SettingsViewModel(application.database.noteDao()) as T
            }
        }
    }
}