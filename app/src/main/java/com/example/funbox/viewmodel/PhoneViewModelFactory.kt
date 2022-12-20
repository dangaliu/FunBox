package com.example.funbox.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.funbox.model.repository.PhoneRepository

class PhoneViewModelFactory(
    private val phoneRepository: PhoneRepository
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return PhoneViewModel(
            phoneRepository = phoneRepository
        ) as T
    }
}