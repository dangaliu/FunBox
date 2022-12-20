package com.example.funbox.model.storage

import android.content.Context
import com.example.funbox.model.entitiy.Phone

interface PhoneStorage {


    fun getAll(context: Context): List<Phone>

    fun saveAll(context: Context, phones: List<Phone>)
}