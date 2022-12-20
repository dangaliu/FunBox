package com.example.funbox.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.funbox.databinding.BackendRvItemBinding
import com.example.funbox.model.entitiy.StoreItem

class BackendItemAdapter : RecyclerView.Adapter<BackendItemAdapter.BackendItemViewHolder>() {

    private val items: ArrayList<StoreItem> = arrayListOf()

    inner class BackendItemViewHolder(val binding: BackendRvItemBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BackendItemViewHolder {
        val binding =
            BackendRvItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return BackendItemViewHolder((binding))
    }

    override fun onBindViewHolder(holder: BackendItemViewHolder, position: Int) {
        val item = items[position]
        with(holder.binding) {
            tvTitle.text = item.title.trim('\"')
            tvCount.text = "${item.count.trim('"')} шт."
        }
    }

    override fun getItemCount(): Int = items.size

    fun updateItems(items: List<StoreItem>) {
        this.items.clear()
        this.items.addAll(items)
        notifyDataSetChanged()
    }
}