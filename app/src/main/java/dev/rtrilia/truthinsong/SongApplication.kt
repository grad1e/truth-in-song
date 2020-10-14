package dev.rtrilia.truthinsong

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import dev.rtrilia.truthinsong.data.preference.PreferenceHandler
import timber.log.Timber
import javax.inject.Inject

@HiltAndroidApp
class SongApplication : Application() {

    @Inject
    lateinit var sharedPreferences: PreferenceHandler

    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
    }

}
