package dev.rtrilia.truthinsong.data.database

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import dev.rtrilia.truthinsong.data.database.entities.EnglishEntity
import dev.rtrilia.truthinsong.data.database.entities.MalayalamEntity
import dev.rtrilia.truthinsong.data.database.entities.ResponsiveEntity
import dev.rtrilia.truthinsong.data.database.entities.TopicEntity
import dev.rtrilia.truthinsong.data.models.EnglishList
import dev.rtrilia.truthinsong.data.models.MalayalamList
import dev.rtrilia.truthinsong.data.models.ResponsiveList
import dev.rtrilia.truthinsong.data.models.Song

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

    @Query("SELECT (SELECT COUNT(*) FROM MalayalamEntity) + (SELECT COUNT(*) FROM EnglishEntity)+ (SELECT COUNT(*) FROM ResponsiveEntity) +(SELECT COUNT(*) FROM TopicEntity)")
    fun getDbRows(): LiveData<Int>

    @Query("SELECT id,eng_title,song_id FROM englishentity")
    fun getEnglishList(): DataSource.Factory<Int, EnglishList>

    @Query("SELECT id,song_id,mal_title FROM malayalamentity")
    fun getMalayalamList(): DataSource.Factory<Int, MalayalamList>

    @Query("SELECT id,mal_title,song_id FROM responsiveentity")
    fun getResponsiveList(): DataSource.Factory<Int, ResponsiveList>

    @Query("SELECT id,song_id,mal_title,eng_title,author,content,translate_id FROM(SELECT id,song_id,NULL as mal_title,eng_title,author,content,translate_id FROM EnglishEntity UNION ALL SELECT id,song_id,mal_title,eng_title,author,content,translate_id FROM MalayalamEntity UNION ALL SELECT id,song_id,mal_title,eng_title,Null as author,content,NULL as translate_id FROM ResponsiveEntity) WHERE id=:id")
    suspend fun getSong(id: String): Song

    @Query("SELECT id,song_id,mal_title,eng_title,author,content,translate_id FROM(SELECT id,song_id,NULL as mal_title,eng_title,author,content,translate_id FROM EnglishEntity UNION ALL SELECT id,song_id,mal_title,eng_title,author,content,translate_id FROM MalayalamEntity UNION ALL SELECT id,song_id,mal_title,eng_title,Null as author,content,NULL as translate_id FROM ResponsiveEntity) WHERE (song_id like :songIdQuery or mal_title like :malTitleQuery or eng_title like :engTitleQuery or content like :engTitleQuery) LIMIT 200")
    fun getSearchList(songIdQuery: String?, malTitleQuery: String?, engTitleQuery: String?): LiveData<List<Song>>

}