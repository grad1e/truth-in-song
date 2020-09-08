package dev.rtrilia.truthinsong.util

import dev.rtrilia.truthinsong.data.database.entities.EnglishEntity
import dev.rtrilia.truthinsong.data.database.entities.MalayalamEntity
import dev.rtrilia.truthinsong.data.database.entities.ResponsiveEntity
import dev.rtrilia.truthinsong.data.database.entities.TopicEntity
import dev.rtrilia.truthinsong.data.models.toDatabase.EnglishJson
import dev.rtrilia.truthinsong.data.models.toDatabase.MalayalamJson
import dev.rtrilia.truthinsong.data.models.toDatabase.ResponsiveJson
import dev.rtrilia.truthinsong.data.models.toDatabase.TopicsJson

fun EnglishJson.EnglishJsonResponse.asDatabaseModel(): List<EnglishEntity> {
    return english.map {
        EnglishEntity(
            author = it.author,
            content = it.content,
            eng_title = it.eng_title,
            id = it.id,
            song_id = it.song_id,
            translate_id = it.translate_id
        )
    }
}

fun MalayalamJson.MalayalamJsonResponse.asDatabaseModel(): List<MalayalamEntity> {
    return malayalam.map {
        MalayalamEntity(
            author = it.author,
            content = it.content,
            eng_title = it.eng_title,
            id = it.id,
            mal_title = it.mal_title,
            song_id = it.song_id,
            topic_id = it.topic_id,
            translate_id = it.translate_id
        )
    }
}

fun ResponsiveJson.ResponsiveJsonResponse.asDatabaseModel(): List<ResponsiveEntity> {
    return responsive.map {
        ResponsiveEntity(
            content = it.content,
            eng_title = it.eng_title,
            id = it.id,
            mal_title = it.mal_title,
            song_id = it.song_id
        )
    }
}


fun TopicsJson.TopicsJsonResponse.asDatabaseModel(): List<TopicEntity> {
    return topics.map {
        TopicEntity(
            id = it.id,
            title = it.title
        )
    }
}


