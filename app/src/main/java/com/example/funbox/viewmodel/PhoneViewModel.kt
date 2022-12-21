package com.example.funbox.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.funbox.model.entitiy.Phone
import com.example.funbox.model.repository.PhoneRepository

class PhoneViewModel(
    private val phoneRepository: PhoneRepository
) : ViewModel() {

    private var _allPhones: ArrayList<Phone> = ArrayList(phoneRepository.getAll())

    private var mutableAllPhones = MutableLiveData<ArrayList<Phone>>(arrayListOf())
    val allPhones: LiveData<ArrayList<Phone>> = mutableAllPhones

    fun getPhones() {
        mutableAllPhones.value = _allPhones
    }

    fun savePhones(phones: List<Phone>) {
        phoneRepository.saveAll(phones)
    }

    fun addPhone(phone: Phone) {
        _allPhones.add(phone)
        mutableAllPhones.value = _allPhones
    }

    fun updatePhone(phone: Phone) {
        _allPhones.let {
            it.forEachIndexed { i, _ ->
                if (it[i].id == phone.id) {
                    it[i] = phone
                }
            }
        }
        mutableAllPhones.value = _allPhones
    }

    fun buyPhone(phone: Phone) {
        _allPhones.forEachIndexed { i, _ ->
            if (_allPhones[i].id == phone.id) {
                if (phone.count.toInt() >= 1) {
                    _allPhones[i] = phone.copy(count = (phone.count.toInt() - 1).toString())
                } else if (phone.count.toInt() == 0) {
                    _allPhones.removeAt(i)
                }
                return@forEachIndexed
            }
        }
        mutableAllPhones.value = _allPhones
    }
}