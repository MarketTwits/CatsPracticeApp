package com.example.catspracticeapp.adapter

import androidx.recyclerview.widget.DiffUtil
import com.example.catspracticeapp.db.CatEntity

class CatItemDiffCallback : DiffUtil.ItemCallback<CatEntity>()  {

    override fun areItemsTheSame(oldItem: CatEntity, newItem: CatEntity): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: CatEntity, newItem: CatEntity): Boolean {
        return oldItem == newItem
    }
}