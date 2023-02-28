package com.lyadsky.noteapp.common.di

import com.lyadsky.database.NoteDatabase
import com.squareup.sqldelight.android.AndroidSqliteDriver
import org.koin.dsl.module

actual fun platformModule() = module {
    single {
        NoteDatabase(AndroidSqliteDriver(NoteDatabase.Schema, get(), "NoteDatabase.db"))
    }
}
