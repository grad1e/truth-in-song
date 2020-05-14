package dev.rtrilia.truthinsong.models.toDatabase

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import dev.rtrilia.truthinsong.database.entities.EnglishEntity

@JsonClass(generateAdapter = true)
data class English(
    val english: List<EnglishX>
)

@JsonClass(generateAdapter = true)
data class EnglishX(
    val author: String?,
    val content: String?,
    @Json(name = "eng_title") val engTitle: String?,
    val id: String,
    @Json(name = "song_id") val songId: String?,
    @Json(name = "translate_id") val translateId: String?
)

fun English.asDatabaseModel(): List<EnglishEntity> {
    return english.map {
        EnglishEntity(
            author = it.author,
            content = it.content,
            engTitle = it.engTitle,
            id = it.id,
            songId = it.songId,
            translateId = it.translateId
        )
    }
}