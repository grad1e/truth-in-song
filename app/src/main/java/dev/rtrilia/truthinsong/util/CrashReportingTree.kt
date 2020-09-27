package dev.rtrilia.truthinsong.util

import android.util.Log
import com.google.firebase.crashlytics.FirebaseCrashlytics
import dev.rtrilia.truthinsong.data.preference.PreferenceHandler
import timber.log.Timber
import javax.inject.Inject

class CrashReportingTree @Inject constructor(private val sharedPreferences: PreferenceHandler) :
    Timber.Tree() {
    override fun log(priority: Int, tag: String?, message: String, t: Throwable?) {
        if (priority == Log.VERBOSE || priority == Log.INFO) return
        FirebaseCrashlytics.getInstance().setCustomKey("uiMode", sharedPreferences.getUiMode())
        FirebaseCrashlytics.getInstance()
            .setCustomKey("shuffleMode", sharedPreferences.getShuffleMode())
        if (t == null) {
            FirebaseCrashlytics.getInstance().log(message)
        } else {
            FirebaseCrashlytics.getInstance().log(t.message.toString())
        }
    }
}