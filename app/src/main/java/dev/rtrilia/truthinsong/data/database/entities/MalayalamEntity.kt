package dev.rtrilia.truthinsong.data.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.JsonClass

@Entity
@JsonClass(generateAdapter = true)
data class MalayalamEntity(
    val author: String,
    val content: String,
    val eng_title: String,
    @PrimaryKey val id: String,
    val mal_title: String,
    val song_id: String,
    val topic_id: String,
    val translate_id: String
)