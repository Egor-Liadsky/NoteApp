package com.lyadsky.noteapp.features.notes.model

data class Note(
    val id: Long? = 0,
    val title: String,
    val description: String
)
