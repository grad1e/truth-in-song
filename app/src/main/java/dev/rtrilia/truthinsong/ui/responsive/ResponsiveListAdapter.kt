package dev.rtrilia.truthinsong.ui.responsive

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import dev.rtrilia.truthinsong.data.models.ResponsiveList
import dev.rtrilia.truthinsong.databinding.ItemResponsiveListBinding

class ResponsiveListAdapter(val onClick: (ResponsiveList) -> Unit) :
    PagedListAdapter<ResponsiveList, ResponsiveListAdapter.ViewHolder>(ResponsiveListDiffCallback) {

    companion object ResponsiveListDiffCallback : DiffUtil.ItemCallback<ResponsiveList>() {
        override fun areItemsTheSame(oldItem: ResponsiveList, newItem: ResponsiveList): Boolean {
            return oldItem.song_id == newItem.song_id
        }

        override fun areContentsTheSame(oldItem: ResponsiveList, newItem: ResponsiveList): Boolean {
            return oldItem == newItem
        }
    }

    class ViewHolder(val binding: ItemResponsiveListBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemResponsiveListBinding.inflate(
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
