package dev.rtrilia.truthinsong.util

import android.content.res.Resources
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import dev.rtrilia.truthinsong.R
import dev.rtrilia.truthinsong.database.entities.EnglishEntity
import dev.rtrilia.truthinsong.database.entities.MalayalamEntity
import dev.rtrilia.truthinsong.database.entities.ResponsiveEntity
import dev.rtrilia.truthinsong.database.entities.TopicEntity
import dev.rtrilia.truthinsong.models.toDatabase.*
import timber.log.Timber

object SongUtil {

    fun getTopicsJson(resources: Resources): List<TopicEntity> {
        val jsonString = resources.openRawResource(R.raw.topics).bufferedReader().use {
            it.readText()
        }
        val moshi = Moshi.Builder().build()
        val adapter = moshi.adapter(Topics::class.java)
        val topicObject = adapter.fromJson(jsonString)
        Timber.d("Retrieved Topics")
        return topicObject!!.asDatabaseModel()
    }

    fun getEnglishJson(resources: Resources): List<EnglishEntity> {
        val jsonString = resources.openRawResource(R.raw.english).bufferedReader().use {
            it.readText()
        }
        val moshi = Moshi.Builder().build()
        val adapter: JsonAdapter<English> = moshi.adapter(English::class.java)
        val englishObject = adapter.fromJson(jsonString)
        Timber.d("Retrieved English")
        return englishObject!!.asDatabaseModel()
    }


    fun getScripturalJson(resources: Resources): List<ResponsiveEntity> {
        val jsonString = resources.openRawResource(R.raw.responsive).bufferedReader().use {
            it.readText()
        }
        val moshi = Moshi.Builder().build()
        val adapter: JsonAdapter<Responsive> = moshi.adapter(Responsive::class.java)
        val scripturalObject = adapter.fromJson(jsonString)
        Timber.d("Retrieved Responsive")
        return scripturalObject!!.asDatabaseModel()
    }


    fun getMalayalamJson(resources: Resources): List<MalayalamEntity> {
        val jsonString = resources.openRawResource(R.raw.malayalam).bufferedReader().use {
            it.readText()
        }
        val moshi = Moshi.Builder().build()
        val adapter: JsonAdapter<Malayalam> = moshi.adapter(Malayalam::class.java)
        val malayalamObject = adapter.fromJson(jsonString)
        Timber.d("Retrieved Malayalam")
        return malayalamObject!!.asDatabaseModel()
    }
}
