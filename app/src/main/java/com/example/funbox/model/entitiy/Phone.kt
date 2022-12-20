package com.example.funbox.model.entitiy

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.util.UUID

@Parcelize
data class Phone(
    val id: String = UUID.randomUUID().toString(),
    var title: String,
    var price: String,
    var count: String
) : Parcelable
