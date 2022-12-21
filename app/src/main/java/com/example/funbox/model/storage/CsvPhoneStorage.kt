package com.example.funbox.model.storage

import com.example.funbox.model.entitiy.Phone
import com.example.funbox.utils.CsvHelper

class CsvPhoneStorage : PhoneStorage {

    override fun getAll(): List<Phone> {
        return CsvHelper.readFromCsv()
    }

    override fun saveAll(phones: List<Phone>) {
        CsvHelper.writeToCsv(phones)
    }
}