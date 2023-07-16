package com.example.meta.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.example.meta.data.event.ItemClickListener
import com.example.meta.data.model.ItemModel
import com.example.meta.databinding.ItemCardBinding

class ItemCardAdapter(private val model: List<ItemModel>, private val listener: ItemClickListener) : Adapter<ItemCardViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemCardViewHolder {
        return ItemCardViewHolder(
            ItemCardBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ItemCardViewHolder, position: Int) {
       holder.bindViews(model[position],listener)
    }

    override fun getItemCount(): Int {
       return model.size
    }
}