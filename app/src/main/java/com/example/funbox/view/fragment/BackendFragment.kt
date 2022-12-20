package com.example.funbox.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView.*
import com.example.funbox.R
import com.example.funbox.adapters.BackendItemAdapter
import com.example.funbox.databinding.FragmentBackendBinding
import com.example.funbox.utils.CsvHelper

class BackendFragment : Fragment() {

    private lateinit var binding: FragmentBackendBinding
    private lateinit var adapter: BackendItemAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentBackendBinding.inflate(inflater, container, false)
        init()
        return binding.root
    }

    private fun init() {
        adapter = BackendItemAdapter().also {
            it.updateItems(CsvHelper.readFromCsv(requireContext()))
        }
        binding.rvItems.also {
            it.layoutManager = LinearLayoutManager(requireContext())
            it.adapter = adapter
            it.addItemDecoration(DividerItemDecoration(requireContext(), VERTICAL))
        }
    }
}