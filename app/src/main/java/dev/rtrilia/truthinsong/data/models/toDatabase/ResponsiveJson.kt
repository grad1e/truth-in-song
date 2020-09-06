package dev.rtrilia.truthinsong.data.models.toDatabase

import com.squareup.moshi.JsonClass
import dev.rtrilia.truthinsong.data.database.entities.ResponsiveEntity

class ResponsiveJson {
    @JsonClass(generateAdapter = true)
    data class ResponsiveJsonResponse(
        val responsive: List<ResponsiveEntity>
    )
}
