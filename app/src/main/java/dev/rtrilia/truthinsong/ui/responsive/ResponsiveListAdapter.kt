package dev.rtrilia.truthinsong.ui.responsive

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.qtalk.recyclerviewfastscroller.RecyclerViewFastScroller
import dev.rtrilia.truthinsong.databinding.ItemResponsiveListBinding
import dev.rtrilia.truthinsong.models.ResponsiveList

class ResponsiveListAdapter(private val clickListener: ResponsiveListItemListener) :
    PagedListAdapter<ResponsiveList, ResponsiveListAdapter.ViewHolder>(ResponsiveListDiffCallback), RecyclerViewFastScroller.OnPopupTextUpdate {

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
        return ViewHolder(ItemResponsiveListBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val listItem = getItem(position)
        holder.binding.model = listItem
        holder.binding.clickListener = clickListener
    }

    override fun onChange(position: Int): CharSequence {
        return getItem(position)?.song_id.toString()
    }

}
