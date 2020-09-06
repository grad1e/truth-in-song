package dev.rtrilia.truthinsong.data.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.JsonClass

@Entity
@JsonClass(generateAdapter = true)
data class TopicEntity(
    @PrimaryKey val id: String,
    val title: String
)