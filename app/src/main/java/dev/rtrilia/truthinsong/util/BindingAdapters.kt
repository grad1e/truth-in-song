package dev.rtrilia.truthinsong.util

import android.view.View
import android.view.ViewGroup
import androidx.core.view.*
import androidx.databinding.BindingAdapter

@BindingAdapter("bottomInsetMargin")
fun View.setBottomInset(value: Boolean) {
    if (value) {
        val initialBottomInset = this.marginBottom
        ViewCompat.setOnApplyWindowInsetsListener(this) { v, insets ->
            val bottomInset =
                insets.getInsetsIgnoringVisibility(WindowInsetsCompat.Type.systemBars()).bottom
            v.updateLayoutParams {
                (this as ViewGroup.MarginLayoutParams).updateMargins(bottom = bottomInset + initialBottomInset)
            }
            insets
        }
    }
}

@BindingAdapter("topInsetMargin")
fun View.setTopInset(value: Boolean) {
    if (value) {
        val initialTopInset = this.marginTop
        ViewCompat.setOnApplyWindowInsetsListener(this) { v, insets ->
            val topInset =
                insets.getInsetsIgnoringVisibility(WindowInsetsCompat.Type.systemBars()).top
            v.updateLayoutParams {
                (this as ViewGroup.MarginLayoutParams).updateMargins(top = topInset + initialTopInset)
            }
            insets
        }
    }
}

@BindingAdapter("bottomInsetPadding")
fun View.setPadding(value: Boolean) {
    if (value) {
        ViewCompat.setOnApplyWindowInsetsListener(this) { v, insets ->
            val bottomInset =
                insets.getInsetsIgnoringVisibility(WindowInsetsCompat.Type.systemBars()).bottom
            v.updatePadding(bottom = bottomInset)
            insets
        }
    }
}