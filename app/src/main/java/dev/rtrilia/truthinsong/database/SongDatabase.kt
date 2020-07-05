package dev.rtrilia.truthinsong.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import dev.rtrilia.truthinsong.database.entities.EnglishEntity
import dev.rtrilia.truthinsong.database.entities.MalayalamEntity
import dev.rtrilia.truthinsong.database.entities.ResponsiveEntity
import dev.rtrilia.truthinsong.database.entities.TopicEntity
import dev.rtrilia.truthinsong.util.SongDatabaseUtil
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

@Database(
    entities = [MalayalamEntity::class, EnglishEntity::class, ResponsiveEntity::class, TopicEntity::class],
    version = 1,
    exportSchema = false
)
abstract class SongDatabase : RoomDatabase() {
    abstract val songBookDao: SongBookDao

    companion object {
        private var INSTANCE: SongDatabase? = null

        fun getDatabase(context: Context): SongDatabase {

            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    SongDatabase::class.java,
                    "song_database"
                )
                    .addCallback(object : Callback(), CoroutineScope {
                        override val coroutineContext: CoroutineContext
                            get() = Dispatchers.IO

                        override fun onCreate(db: SupportSQLiteDatabase) {
                            super.onCreate(db)
                            INSTANCE?.let { database ->
                                launch(coroutineContext) {
                                    database.songBookDao.insertTopic(SongDatabaseUtil.getTopicsJson(context.resources))
                                    database.songBookDao.insertEnglish(SongDatabaseUtil.getEnglishJson(context.resources))
                                    database.songBookDao.insertMalayalam(SongDatabaseUtil.getMalayalamJson(context.resources))
                                    database.songBookDao.insertResponsive(SongDatabaseUtil.getScripturalJson(context.resources))
                                }
                            }
                        }
                    })
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                return instance
            }
        }
    }

}
