package com.example.funbox.app

import android.app.Application
import android.content.Context
import com.example.funbox.utils.CsvHelper

class FunBoxApp: Application() {

    companion object {
        lateinit var context: FunBoxApp
    }

    override fun onCreate() {
        super.onCreate()
        context = this
    }
}