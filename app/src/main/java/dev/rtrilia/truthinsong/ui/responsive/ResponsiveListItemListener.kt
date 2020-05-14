package dev.rtrilia.truthinsong.ui.responsive

class ResponsiveListItemListener(val clickListener: (id: String) -> Unit) {
    fun onClick(id: String) {
        clickListener(id)
    }
}