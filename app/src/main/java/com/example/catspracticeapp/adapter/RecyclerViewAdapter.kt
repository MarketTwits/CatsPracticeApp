package com.example.catspracticeapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.catspracticeapp.R
import com.example.catspracticeapp.db.CatEntity


class RecyclerViewAdapter
    : ListAdapter<CatEntity, MyViewHolder> (CatItemDiffCallback()){
    var onCatItemClickListener : ((CatEntity) -> Unit)? = null

    private var catList = emptyList<CatEntity>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(parent.context)
            .inflate(R.layout.cats_item_list, parent, false))
    }

    override fun getItemCount(): Int {
        return catList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        val item = catList[position]
        holder.tvId.text = item.id.toString()
        holder.tvName.text = item.name
        holder.tvDescription.text = item.description

        holder.itemView.setOnClickListener {
            onCatItemClickListener?.invoke(item)
        }
    }
    fun setData(catEntity: List<CatEntity>){
        this.catList = catEntity
    }
}