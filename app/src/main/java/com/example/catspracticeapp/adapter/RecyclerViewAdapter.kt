package com.example.catspracticeapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.catspracticeapp.R
import com.example.catspracticeapp.db.CatEntity
import com.example.retrofittestapp.retrofit.RetrofitBuilder
import kotlin.coroutines.coroutineContext


class RecyclerViewAdapter(private val context: Context)
    : ListAdapter<CatEntity, RecyclerViewAdapter.MyViewHolder> (CatItemDiffCallback()){
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
        Glide.with(context)
            .load(catList[position].image)
            .centerCrop()
            .into(holder.image)
        holder.itemView.setOnClickListener {
            onCatItemClickListener?.invoke(item)
        }
    }
    fun setData(catEntity: List<CatEntity>){
        this.catList = catEntity
    }
    class MyViewHolder (itemView : View) : RecyclerView.ViewHolder(itemView){
        val tvId : TextView = itemView.findViewById(R.id.tvIDCat)
        val tvName : TextView = itemView.findViewById(R.id.tvCatsName)
        val tvDescription : TextView = itemView.findViewById(R.id.tvDescriptionCat)
        val image : ImageView = itemView.findViewById(R.id.imageView)
    }
}