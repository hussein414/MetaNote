package com.example.meta.ui.adapter

import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.meta.data.event.ItemClickListener
import com.example.meta.data.model.ItemModel
import com.example.meta.databinding.ItemCardBinding

class ItemCardViewHolder(private val binding: ItemCardBinding) : ViewHolder(binding.root) {
    fun bindViews(model: ItemModel, listener: ItemClickListener) {
        binding.yourName.text = model.name
       binding.image.setImageResource(model.image)
        binding.parent.setOnClickListener {
            listener.itemClick(model)
        }
    }
}