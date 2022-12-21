package com.example.funbox.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.funbox.databinding.StoreItemBinding
import com.example.funbox.interfaces.OnBuyPhoneListener
import com.example.funbox.model.entitiy.Phone

class StoreItemAdapter(private val onBuyPhoneListener: OnBuyPhoneListener) :
    RecyclerView.Adapter<StoreItemAdapter.StoreItemViewHolder>() {

    private var phonesInStock: ArrayList<Phone> = arrayListOf()

    inner class StoreItemViewHolder(val binding: StoreItemBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StoreItemViewHolder {
        val binding = StoreItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return StoreItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: StoreItemViewHolder, position: Int) {
        val item = phonesInStock[position]
        with(holder.binding) {
            if (item.count.toInt() > 0) {
                itemTitle.text = item.title
                itemPriceValue.text = "${item.price} руб."
                itemCountValue.text = "${item.count} шт."
                btnBuy.setOnClickListener {
                    onBuyPhoneListener.onBuy(item)
                }
            }
        }
    }

    override fun getItemCount(): Int = phonesInStock.size

    fun updateItems(items: List<Phone>) {
        phonesInStock.clear()
        items.forEach {
            if (it.count.toInt() >= 1) {
                phonesInStock.add(it)
            }
        }
        notifyDataSetChanged()
    }
}