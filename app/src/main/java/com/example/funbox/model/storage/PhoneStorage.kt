package com.example.funbox.model.storage

import com.example.funbox.model.entitiy.Phone

interface PhoneStorage {


    fun getAll(): List<Phone>

    fun saveAll(phones: List<Phone>)
}