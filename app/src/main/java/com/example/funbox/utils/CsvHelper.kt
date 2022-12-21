package com.example.funbox.utils

import com.example.funbox.app.FunBoxApp
import com.example.funbox.model.entitiy.Phone

object CsvHelper {

    fun readFromCsv(): List<Phone> {
        val phones = mutableListOf<Phone>()
        FunBoxApp.context.assets.open(CSV_FILE_NAME).bufferedReader().useLines { it ->
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
}