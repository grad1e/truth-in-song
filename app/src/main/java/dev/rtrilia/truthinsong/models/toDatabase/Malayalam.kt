package dev.rtrilia.truthinsong.models.toDatabase

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import dev.rtrilia.truthinsong.database.entities.MalayalamEntity

@JsonClass(generateAdapter = true)
data class Malayalam(
    val malayalam: List<MalayalamX>
)

@JsonClass(generateAdapter = true)
data class MalayalamX(
    val author: String?,
    val content: String?,
    @Json(name = "eng_title") val engTitle: String?,
    val id: String,
    @Json(name = "mal_title") val malTitle: String?,
    @Json(name = "song_id") val songId: String?,
    @Json(name = "topic_id") val topicId: String?,
    @Json(name = "translate_id") val translateId: String?
)

fun Malayalam.asDatabaseModel(): List<MalayalamEntity> {
    return malayalam.map {
        MalayalamEntity(
            author = it.author,
            content = it.content,
            engTitle = it.engTitle,
            id = it.id,
            malTitle = it.malTitle,
            songId = it.songId,
            topicId = it.topicId,
            translateId = it.translateId
        )
    }
}