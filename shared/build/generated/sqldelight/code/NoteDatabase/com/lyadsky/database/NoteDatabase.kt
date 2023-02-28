package com.lyadsky.database

import com.lyadsky.database.Notes.NotesQueries
import com.lyadsky.database.shared.newInstance
import com.lyadsky.database.shared.schema
import com.squareup.sqldelight.Transacter
import com.squareup.sqldelight.db.SqlDriver

public interface NoteDatabase : Transacter {
  public val notesQueries: NotesQueries

  public companion object {
    public val Schema: SqlDriver.Schema
      get() = NoteDatabase::class.schema

    public operator fun invoke(driver: SqlDriver): NoteDatabase =
        NoteDatabase::class.newInstance(driver)
  }
}
