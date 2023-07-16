package com.example.meta.data.event

import com.example.meta.data.model.ItemModel

interface ItemClickListener {
    fun itemClick(model: ItemModel)
}