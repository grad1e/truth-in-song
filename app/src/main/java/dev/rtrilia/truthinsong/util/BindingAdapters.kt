package dev.rtrilia.truthinsong.util

import android.view.View
import android.view.ViewGroup
import androidx.core.view.*
import androidx.databinding.BindingAdapter

@BindingAdapter("topInsetMargin")
fun View.setTopInsetMargin(value: Boolean) {
    if (value) {
        val initialTopInset = this.marginTop
        ViewCompat.setOnApplyWindowInsetsListener(this) { v, insets ->
            val topInset =
                insets.getInsets(WindowInsetsCompat.Type.systemBars()).top
            v.updateLayoutParams {
                (this as ViewGroup.MarginLayoutParams).updateMargins(top = topInset + initialTopInset)
            }
            insets
        }
    }
}

@BindingAdapter("bottomInsetMargin")
fun View.setBottomInsetMargin(value: Boolean) {
    if (value) {
        val initialBottomInset = this.marginBottom
        ViewCompat.setOnApplyWindowInsetsListener(this) { v, insets ->
            val bottomInset =
                insets.getInsets(WindowInsetsCompat.Type.systemBars() or WindowInsetsCompat.Type.ime()).bottom
            v.updateLayoutParams {
                (this as ViewGroup.MarginLayoutParams).updateMargins(bottom = bottomInset + initialBottomInset)
            }
            insets
        }
    }
}

@BindingAdapter("topInsetPadding")
fun View.setTopInsetPadding(value: Boolean) {
    if (value) {
        ViewCompat.setOnApplyWindowInsetsListener(this) { v, insets ->
            val topInset =
                insets.getInsets(WindowInsetsCompat.Type.systemBars()).top
            v.updatePadding(top = topInset)
            insets
        }
    }
}

@BindingAdapter("bottomInsetPadding")
fun View.setBottomInsetPadding(value: Boolean) {
    if (value) {
        ViewCompat.setOnApplyWindowInsetsListener(this) { v, insets ->
            val bottomInset =
                insets.getInsets(WindowInsetsCompat.Type.systemBars() or WindowInsetsCompat.Type.ime()).bottom
            v.updatePadding(bottom = bottomInset)
            insets
        }
    }
}