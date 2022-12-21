package com.example.funbox.view.fragment

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.funbox.databinding.FragmentEditPhoneBinding
import com.example.funbox.model.entitiy.Phone
import com.example.funbox.model.repository.PhoneRepository
import com.example.funbox.model.storage.CsvPhoneStorage
import com.example.funbox.utils.STORE_ITEM_KEY
import com.example.funbox.utils.defaultPhone
import com.example.funbox.view.activity.MainActivity
import com.example.funbox.viewmodel.PhoneViewModel
import com.example.funbox.viewmodel.PhoneViewModelFactory

class EditPhoneFragment : BaseFragment() {

    private lateinit var binding: FragmentEditPhoneBinding
    private var isEdit = false
    private lateinit var phone: Phone

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        binding = FragmentEditPhoneBinding.inflate(inflater, container, false)
        init()
        setListeners()
        return binding.root
    }

    private fun init() {
        phone = defaultPhone
        arguments?.let {
            isEdit = true
            phone = if (Build.VERSION.SDK_INT >= 33) {
                it.getParcelable(STORE_ITEM_KEY, Phone::class.java) ?: defaultPhone
            } else {
                it.getParcelable(STORE_ITEM_KEY) ?: defaultPhone
            }
            if (phone != defaultPhone) {
                isEdit = true
                binding.apply {
                    etTitle.setText(phone.title)
                    etPrice.setText(phone.price)
                    etCount.setText(phone.count)
                }
            }
        }
    }

    private fun setListeners() {
        binding.btnClose.setOnClickListener {
            findNavController().popBackStack()
        }

        binding.btnSave.setOnClickListener {
            if (isEdit) {
                saveItem()
            } else {
                addNewItem()
            }
            findNavController().popBackStack()
        }
    }

    private fun saveItem() {
        val title = binding.etTitle.text.toString()
        val price = binding.etPrice.text.toString()
        val count = binding.etCount.text.toString()
        val changedPhone = Phone(id = phone.id, title = title, price = price, count = count)
        if (changedPhone != phone) {
            viewModel.updatePhone(changedPhone)
        }

    }

    private fun addNewItem() {
        val title = binding.etTitle.text.toString()
        val price = binding.etPrice.text.toString()
        val count = binding.etCount.text.toString()
        val newPhone = Phone(title = title, price = price, count = count)
        viewModel.addPhone(newPhone)
    }

}