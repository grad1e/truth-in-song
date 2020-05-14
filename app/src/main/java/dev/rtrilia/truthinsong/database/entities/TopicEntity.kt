package dev.rtrilia.truthinsong.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class TopicEntity(
    @PrimaryKey val id: String,
    val title:String?
)