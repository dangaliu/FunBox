package com.example.funbox.view.fragment

import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.Toolbar
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.MenuProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView.VERTICAL
import com.example.funbox.R
import com.example.funbox.adapters.BackendItemAdapter
import com.example.funbox.databinding.FragmentBackendBinding
import com.example.funbox.interfaces.OnBackendItemClickListener
import com.example.funbox.model.entitiy.Phone
import com.example.funbox.utils.STORE_ITEM_KEY

class BackendFragment : BaseFragment(), OnBackendItemClickListener {
    private lateinit var binding: FragmentBackendBinding
    private lateinit var backendItemAdapter: BackendItemAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentBackendBinding.inflate(inflater, container, false)
        init()
        setObservers()
        return binding.root
    }

    private fun setObservers() {
        viewModel.phones.observe(requireActivity()) {
            backendItemAdapter.updateItems(it)
        }
    }

    private fun init() {
        backendItemAdapter = BackendItemAdapter(this)

        binding.rvItems.also {
            it.layoutManager = LinearLayoutManager(requireContext())
            it.adapter = backendItemAdapter
            it.addItemDecoration(DividerItemDecoration(requireContext(), VERTICAL))
        }

        (requireActivity() as AppCompatActivity).setSupportActionBar(binding.toolbar)

        requireActivity().addMenuProvider(object : MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menuInflater.inflate(R.menu.backend_toolbar_menu, menu)
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                when (menuItem.itemId) {
                    R.id.add -> {
                        findNavController().navigate(R.id.action_backendFragment_to_editItemFragment)
                        return true
                    }
                    else -> return false
                }
            }
        }, viewLifecycleOwner)
    }

    override fun onClick(item: Phone) {
        findNavController().navigate(
            R.id.action_backendFragment_to_editItemFragment,
            Bundle().also {
                it.putParcelable(STORE_ITEM_KEY, item)
            })
    }
}