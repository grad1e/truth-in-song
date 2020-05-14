package dev.rtrilia.truthinsong.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class MalayalamEntity(
    val author: String?,
    val content: String?,
    @ColumnInfo(name = "eng_title") val engTitle: String?,
    @PrimaryKey val id: String,
    @ColumnInfo(name = "mal_title") val malTitle: String?,
    @ColumnInfo(name = "song_id") val songId: String?,
    @ColumnInfo(name = "topic_id") val topicId: String?,
    @ColumnInfo(name = "translate_id") val translateId: String?
)