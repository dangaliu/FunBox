package com.example.funbox.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.widget.ViewPager2
import com.example.funbox.adapters.StoreItemAdapter
import com.example.funbox.databinding.FragmentStoreFrontBinding
import com.example.funbox.model.repository.PhoneRepository
import com.example.funbox.model.storage.CsvPhoneStorage
import com.example.funbox.view.activity.MainActivity
import com.example.funbox.viewmodel.PhoneViewModel
import com.example.funbox.viewmodel.PhoneViewModelFactory

class StoreFrontFragment : BaseFragment() {

    private lateinit var binding: FragmentStoreFrontBinding
    private lateinit var adapter: StoreItemAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        binding = FragmentStoreFrontBinding.inflate(inflater, container, false)
        init()
        setObservers()
        return binding.root
    }

    private fun setObservers() {
        viewModel.phones.observe(requireActivity()) {
            adapter.updateItems(it)
        }
    }

    private fun init() {
        adapter = StoreItemAdapter()
        binding.storeViewPager.also {
            it.orientation = ViewPager2.ORIENTATION_HORIZONTAL
            it.adapter = adapter
        }
    }
}