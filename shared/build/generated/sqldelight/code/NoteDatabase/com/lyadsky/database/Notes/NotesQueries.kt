package com.lyadsky.database.Notes

import com.squareup.sqldelight.Query
import com.squareup.sqldelight.Transacter
import kotlin.Any
import kotlin.Long
import kotlin.String
import kotlin.Unit

public interface NotesQueries : Transacter {
  public fun <T : Any> getAllNotes(mapper: (
    id: Long,
    title: String,
    description: String
  ) -> T): Query<T>

  public fun getAllNotes(): Query<Notes>

  public fun <T : Any> getNotes(id: Long, mapper: (
    id: Long,
    title: String,
    description: String
  ) -> T): Query<T>

  public fun getNotes(id: Long): Query<Notes>

  public fun insertNote(
    id: Long?,
    title: String,
    description: String
  ): Unit

  public fun deleteNote(id: Long): Unit
}
