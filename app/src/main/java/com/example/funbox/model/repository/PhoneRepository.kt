package com.example.funbox.model.repository

import android.content.Context
import com.example.funbox.model.entitiy.Phone
import com.example.funbox.model.storage.PhoneStorage

class PhoneRepository(private val phoneStorage: PhoneStorage) {

    fun getAll(context: Context): List<Phone> {
        return phoneStorage.getAll(context)
    }

    fun saveAll(context: Context, phones: List<Phone>) {
        phoneStorage.saveAll(context, phones)
    }
}