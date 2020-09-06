package dev.rtrilia.truthinsong

import android.app.Application
import android.util.Log
import com.google.firebase.crashlytics.FirebaseCrashlytics
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

@HiltAndroidApp
class SongApplication : Application() {


    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) Timber.plant(Timber.DebugTree())
        else Timber.plant(CrashReportingTree())
    }

    private class CrashReportingTree : Timber.Tree() {
        override fun log(priority: Int, tag: String?, message: String, t: Throwable?) {
            if (priority == Log.VERBOSE || priority == Log.INFO) return

            if (t == null) {
                FirebaseCrashlytics.getInstance().log(message)
            } else {
                FirebaseCrashlytics.getInstance().log(t.message.toString())
            }
        }
    }

}
