package dev.rtrilia.truthinsong.data.models.toDatabase

import com.squareup.moshi.JsonClass
import dev.rtrilia.truthinsong.data.database.entities.MalayalamEntity

class MalayalamJson {
    @JsonClass(generateAdapter = true)
    data class MalayalamJsonResponse(
        val malayalam: List<MalayalamEntity>
    )
}
