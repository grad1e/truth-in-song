package dev.rtrilia.truthinsong.models.toDatabase

import com.squareup.moshi.JsonClass
import dev.rtrilia.truthinsong.database.entities.TopicEntity

@JsonClass(generateAdapter = true)
data class Topics(
    val topics: List<Topic>
)

@JsonClass(generateAdapter = true)
data class Topic(
    val id: String,
    val title: String?
)

fun Topics.asDatabaseModel(): List<TopicEntity> {
    return topics.map {
        TopicEntity(
            id = it.id,
            title = it.title
        )
    }
}