package dev.rtrilia.truthinsong.data.models.toDatabase

import com.squareup.moshi.JsonClass
import dev.rtrilia.truthinsong.data.database.entities.TopicEntity

class TopicsJson {
    @JsonClass(generateAdapter = true)
    data class TopicsJsonResponse(
        val topics: List<TopicEntity>
    )
}
