package com.example.catspracticeapp.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query


@Dao
interface CatDao {

    @Query("SELECT * FROM cat_items")
    fun getCat() : LiveData<List<CatEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    //перезапись объекта с одинаковым id
    fun addCatItem(catEntity: CatEntity)

    @Query("DELETE FROM cat_items WHERE id = :catItemId")
    fun deleteCatItem(catItemId : Int)

    @Query("SELECT * FROM cat_items WHERE id=:catItemId LIMIT 1")
    fun getCatItem(catItemId : Int) : CatEntity
}