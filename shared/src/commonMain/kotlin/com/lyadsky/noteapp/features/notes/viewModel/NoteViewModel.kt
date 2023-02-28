package com.lyadsky.noteapp.features.notes.viewModel

import com.lyadsky.noteapp.common.navigation.Navigator
import com.lyadsky.noteapp.common.viewModel.StatefulKmpViewModel
import com.lyadsky.noteapp.common.viewModel.StatefulKmpViewModelImpl
import com.lyadsky.noteapp.common.viewModel.SubScreenViewModel
import com.lyadsky.noteapp.features.notes.model.Note
import com.lyadsky.noteapp.features.notes.service.NoteService
import com.lyadsky.noteapp.features.notes.state.NoteState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

interface NoteViewModel : StatefulKmpViewModel<NoteState>, SubScreenViewModel {
    override val state: StateFlow<NoteState>

    fun insertNote(note: Note)
}

class NoteViewModelImpl(
    override val navigator: Navigator,
) : KoinComponent, StatefulKmpViewModelImpl<NoteState>(), NoteViewModel {
    private val noteService: NoteService by inject()

    private val mutableState = MutableStateFlow(
        NoteState()
    )

    override val state: StateFlow<NoteState>
        get() = mutableState

    override fun onViewShown() {
        super.onViewShown()
        getAllNotes()
    }

    private fun getAllNotes() {
        jobs.add(scope.launch {
            exceptionHandleable(executionBlock = {
                val data = noteService.getAllNotes()
                mutableState.update { it.copy(noteList = data) }
            })
        })
    }

    override fun insertNote(note: Note){
        jobs.add(scope.launch {
            exceptionHandleable(executionBlock = {
                noteService.insertNote(note = note)
            })
        })
    }

    private fun deleteNote(id: Long){
        jobs.add(scope.launch {
            exceptionHandleable(executionBlock = {
                noteService.deleteNote(id)
            })
        })
    }
}
