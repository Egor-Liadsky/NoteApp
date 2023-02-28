package com.lyadsky.database.Notes

import kotlin.Long
import kotlin.String

public data class Notes(
  public val id: Long,
  public val title: String,
  public val description: String
) {
  public override fun toString(): String = """
  |Notes [
  |  id: $id
  |  title: $title
  |  description: $description
  |]
  """.trimMargin()
}
