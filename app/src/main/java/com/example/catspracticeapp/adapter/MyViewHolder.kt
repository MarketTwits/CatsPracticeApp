package com.example.catspracticeapp.adapter

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.catspracticeapp.R

class MyViewHolder (itemView : View) : RecyclerView.ViewHolder(itemView){
    val tvId : TextView = itemView.findViewById(R.id.tvIDCat)
    val tvName : TextView = itemView.findViewById(R.id.tvCatsName)
    val tvDescription : TextView = itemView.findViewById(R.id.tvDescriptionCat)

}