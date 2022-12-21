package com.example.funbox.model.storage

import com.example.funbox.model.entitiy.Phone

interface PhoneStorage {


    suspend fun getAll(): List<Phone>

    suspend fun saveAll(phones: List<Phone>)
}