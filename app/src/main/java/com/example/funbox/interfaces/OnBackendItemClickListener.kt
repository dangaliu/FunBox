package com.example.funbox.interfaces

import com.example.funbox.model.entitiy.StoreItem

interface OnBackendItemClickListener {

    fun onClick(item: StoreItem)
}