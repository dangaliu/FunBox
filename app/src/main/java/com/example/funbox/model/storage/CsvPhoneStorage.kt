package com.example.funbox.model.storage

import android.content.Context
import com.example.funbox.model.entitiy.Phone
import com.example.funbox.utils.CSV_FILE_NAME

class CsvPhoneStorage() : PhoneStorage {

    override fun getAll(context: Context): List<Phone> {
        val phones = mutableListOf<Phone>()
        context.assets.open(CSV_FILE_NAME).bufferedReader().useLines { it ->
            var values: List<String>
            it.forEach {
                values = it.split(", ")
                phones.add(
                    Phone(
                        title = values[0].trim('\"'),
                        price = values[1].trim('\"'),
                        count = values[2].trim('\"')
                    )
                )
            }
        }
        return phones
    }

    override fun saveAll(context: Context, phones: List<Phone>) {

    }
}