package com.example.funbox.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.funbox.model.entitiy.Phone
import com.example.funbox.model.repository.PhoneRepository
import com.example.funbox.utils.CsvHelper

class PhoneViewModel(
    private val phoneRepository: PhoneRepository
) : ViewModel() {

    private var _phones: ArrayList<Phone> = ArrayList(phoneRepository.getAll())
    private var mutablePhones = MutableLiveData<ArrayList<Phone>>(arrayListOf())
    val phones: LiveData<ArrayList<Phone>> = mutablePhones

    fun getPhones() {
        mutablePhones.value = _phones
    }

    fun savePhones(phones: List<Phone>) {
        phoneRepository.saveAll(phones)
    }

    fun addPhone(phone: Phone) {
        _phones.add(phone)
        mutablePhones.value = _phones
    }

    fun updatePhone(phone: Phone) {
        _phones.let {
            it.forEachIndexed { i, _ ->
                if (it[i].id == phone.id) {
                    it[i] = phone
                }
            }
        }
        mutablePhones.value = _phones
    }
}