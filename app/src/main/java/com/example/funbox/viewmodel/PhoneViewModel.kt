package com.example.funbox.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.funbox.model.entitiy.Phone
import com.example.funbox.model.repository.PhoneRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class PhoneViewModel(
    private val phoneRepository: PhoneRepository
) : ViewModel() {

    private var _allPhones: ArrayList<Phone> = arrayListOf()

    private var mutableAllPhones = MutableLiveData<ArrayList<Phone>>(arrayListOf())
    val allPhones: LiveData<ArrayList<Phone>> = mutableAllPhones

    init {
        viewModelScope.launch(Dispatchers.IO) {
            _allPhones = ArrayList(phoneRepository.getAll())
            withContext(Dispatchers.Main) {
                mutableAllPhones.value = _allPhones
            }
        }
    }

    fun savePhones(phones: List<Phone>) {
        viewModelScope.launch(Dispatchers.IO) {
            phoneRepository.saveAll(phones)
        }
    }

    fun addPhone(phone: Phone) {
        viewModelScope.launch(Dispatchers.Default) {
            _allPhones.add(phone)
            withContext(Dispatchers.Main) {
                mutableAllPhones.value = _allPhones
            }
        }
    }

    fun updatePhone(phone: Phone) {
        viewModelScope.launch(Dispatchers.Default) {
            _allPhones.let {
                it.forEachIndexed { i, _ ->
                    if (it[i].id == phone.id) {
                        it[i] = phone
                    }
                }
            }
            withContext(Dispatchers.Main) {
                mutableAllPhones.value = _allPhones
            }
        }
    }

    fun buyPhone(phone: Phone) {
        viewModelScope.launch(Dispatchers.Default) {
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
            withContext(Dispatchers.Main) {
                mutableAllPhones.value = _allPhones
            }
        }
    }
}