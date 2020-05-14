package dev.rtrilia.truthinsong.models.toDatabase

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import dev.rtrilia.truthinsong.database.entities.ResponsiveEntity

@JsonClass(generateAdapter = true)
data class Responsive(
    val responsive: List<ResponsiveX>
)

@JsonClass(generateAdapter = true)
data class ResponsiveX(
    val content: String?,
    @Json(name = "eng_title") val engTitle: String?,
    val id: String,
    @Json(name = "mal_title") val malTitle: String?,
    @Json(name = "song_id") val songId: String?
)

fun Responsive.asDatabaseModel(): List<ResponsiveEntity> {
    return responsive.map {
        ResponsiveEntity(
            content = it.content,
            engTitle = it.engTitle,
            id = it.id,
            malTitle = it.malTitle,
            songId = it.songId
        )
    }
}