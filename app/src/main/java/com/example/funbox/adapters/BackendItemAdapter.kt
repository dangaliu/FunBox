package com.example.funbox.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.funbox.databinding.BackendRvItemBinding
import com.example.funbox.interfaces.OnBackendItemClickListener
import com.example.funbox.model.entitiy.Phone

class BackendItemAdapter(private val listener: OnBackendItemClickListener) : RecyclerView.Adapter<BackendItemAdapter.BackendItemViewHolder>() {

    private var items: ArrayList<Phone> = arrayListOf()

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
            ivOpen.setOnClickListener {
                listener.onClick(item)
            }
        }
    }

    override fun getItemCount(): Int = items.size


    fun updateItems(items: List<Phone>) {
        this.items = ArrayList(items)
        notifyDataSetChanged()
    }
}