package dev.rtrilia.truthinsong.ui.malayalam

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import dev.rtrilia.truthinsong.data.models.MalayalamList
import dev.rtrilia.truthinsong.databinding.ItemMalayalamListBinding
import dev.rtrilia.truthinsong.util.setCustomClickListener


class MalayalamListAdapter(val clickListener: (MalayalamList) -> Unit) :
    PagedListAdapter<MalayalamList, MalayalamListAdapter.ViewHolder>(MalayalamListDiffCallback) {

    companion object MalayalamListDiffCallback : DiffUtil.ItemCallback<MalayalamList>() {
        override fun areItemsTheSame(oldItem: MalayalamList, newItem: MalayalamList): Boolean {
            return oldItem.song_id == newItem.song_id
        }

        override fun areContentsTheSame(oldItem: MalayalamList, newItem: MalayalamList): Boolean {
            return oldItem == newItem
        }
    }

    class ViewHolder(val binding: ItemMalayalamListBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemMalayalamListBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val listItem = getItem(position)
        holder.binding.model = listItem
        holder.binding.songLayout.setCustomClickListener {
            listItem?.let {
                clickListener(listItem)
            }
        }
    }

}
