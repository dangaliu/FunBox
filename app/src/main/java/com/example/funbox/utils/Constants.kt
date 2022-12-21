package com.example.funbox.utils

import com.example.funbox.app.FunBoxApp

const val STORE_ITEM_KEY = "STORE_ITEM_KEY"
const val CSV_FILE_NAME = "data.csv"
var CACHED_DATA_PATH = "${FunBoxApp.context.filesDir}/${CSV_FILE_NAME}"