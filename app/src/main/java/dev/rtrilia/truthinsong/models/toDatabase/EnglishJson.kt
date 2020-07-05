package dev.rtrilia.truthinsong.models.toDatabase

import com.squareup.moshi.JsonClass
import dev.rtrilia.truthinsong.database.entities.EnglishEntity

class EnglishJson {
    @JsonClass(generateAdapter = true)
    data class EnglishJsonResponse(
        val english: List<EnglishEntity>
    )
}

