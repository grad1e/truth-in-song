package dev.rtrilia.truthinsong.repository

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import dev.rtrilia.truthinsong.data.database.SongBookDao
import dev.rtrilia.truthinsong.data.models.EnglishList
import dev.rtrilia.truthinsong.data.models.MalayalamList
import dev.rtrilia.truthinsong.data.models.ResponsiveList
import dev.rtrilia.truthinsong.data.models.Song
import dev.rtrilia.truthinsong.data.preference.PreferenceHandler
import javax.inject.Inject


class Repository @Inject constructor(private val songBookDao: SongBookDao, private val preferences: PreferenceHandler) {

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

    fun setUiMode(value: Int) = preferences.setUiMode(value)
    fun getUiMode(): Int = preferences.getUiMode()

    fun setFontSize(value: Float) = preferences.setFontSize(value)
    fun getFontSize(): Float = preferences.getFontSize()

    fun setFontSizeSmall(value: Float) = preferences.setFontSizeSmall(value)
    fun getFontSizeSmall(): Float = preferences.getFontSizeSmall()

}