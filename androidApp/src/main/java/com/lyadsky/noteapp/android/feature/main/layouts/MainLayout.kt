package com.lyadsky.noteapp.android.feature.main.layouts

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import com.lyadsky.noteapp.android.feature.views.notes.CommonNote
import com.lyadsky.noteapp.features.notes.model.Note

@Composable
fun MainLayout(notes: List<Note>) {
    LazyColumn{
        items(items = notes){note ->
            CommonNote(note = note)
        }
    }
}
