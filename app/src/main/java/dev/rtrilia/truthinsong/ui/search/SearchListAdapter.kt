package dev.rtrilia.truthinsong.ui.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import dev.rtrilia.truthinsong.data.models.Song
import dev.rtrilia.truthinsong.databinding.ItemFragmentSearchBinding

class SearchListAdapter(val onClick: (Song) -> Unit) :
    ListAdapter<Song, SearchListAdapter.ViewHolder>(SearchListDiffUtil) {

    object SearchListDiffUtil : DiffUtil.ItemCallback<Song>() {
        override fun areItemsTheSame(oldItem: Song, newItem: Song): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Song, newItem: Song): Boolean {
            return oldItem == newItem
        }
    }

    class ViewHolder(val binding: ItemFragmentSearchBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemFragmentSearchBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.model = getItem(holder.layoutPosition)
        holder.binding.clickListener = this
    }
}