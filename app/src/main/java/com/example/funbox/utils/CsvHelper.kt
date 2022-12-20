package com.example.funbox.utils

import android.content.Context
import com.example.funbox.model.entitiy.StoreItem

object CsvHelper {

    private var fileName = "data.csv"

    fun readFromCsv(context: Context): List<StoreItem> {
        val storeItems = mutableListOf<StoreItem>()
        context.assets.open(fileName).bufferedReader().useLines { it ->
            var values: List<String>
            it.forEach {
                values = it.split(", ")
                storeItems.add(
                    StoreItem(
                        title = values[0].trim('\"'),
                        price = values[1].trim('\"'),
                        count = values[2].trim('\"')
                    )
                )
            }
        }
        return storeItems
    }
}