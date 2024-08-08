package com.example.note.home

import androidx.lifecycle.ViewModel
import com.example.note.model.Note

class HomeViewModel: ViewModel() {

    fun getDummyData() = listOf(
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

}