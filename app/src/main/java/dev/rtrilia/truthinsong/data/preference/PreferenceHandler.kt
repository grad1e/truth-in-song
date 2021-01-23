package dev.rtrilia.truthinsong.data.preference

import android.content.SharedPreferences
import androidx.core.content.edit
import dev.rtrilia.truthinsong.util.Constants.FONT_SIZE
import dev.rtrilia.truthinsong.util.Constants.FONT_SIZE_SMALL
import dev.rtrilia.truthinsong.util.Constants.SHUFFLE_MODE
import dev.rtrilia.truthinsong.util.Constants.UI_MODE
import dev.rtrilia.truthinsong.util.ShuffleMode
import dev.rtrilia.truthinsong.util.UiMode
import javax.inject.Inject

class PreferenceHandler @Inject constructor(private val preferences: SharedPreferences) {

    var uiMode
        get() = preferences.getInt(UI_MODE, UiMode.SYSTEM_DEFAULT)
        set(value) = preferences.edit { putInt(UI_MODE, value) }

    var fontSize
        get() = preferences.getFloat(FONT_SIZE, 0f)
        set(value) = preferences.edit { putFloat(FONT_SIZE, value) }

    var fontSizeSmall
        get() = preferences.getFloat(FONT_SIZE_SMALL, 0f)
        set(value) = preferences.edit { putFloat(FONT_SIZE_SMALL, value) }

    var shuffleMode
        get() = preferences.getInt(SHUFFLE_MODE, ShuffleMode.MALAYALAM_ONLY)
        set(value) = preferences.edit { putInt(SHUFFLE_MODE, value) }

}