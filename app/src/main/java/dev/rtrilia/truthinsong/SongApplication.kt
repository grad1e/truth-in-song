package dev.rtrilia.truthinsong

import android.app.Application
import dev.rtrilia.truthinsong.database.SongDatabase
import dev.rtrilia.truthinsong.repository.Repository
import timber.log.Timber

class SongApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())

    }

    fun getRepository(): Repository {
        val songBookDao = SongDatabase.getDatabase(applicationContext).songBookDao
        return Repository(songBookDao)
    }

}
