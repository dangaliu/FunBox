package com.example.funbox.viewmodel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.funbox.model.entitiy.Phone
import com.example.funbox.model.repository.PhoneRepository

class PhoneViewModel(
    private val phoneRepository: PhoneRepository
) : ViewModel() {

    private var _phones = arrayListOf<Phone>()
    private var mutablePhones = MutableLiveData<ArrayList<Phone>>(arrayListOf())
    val phones: LiveData<ArrayList<Phone>> = mutablePhones

    fun getPhones(context: Context) {
        _phones = ArrayList(phoneRepository.getAll(context))
        mutablePhones.value = _phones
    }

    fun savePhones(context: Context, phones: List<Phone>) {
        phoneRepository.saveAll(context, phones)
    }

    fun addPhone(phone: Phone) {
        _phones.add(phone)
        mutablePhones.value = _phones
    }

    fun updatePhone(phone: Phone) {
        _phones.let {
            it.forEachIndexed { i, _ ->
                if (it[i].id == phone.id) {
                    println(it[i])
                    it[i] = phone
                    println(it[i])
                }
            }
        }
        mutablePhones.value = _phones
    }
}