package com.example.funbox.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.funbox.databinding.StoreItemBinding
import com.example.funbox.model.entitiy.StoreItem

class StoreItemAdapter : RecyclerView.Adapter<StoreItemAdapter.StoreItemViewHolder>() {

    private val items: ArrayList<StoreItem> = arrayListOf()

    inner class StoreItemViewHolder(val binding: StoreItemBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StoreItemViewHolder {
        val binding = StoreItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return StoreItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: StoreItemViewHolder, position: Int) {
        val item = items[position]
        with(holder.binding) {
            itemTitle.text = item.title
            itemPriceValue.text = "${item.price} руб."
            itemCountValue.text = "${item.count} шт."
        }
    }

    override fun getItemCount(): Int = items.size

    private fun updateItems(items: List<StoreItem>) {
        this.items.clear()
        this.items.addAll(items)
        notifyDataSetChanged()
    }
}