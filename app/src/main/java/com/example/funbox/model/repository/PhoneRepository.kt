package com.example.funbox.model.repository

import com.example.funbox.model.entitiy.Phone
import com.example.funbox.model.storage.PhoneStorage

class PhoneRepository(private val phoneStorage: PhoneStorage) {

    suspend fun getAll(): List<Phone> {
        return phoneStorage.getAll()
    }

    suspend fun saveAll(phones: List<Phone>) {
        phoneStorage.saveAll(phones)
    }
}