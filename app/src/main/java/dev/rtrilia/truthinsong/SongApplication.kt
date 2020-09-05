package dev.rtrilia.truthinsong

import android.util.Log
import androidx.multidex.MultiDexApplication
import com.google.firebase.crashlytics.FirebaseCrashlytics
import dagger.hilt.android.HiltAndroidApp
import leakcanary.LeakCanary
import timber.log.Timber

@HiltAndroidApp
class SongApplication : MultiDexApplication() {


    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) Timber.plant(Timber.DebugTree())
        else Timber.plant(CrashReportingTree())
    }

    private class CrashReportingTree : Timber.Tree() {
        override fun log(priority: Int, tag: String?, message: String, t: Throwable?) {
            if (priority == Log.VERBOSE || priority == Log.DEBUG || priority == Log.INFO) return

            FirebaseCrashlytics.getInstance().setCustomKey("priority", priority)
            tag?.let { FirebaseCrashlytics.getInstance().setCustomKey("tag", it) }
            FirebaseCrashlytics.getInstance().setCustomKey("message", message)

            if (t == null) {
                FirebaseCrashlytics.getInstance().log(message)
            } else {
                FirebaseCrashlytics.getInstance().log(t.message.toString())
            }

        }

    }

}
