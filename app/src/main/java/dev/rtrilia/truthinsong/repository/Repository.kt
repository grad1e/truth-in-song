package dev.rtrilia.truthinsong.repository

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import dev.rtrilia.truthinsong.database.SongBookDao
import dev.rtrilia.truthinsong.database.entities.EnglishEntity
import dev.rtrilia.truthinsong.database.entities.MalayalamEntity
import dev.rtrilia.truthinsong.database.entities.ResponsiveEntity
import dev.rtrilia.truthinsong.database.entities.TopicEntity
import dev.rtrilia.truthinsong.models.EnglishList
import dev.rtrilia.truthinsong.models.MalayalamList
import dev.rtrilia.truthinsong.models.ResponsiveList
import dev.rtrilia.truthinsong.models.Song


class Repository(private val songBookDao: SongBookDao) {

    suspend fun insertMalayalam(malayalamEntity: List<MalayalamEntity>) {
        songBookDao.insertMalayalam(malayalamEntity)
    }

    suspend fun insertEnglish(englishEntity: List<EnglishEntity>) {
        songBookDao.insertEnglish(englishEntity)
    }

    suspend fun insertResponsive(responsiveEntity: List<ResponsiveEntity>) {
        songBookDao.insertResponsive(responsiveEntity)
    }

    suspend fun insertTopic(topicEntity: List<TopicEntity>) {
        songBookDao.insertTopic(topicEntity)
    }

    suspend fun getDbRows(): Int {
        return songBookDao.getDbRows()
    }

    fun getEnglishList(): DataSource.Factory<Int, EnglishList> {
        return songBookDao.getEnglishList()
    }

    fun getMalayalamList(): DataSource.Factory<Int, MalayalamList> {
        return songBookDao.getMalayalamList()
    }

    fun getResponsiveList(): DataSource.Factory<Int, ResponsiveList> {
        return songBookDao.getResponsiveList()
    }

    fun getSong(id: String): LiveData<Song> {
        return songBookDao.getSong(id)
    }

    fun getSearchList(songIdQuery: String?, malTitleQuery: String?, engTitleQuery: String?): LiveData<List<Song>> {
        return songBookDao.getSearchList(songIdQuery, malTitleQuery, engTitleQuery)
    }

}