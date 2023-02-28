package com.lyadsky.noteapp.common.data.local

import com.lyadsky.database.NoteDatabase
import com.lyadsky.noteapp.common.utils.toNote
import com.lyadsky.noteapp.features.notes.model.Note
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

interface DBRepository {
    suspend fun getNotes(): List<Note>
    suspend fun getNote(id: Long): Note
    suspend fun insertNote(note: Note)
    suspend fun deleteNote(id: Long)
}

class DBRepositoryImpl : KoinComponent, DBRepository {

    private val database: NoteDatabase by inject()

    private val noteQuery = database.notesQueries

    override suspend fun getNotes(): List<Note> {
        return noteQuery.getAllNotes().executeAsList().map { it.toNote() }
    }

    override suspend fun getNote(id: Long): Note {
        return noteQuery.getNotes(id).executeAsOne().toNote()
    }

    override suspend fun insertNote(note: Note) {
        noteQuery.insertNote(
            id = null,
            title = note.title,
            description = note.description
        )
    }

    override suspend fun deleteNote(id: Long) {
        noteQuery.deleteNote(id)
    }
}
