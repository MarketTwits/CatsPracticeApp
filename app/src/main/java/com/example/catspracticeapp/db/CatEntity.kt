package com.example.catspracticeapp.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cat_items")
data class CatEntity (
        @PrimaryKey(autoGenerate = true)
        val id : Int,
        val name : String,
        val description : String,
        val image : String?
)