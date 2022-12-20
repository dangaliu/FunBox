package com.example.funbox.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView.VERTICAL
import com.example.funbox.R
import com.example.funbox.adapters.BackendItemAdapter
import com.example.funbox.databinding.FragmentBackendBinding
import com.example.funbox.interfaces.OnBackendItemClickListener
import com.example.funbox.model.entitiy.Phone
import com.example.funbox.model.repository.PhoneRepository
import com.example.funbox.model.storage.CsvPhoneStorage
import com.example.funbox.utils.STORE_ITEM_KEY
import com.example.funbox.view.activity.MainActivity
import com.example.funbox.viewmodel.PhoneViewModel
import com.example.funbox.viewmodel.PhoneViewModelFactory

class BackendFragment : BaseFragment(), OnBackendItemClickListener {

    private lateinit var binding: FragmentBackendBinding
    private lateinit var backendItemAdapter: BackendItemAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentBackendBinding.inflate(inflater, container, false)
        init()
        return binding.root
    }

    private fun setObservers() {
        viewModel.phones.observe(requireActivity()) {
            backendItemAdapter.updateItems(it)
        }
    }

    private fun init() {
        backendItemAdapter = BackendItemAdapter(this)
        setObservers()
        binding.rvItems.also {
            it.layoutManager = LinearLayoutManager(requireContext())
            it.adapter = backendItemAdapter
            it.addItemDecoration(DividerItemDecoration(requireContext(), VERTICAL))
        }
    }

    override fun onClick(item: Phone) {
        findNavController().navigate(
            R.id.action_backendFragment_to_editItemFragment,
            Bundle().also {
                it.putParcelable(STORE_ITEM_KEY, item)
            })
    }
}