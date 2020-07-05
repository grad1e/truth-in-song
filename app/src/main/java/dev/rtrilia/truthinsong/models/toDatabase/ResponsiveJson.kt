package dev.rtrilia.truthinsong.models.toDatabase

import com.squareup.moshi.JsonClass
import dev.rtrilia.truthinsong.database.entities.ResponsiveEntity

class ResponsiveJson {
    @JsonClass(generateAdapter = true)
    data class ResponsiveJsonResponse(
        val responsive: List<ResponsiveEntity>
    )
}
