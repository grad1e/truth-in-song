package dev.rtrilia.truthinsong.ui.english

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.qtalk.recyclerviewfastscroller.RecyclerViewFastScroller
import dev.rtrilia.truthinsong.databinding.ItemEnglishListBinding
import dev.rtrilia.truthinsong.models.EnglishList

class EnglishListAdapter(private val clickItemListener: EnglishListItemListener) :
    PagedListAdapter<EnglishList, EnglishListAdapter.ViewHolder>(EnglishListDiffCallback), RecyclerViewFastScroller.OnPopupTextUpdate {

    companion object EnglishListDiffCallback : DiffUtil.ItemCallback<EnglishList>() {
        override fun areItemsTheSame(oldItem: EnglishList, newItem: EnglishList): Boolean {
            return oldItem.song_id == newItem.song_id
        }

        override fun areContentsTheSame(oldItem: EnglishList, newItem: EnglishList): Boolean {
            return oldItem == newItem
        }
    }

    class ViewHolder(val binding: ItemEnglishListBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemEnglishListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val listItem = getItem(position)
        holder.binding.model = listItem
        holder.binding.clickListener = clickItemListener
    }

    override fun onChange(position: Int): CharSequence {
        return getItem(position)?.song_id.toString()
    }

}




