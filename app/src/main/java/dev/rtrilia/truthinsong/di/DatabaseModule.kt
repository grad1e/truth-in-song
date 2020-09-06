package dev.rtrilia.truthinsong.di

import android.content.Context
import android.content.SharedPreferences
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import dev.rtrilia.truthinsong.data.database.SongBookDao
import dev.rtrilia.truthinsong.data.database.SongDatabase
import dev.rtrilia.truthinsong.util.SongDatabaseUtil
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
class DatabaseModule {

    lateinit var database: SongDatabase

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): SongDatabase {
        database = Room.databaseBuilder(
            context,
            SongDatabase::class.java,
            "song_database"
        )
            .addCallback(object : RoomDatabase.Callback() {
                override fun onOpen(db: SupportSQLiteDatabase) {
                    super.onOpen(db)
                    CoroutineScope(Dispatchers.IO).launch {
                        database.songBookDao().insertTopic(SongDatabaseUtil.getTopicsJson(context.resources))
                        database.songBookDao().insertEnglish(SongDatabaseUtil.getEnglishJson(context.resources))
                        database.songBookDao().insertMalayalam(SongDatabaseUtil.getMalayalamJson(context.resources))
                        database.songBookDao().insertResponsive(SongDatabaseUtil.getScripturalJson(context.resources))
                    }
                }

            })
            .fallbackToDestructiveMigration()
            .build()
        return database
    }

    @Provides
    @Singleton
    fun provideDao(database: SongDatabase): SongBookDao = database.songBookDao()

    @Provides
    @Singleton
    fun providePreference(@ApplicationContext context: Context): SharedPreferences {
        return context.getSharedPreferences("settings_pref", Context.MODE_PRIVATE)
    }


}