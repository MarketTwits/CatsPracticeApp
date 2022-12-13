package com.example.catspracticeapp.db

import androidx.lifecycle.LiveData
import androidx.room.*


@Dao
interface CatDao {
    @Query("SELECT * FROM cat_items")
    fun getCatList() : LiveData<List<CatEntity>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addCatItem(catEntity: CatEntity)

    @Delete
    suspend fun deleteCatItem(catItem : CatEntity)

    @Query("DELETE FROM cat_items WHERE id = :catItemId")
    suspend fun deleteCatById(catItemId : Int)

    @Query("SELECT * FROM cat_items WHERE id=:catItemId LIMIT 1")
    fun getCatItem(catItemId : Int) : CatEntity
}