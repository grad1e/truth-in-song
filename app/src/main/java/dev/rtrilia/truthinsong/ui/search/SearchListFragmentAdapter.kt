package dev.rtrilia.truthinsong.ui.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import dev.rtrilia.truthinsong.databinding.ItemFragmentSearchBinding
import dev.rtrilia.truthinsong.models.Song

class SearchListFragmentAdapter(private val clickListener: SearchListItemClickListener) : RecyclerView.Adapter<SearchListFragmentAdapter.ViewHolder>() {

    private val searchList = arrayListOf<Song>()

    fun setListData(listData: List<Song>) {
        searchList.clear()
        searchList.addAll(listData)
        notifyDataSetChanged()
    }

    class ViewHolder(val binding: ItemFragmentSearchBinding) : RecyclerView.ViewHolder(binding.root)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemFragmentSearchBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun getItemCount(): Int {
        return searchList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.model = searchList[position]
        holder.binding.clickListener = clickListener
    }
}