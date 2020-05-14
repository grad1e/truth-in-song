package dev.rtrilia.truthinsong.ui.malayalam

class MalayalamListItemClickListener(val clickListener: (id: String) -> Unit) {
    fun onClick(id: String) {
        clickListener(id)
    }
}