package dev.rtrilia.truthinsong.database

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import dev.rtrilia.truthinsong.database.entities.EnglishEntity
import dev.rtrilia.truthinsong.database.entities.MalayalamEntity
import dev.rtrilia.truthinsong.database.entities.ResponsiveEntity
import dev.rtrilia.truthinsong.database.entities.TopicEntity
import dev.rtrilia.truthinsong.models.EnglishList
import dev.rtrilia.truthinsong.models.MalayalamList
import dev.rtrilia.truthinsong.models.ResponsiveList
import dev.rtrilia.truthinsong.models.Song

@Dao
interface SongBookDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMalayalam(malayalamEntity: List<MalayalamEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTopic(topicEntity: List<TopicEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertEnglish(englishEntity: List<EnglishEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertResponsive(responsiveEntity: List<ResponsiveEntity>)

    @Query("SELECT (SELECT COUNT(*) FROM malayalamentity) + (SELECT COUNT(*) FROM englishentity)+ (SELECT COUNT(*) FROM responsiveentity) +(SELECT COUNT(*) FROM topicentity)")
    suspend fun getDbRows(): Int

    @Query("SELECT id,eng_title,song_id FROM englishentity")
    fun getEnglishList(): DataSource.Factory<Int, EnglishList>

    @Query("SELECT id,song_id,mal_title FROM malayalamentity")
    fun getMalayalamList(): DataSource.Factory<Int, MalayalamList>

    @Query("SELECT id,mal_title,song_id FROM responsiveentity")
    fun getResponsiveList(): DataSource.Factory<Int, ResponsiveList>

    @Query("SELECT id,song_id,mal_title,eng_title,author,content,translate_id FROM(SELECT id,song_id,NULL as mal_title,eng_title,author,content,translate_id FROM EnglishEntity UNION ALL SELECT id,song_id,mal_title,eng_title,author,content,translate_id FROM MalayalamEntity UNION ALL SELECT id,song_id,mal_title,eng_title,Null as author,content,NULL as translate_id FROM ResponsiveEntity) WHERE id=:id")
    fun getSong(id: String): LiveData<Song>

    @Query("SELECT id,song_id,mal_title,eng_title,author,content,translate_id FROM(SELECT id,song_id,NULL as mal_title,eng_title,author,content,translate_id FROM EnglishEntity UNION ALL SELECT id,song_id,mal_title,eng_title,author,content,translate_id FROM MalayalamEntity UNION ALL SELECT id,song_id,mal_title,eng_title,Null as author,content,NULL as translate_id FROM ResponsiveEntity) WHERE (song_id like :songIdQuery or mal_title like :malTitleQuery or eng_title like :engTitleQuery) LIMIT 10")
    fun getSearchList(songIdQuery: String?, malTitleQuery: String?, engTitleQuery: String?): LiveData<List<Song>>

}