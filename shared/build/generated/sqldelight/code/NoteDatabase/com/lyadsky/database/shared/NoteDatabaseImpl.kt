package com.lyadsky.database.shared

import com.lyadsky.database.NoteDatabase
import com.lyadsky.database.Notes.Notes
import com.lyadsky.database.Notes.NotesQueries
import com.squareup.sqldelight.Query
import com.squareup.sqldelight.TransacterImpl
import com.squareup.sqldelight.`internal`.copyOnWriteList
import com.squareup.sqldelight.db.SqlCursor
import com.squareup.sqldelight.db.SqlDriver
import kotlin.Any
import kotlin.Int
import kotlin.Long
import kotlin.String
import kotlin.Unit
import kotlin.collections.MutableList
import kotlin.reflect.KClass

internal val KClass<NoteDatabase>.schema: SqlDriver.Schema
  get() = NoteDatabaseImpl.Schema

internal fun KClass<NoteDatabase>.newInstance(driver: SqlDriver): NoteDatabase =
    NoteDatabaseImpl(driver)

private class NoteDatabaseImpl(
  driver: SqlDriver
) : TransacterImpl(driver), NoteDatabase {
  public override val notesQueries: NotesQueriesImpl = NotesQueriesImpl(this, driver)

  public object Schema : SqlDriver.Schema {
    public override val version: Int
      get() = 1

    public override fun create(driver: SqlDriver): Unit {
      driver.execute(null, """
          |CREATE TABLE Notes (
          |id INTEGER NOT NULL PRIMARY KEY,
          |title TEXT NOT NULL,
          |description TEXT NOT NULL
          |)
          """.trimMargin(), 0)
    }

    public override fun migrate(
      driver: SqlDriver,
      oldVersion: Int,
      newVersion: Int
    ): Unit {
    }
  }
}

private class NotesQueriesImpl(
  private val database: NoteDatabaseImpl,
  private val driver: SqlDriver
) : TransacterImpl(driver), NotesQueries {
  internal val getAllNotes: MutableList<Query<*>> = copyOnWriteList()

  internal val getNotes: MutableList<Query<*>> = copyOnWriteList()

  public override fun <T : Any> getAllNotes(mapper: (
    id: Long,
    title: String,
    description: String
  ) -> T): Query<T> = Query(-1679265079, getAllNotes, driver, "Notes.sq", "getAllNotes",
      "SELECT * FROM Notes") { cursor ->
    mapper(
      cursor.getLong(0)!!,
      cursor.getString(1)!!,
      cursor.getString(2)!!
    )
  }

  public override fun getAllNotes(): Query<Notes> = getAllNotes { id, title, description ->
    Notes(
      id,
      title,
      description
    )
  }

  public override fun <T : Any> getNotes(id: Long, mapper: (
    id: Long,
    title: String,
    description: String
  ) -> T): Query<T> = GetNotesQuery(id) { cursor ->
    mapper(
      cursor.getLong(0)!!,
      cursor.getString(1)!!,
      cursor.getString(2)!!
    )
  }

  public override fun getNotes(id: Long): Query<Notes> = getNotes(id) { id_, title, description ->
    Notes(
      id_,
      title,
      description
    )
  }

  public override fun insertNote(
    id: Long?,
    title: String,
    description: String
  ): Unit {
    driver.execute(-321082312, """
    |INSERT INTO Notes (id, title, description)
    |VALUES (?, ?, ?)
    """.trimMargin(), 3) {
      bindLong(1, id)
      bindString(2, title)
      bindString(3, description)
    }
    notifyQueries(-321082312, {database.notesQueries.getAllNotes + database.notesQueries.getNotes})
  }

  public override fun deleteNote(id: Long): Unit {
    driver.execute(477341226, """
    |DELETE FROM Notes
    |WHERE id = ?
    """.trimMargin(), 1) {
      bindLong(1, id)
    }
    notifyQueries(477341226, {database.notesQueries.getAllNotes + database.notesQueries.getNotes})
  }

  private inner class GetNotesQuery<out T : Any>(
    public val id: Long,
    mapper: (SqlCursor) -> T
  ) : Query<T>(getNotes, mapper) {
    public override fun execute(): SqlCursor = driver.executeQuery(-306424264,
        """SELECT * FROM Notes WHERE id = ?""", 1) {
      bindLong(1, id)
    }

    public override fun toString(): String = "Notes.sq:getNotes"
  }
}
