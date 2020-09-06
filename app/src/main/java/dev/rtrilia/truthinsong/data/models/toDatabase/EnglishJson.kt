package dev.rtrilia.truthinsong.data.models.toDatabase

import com.squareup.moshi.JsonClass
import dev.rtrilia.truthinsong.data.database.entities.EnglishEntity

class EnglishJson {
    @JsonClass(generateAdapter = true)
    data class EnglishJsonResponse(
        val english: List<EnglishEntity>
    )
}

