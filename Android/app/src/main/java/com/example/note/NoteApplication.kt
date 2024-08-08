package com.example.note

import android.app.Application
import com.example.note.data.AppDatabase
import timber.log.Timber

class NoteApplication: Application() {
    val database by lazy { AppDatabase.getInstance(this) }


    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }

    }
}