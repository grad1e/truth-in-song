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

    var uiMode
        get() = preferences.uiMode
        set(value) {
            preferences.uiMode = value
        }

    var fontSize
        get() = preferences.fontSize
        set(value) {
            preferences.fontSize = value
        }

    var fontSizeSmall
        get() = preferences.fontSizeSmall
        set(value) {
            preferences.fontSizeSmall = value
        }

    var shuffleMode
        get() = preferences.shuffleMode
        set(value) {
            preferences.shuffleMode = value
        }

}