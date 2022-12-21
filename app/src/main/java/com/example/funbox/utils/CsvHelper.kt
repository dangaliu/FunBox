package com.example.funbox.utils

import com.example.funbox.app.FunBoxApp
import com.example.funbox.model.entitiy.Phone
import java.io.File
import java.io.FileOutputStream

object CsvHelper {

    fun readFromCsv(): List<Phone> {
        val phones = mutableListOf<Phone>()
        val file = File(CACHED_DATA_PATH)
        if (file.exists()) {
            file.bufferedReader().useLines {
                readFromFile(it, phones, true)
            }
        } else {
            FunBoxApp.context.assets.open(CSV_FILE_NAME).bufferedReader().useLines {
                readFromFile(it, phones)
            }
        }

        return phones
    }

    private fun readFromFile(
        it: Sequence<String>,
        phones: MutableList<Phone>,
        exist: Boolean = false
    ) {
        var values: List<String>
        it.forEach { str ->
            values = str.split(", ")
            if (!exist) saveToCache(values.joinToString(separator = ", "))
            phones.add(
                Phone(
                    title = values[0].trim('\"'),
                    price = values[1].trim('\"'),
                    count = values[2].trim('\"')
                )
            )
        }
    }

    private fun saveToCache(data: String) {
        FileOutputStream(CACHED_DATA_PATH, true).bufferedWriter().use {
            it.write(data)
            it.newLine()
        }
    }


    fun writeToCsv(list: List<Phone>) {
        val file = File(CACHED_DATA_PATH)
        file.delete()
        list.forEach {
            val phoneStr = "\"${it.title}\", \"${it.price}\", \"${it.count}\""
            saveToCache(phoneStr)
        }
    }
}