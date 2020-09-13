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


    fun getUiMode() = preferences.getInt(UI_MODE, UiMode.SYSTEM_DEFAULT)
    fun setUiMode(value: Int) = preferences.edit {
        putInt(UI_MODE, value)
    }

    fun getFontSize() = preferences.getFloat(FONT_SIZE, 0f)
    fun setFontSize(value: Float) = preferences.edit {
        putFloat(FONT_SIZE, value)
    }

    fun getFontSizeSmall() = preferences.getFloat(FONT_SIZE_SMALL, 0f)
    fun setFontSizeSmall(value: Float) = preferences.edit {
        putFloat(FONT_SIZE_SMALL, value)
    }

    fun getShuffleMode() = preferences.getInt(SHUFFLE_MODE, ShuffleMode.MALAYALAM_ONLY)
    fun setShuffleMode(value: Int) = preferences.edit {
        putInt(SHUFFLE_MODE, value)
    }

}