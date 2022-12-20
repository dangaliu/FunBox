package com.example.funbox.model.entitiy

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class StoreItem(
    var title: String,
    var price: String,
    var count: String
): Parcelable
