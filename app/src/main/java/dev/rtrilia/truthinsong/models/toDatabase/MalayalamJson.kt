package dev.rtrilia.truthinsong.models.toDatabase

import com.squareup.moshi.JsonClass
import dev.rtrilia.truthinsong.database.entities.MalayalamEntity

class MalayalamJson {
    @JsonClass(generateAdapter = true)
    data class MalayalamJsonResponse(
        val malayalam: List<MalayalamEntity>
    )
}
