package com.example.funbox.view.fragment

import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.funbox.databinding.FragmentEditItemBinding
import com.example.funbox.model.entitiy.StoreItem
import com.example.funbox.utils.STORE_ITEM_KEY

class EditItemFragment : Fragment() {

    private lateinit var binding: FragmentEditItemBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentEditItemBinding.inflate(inflater, container, false)
        init()
        setListeners()
        return binding.root
    }

    private fun init() {
        val defaultStoreItem = StoreItem("", "", "")
        val storeItem: StoreItem = if (Build.VERSION.SDK_INT >= 33) {
            arguments?.getParcelable(STORE_ITEM_KEY, StoreItem::class.java) ?: defaultStoreItem
        } else {
            arguments?.getParcelable(STORE_ITEM_KEY) ?: defaultStoreItem
        }

        if (storeItem != defaultStoreItem) {
            binding.apply {
                etTitle.setText(storeItem.title)
                etPrice.setText(storeItem.price)
                etCount.setText(storeItem.count)
            }
        }
    }

    private fun setListeners() {
        binding.btnClose.setOnClickListener {
            findNavController().popBackStack()
        }
    }

}