package com.example.funbox.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager2.widget.ViewPager2
import com.example.funbox.adapters.StoreItemAdapter
import com.example.funbox.databinding.FragmentStoreFrontBinding
import com.example.funbox.interfaces.OnBuyPhoneListener
import com.example.funbox.model.entitiy.Phone

class StoreFrontFragment : BaseFragment(), OnBuyPhoneListener {

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
        viewModel.allPhones.observe(requireActivity()) {
            adapter.updateItems(it)
        }
    }

    private fun init() {
        adapter = StoreItemAdapter(this)
        binding.storeViewPager.also {
            it.orientation = ViewPager2.ORIENTATION_HORIZONTAL
            it.adapter = adapter
        }
    }

    override fun onBuy(phone: Phone) {
        viewModel.buyPhone(phone)
    }

}