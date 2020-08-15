package dev.rtrilia.truthinsong.repository

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import dev.rtrilia.truthinsong.database.SongBookDao
import dev.rtrilia.truthinsong.models.EnglishList
import dev.rtrilia.truthinsong.models.MalayalamList
import dev.rtrilia.truthinsong.models.ResponsiveList
import dev.rtrilia.truthinsong.models.Song


class Repository(private val songBookDao: SongBookDao) {

    fun getDbRows(): LiveData<Int> {
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

    suspend fun getSong(id: String): Song {
        return songBookDao.getSong(id)
    }

    fun getSearchList(songIdQuery: String?, malTitleQuery: String?, engTitleQuery: String?): LiveData<List<Song>> {
        return songBookDao.getSearchList(songIdQuery, malTitleQuery, engTitleQuery)
    }

}