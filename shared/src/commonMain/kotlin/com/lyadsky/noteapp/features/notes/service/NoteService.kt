package com.lyadsky.noteapp.features.notes.service

import com.lyadsky.noteapp.common.data.local.DBRepository
import com.lyadsky.noteapp.features.notes.model.Note
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

interface NoteService {
    suspend fun getAllNotes(): List<Note>
    suspend fun getNote(id: Long): Note
    suspend fun insertNote(note: Note)
    suspend fun deleteNote(id: Long)
}

class NoteServiceImpl : KoinComponent, NoteService {

    private val scope = CoroutineScope(Dispatchers.Default)

    private val dbRepository: DBRepository by inject()

    override suspend fun getAllNotes(): List<Note> {
        return dbRepository.getNotes()
    }

    override suspend fun getNote(id: Long): Note {
        return dbRepository.getNote(id)
    }

    override suspend fun insertNote(note: Note) {
        dbRepository.insertNote(note)
    }

    override suspend fun deleteNote(id: Long) {
        dbRepository.deleteNote(id)
    }

}
