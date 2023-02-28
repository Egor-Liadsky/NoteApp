package com.lyadsky.noteapp.android.feature.main

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.lyadsky.noteapp.android.di.StatefulViewModelWrapper
import com.lyadsky.noteapp.android.feature.main.layouts.MainLayout
import com.lyadsky.noteapp.features.notes.state.NoteState
import com.lyadsky.noteapp.features.notes.viewModel.NoteViewModel
import org.koin.androidx.compose.getViewModel
import org.koin.core.qualifier.named

@Composable
fun MainScreen(
    viewModelWrapper: StatefulViewModelWrapper<NoteViewModel, NoteState> =
        getViewModel(named("NoteViewModel"))
) {
    viewModelWrapper.viewModel.onViewShown()

   val state = viewModelWrapper.state

    Column(Modifier.fillMaxSize()) {
        MainLayout(notes = state.value.noteList)
    }
}
