package dev.rtrilia.truthinsong.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class EnglishEntity(
    val author: String?,
    val content: String?,
    @ColumnInfo(name = "eng_title") val engTitle: String?,
    @PrimaryKey val id: String,
    @ColumnInfo(name = "song_id") val songId: String?,
    @ColumnInfo(name = "translate_id") val translateId: String?
)