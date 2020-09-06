package dev.rtrilia.truthinsong.data.preference

import android.content.SharedPreferences
import androidx.core.content.edit
import dev.rtrilia.truthinsong.util.UiMode
import javax.inject.Inject

class PreferenceHandler @Inject constructor(private val preferences: SharedPreferences) {

    companion object {
        const val UI_MODE = "ui_mode"
        const val FONT_SIZE = "font_size"
        const val FONT_SIZE_SMALL = "font_size_small"
    }

    fun getUiMode() = preferences.getInt(UI_MODE, UiMode.SYSTEM_DEFAULT)
    fun setUiMode(value: Int) = preferences.edit {
        putInt(UI_MODE, value)
    }

    fun getFontSize() = preferences.getFloat(FONT_SIZE, 44f)
    fun setFontSize(value: Float) = preferences.edit {
        putFloat(FONT_SIZE, value)
    }

    fun getFontSizeSmall() = preferences.getFloat(FONT_SIZE_SMALL, 33f)
    fun setFontSizeSmall(value: Float) = preferences.edit {
        putFloat(FONT_SIZE_SMALL, value)
    }

}