package dev.rtrilia.truthinsong.ui.english

class EnglishListItemListener(val clickListener: (id: String) -> Unit) {
    fun onClick(id: String) {
        clickListener(id)
    }
}