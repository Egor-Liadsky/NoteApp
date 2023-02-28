package com.lyadsky.noteapp.features.notes.state

import com.lyadsky.noteapp.features.notes.model.Note

data class NoteState(
    val noteList: List<Note> = listOf()
)
