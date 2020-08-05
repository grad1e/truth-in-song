package dev.rtrilia.truthinsong

import android.app.Application
import dev.rtrilia.truthinsong.database.SongBookDao
import dev.rtrilia.truthinsong.database.SongDatabase
import dev.rtrilia.truthinsong.repository.Repository
import timber.log.Timber

class SongApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
    }

    private fun getSongBookDao(): SongBookDao = SongDatabase.getDatabase(applicationContext).songBookDao

    fun getRepository(): Repository = Repository(getSongBookDao())

}
