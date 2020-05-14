package dev.rtrilia.truthinsong

import android.app.Application
import timber.log.Timber

class SongApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
    }
}
