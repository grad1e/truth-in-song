package dev.rtrilia.truthinsong.ui.search

class SearchListItemClickListener(val clickListener: (id: String) -> Unit) {
    fun onClick(id: String) {
        clickListener(id)
    }
}