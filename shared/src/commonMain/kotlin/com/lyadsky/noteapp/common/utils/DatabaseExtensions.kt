package com.lyadsky.noteapp.common.utils

import com.lyadsky.database.Notes.Notes
import com.lyadsky.noteapp.features.notes.model.Note

fun Notes.toNote(): Note =
    Note(
        id = this.id,
        title = this.title,
        description = this.description
    )

fun Note.toNotes(): Notes =
    Notes(
        id = this.id!!,
        title = this.title,
        description = this.description
    )
