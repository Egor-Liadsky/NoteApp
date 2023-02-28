package com.lyadsky.noteapp.android.feature.views.notes

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.lyadsky.noteapp.features.notes.model.Note

@Composable
fun CommonNote(note: Note) {
    Column(
        modifier = Modifier
            .padding(20.dp)
            .fillMaxWidth()
            .background(Color.Gray)
    ) {
        Text(text = note.title)
        Text(text = note.description)
    }
}
