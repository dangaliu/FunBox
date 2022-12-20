package com.example.funbox.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager2.widget.ViewPager2
import com.example.funbox.R
import com.example.funbox.adapters.StoreItemAdapter
import com.example.funbox.databinding.FragmentStoreFrontBinding
import com.example.funbox.utils.CsvHelper

class StoreFrontFragment : Fragment() {

    private lateinit var binding: FragmentStoreFrontBinding
    private lateinit var adapter: StoreItemAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentStoreFrontBinding.inflate(inflater, container, false)
        init()
        return binding.root
    }

    private fun init() {
        adapter = StoreItemAdapter().also {
            it.updateItems(CsvHelper.readFromCsv(requireContext()))
        }
        binding.storeViewPager.also {
            it.orientation = ViewPager2.ORIENTATION_HORIZONTAL
            it.adapter = adapter
        }
    }
}