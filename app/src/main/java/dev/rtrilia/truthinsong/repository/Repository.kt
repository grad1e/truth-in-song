package dev.rtrilia.truthinsong.repository

import dev.rtrilia.truthinsong.data.database.SongBookDao
import dev.rtrilia.truthinsong.data.preference.PreferenceHandler
import javax.inject.Inject


class Repository @Inject constructor(
    private val songBookDao: SongBookDao,
    private val preferences: PreferenceHandler
) {

    fun getDbRows() = songBookDao.getDbRows()

    fun getEnglishList() = songBookDao.getEnglishList()

    fun getMalayalamList() = songBookDao.getMalayalamList()

    fun getResponsiveList() = songBookDao.getResponsiveList()

    suspend fun getSong(id: String) = songBookDao.getSong(id)

    fun getSearchList(
        songIdQuery: String?,
        malTitleQuery: String?,
        engTitleQuery: String?
    ) = songBookDao.getSearchList(songIdQuery, malTitleQuery, engTitleQuery)

    fun setUiModePref(value: Int) = preferences.setUiMode(value)
    fun getUiModePref() = preferences.getUiMode()

    fun setFontSizePref(value: Float) = preferences.setFontSize(value)
    fun getFontSizePref() = preferences.getFontSize()

    fun setFontSizeSmallPref(value: Float) = preferences.setFontSizeSmall(value)
    fun getFontSizeSmallPref() = preferences.getFontSizeSmall()

    fun setShuffleMode(value: Int) = preferences.setShuffleMode(value)
    fun getShuffleMode() = preferences.getShuffleMode()

}