package dev.rtrilia.truthinsong.util

import android.content.Context
import android.widget.Toast

fun Context.showToast(string: String) {
    Toast.makeText(this, string, Toast.LENGTH_LONG).show()
}