package dev.rtrilia.truthinsong.util

import android.content.res.Resources
import com.squareup.moshi.Moshi
import dev.rtrilia.truthinsong.R
import dev.rtrilia.truthinsong.database.entities.EnglishEntity
import dev.rtrilia.truthinsong.database.entities.MalayalamEntity
import dev.rtrilia.truthinsong.database.entities.ResponsiveEntity
import dev.rtrilia.truthinsong.database.entities.TopicEntity
import dev.rtrilia.truthinsong.models.toDatabase.EnglishJson
import dev.rtrilia.truthinsong.models.toDatabase.MalayalamJson
import dev.rtrilia.truthinsong.models.toDatabase.ResponsiveJson
import dev.rtrilia.truthinsong.models.toDatabase.TopicsJson
import timber.log.Timber

object SongDatabaseUtil {

    fun getTopicsJson(resources: Resources): List<TopicEntity> {
        val jsonString = resources.openRawResource(R.raw.topics).bufferedReader().use {
            it.readText()
        }
        val moshi = Moshi.Builder().build()
        val adapter = moshi.adapter(TopicsJson.TopicsJsonResponse::class.java)
        val topicObject = adapter.fromJson(jsonString)
        Timber.d("Retrieved Topics")
        return topicObject!!.asDatabaseModel()
    }

    fun getEnglishJson(resources: Resources): List<EnglishEntity> {
        val jsonString = resources.openRawResource(R.raw.english).bufferedReader().use {
            it.readText()
        }
        val moshi = Moshi.Builder().build()
        val adapter = moshi.adapter(EnglishJson.EnglishJsonResponse::class.java)
        val englishObject = adapter.fromJson(jsonString)
        Timber.d("Retrieved English")
        return englishObject!!.asDatabaseModel()
    }


    fun getScripturalJson(resources: Resources): List<ResponsiveEntity> {
        val jsonString = resources.openRawResource(R.raw.responsive).bufferedReader().use {
            it.readText()
        }
        val moshi = Moshi.Builder().build()
        val adapter = moshi.adapter(ResponsiveJson.ResponsiveJsonResponse::class.java)
        val scripturalObject = adapter.fromJson(jsonString)
        Timber.d("Retrieved Responsive")
        return scripturalObject!!.asDatabaseModel()
    }


    fun getMalayalamJson(resources: Resources): List<MalayalamEntity> {
        val jsonString = resources.openRawResource(R.raw.malayalam).bufferedReader().use {
            it.readText()
        }
        val moshi = Moshi.Builder().build()
        val adapter = moshi.adapter(MalayalamJson.MalayalamJsonResponse::class.java)
        val malayalamObject = adapter.fromJson(jsonString)
        Timber.d("Retrieved Malayalam")
        return malayalamObject!!.asDatabaseModel()
    }
}
