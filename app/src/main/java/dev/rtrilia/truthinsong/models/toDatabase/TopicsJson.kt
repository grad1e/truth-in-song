package dev.rtrilia.truthinsong.models.toDatabase

import com.squareup.moshi.JsonClass
import dev.rtrilia.truthinsong.database.entities.TopicEntity

class TopicsJson {
    @JsonClass(generateAdapter = true)
    data class TopicsJsonResponse(
        val topics: List<TopicEntity>
    )
}
