package com.example.catspracticeapp.db

import androidx.lifecycle.LiveData

class CatRepository(private val catDao: CatDao) {

    fun getAllCat() : LiveData<List<CatEntity>> = catDao.getCatList()

    suspend fun addCat(catEntity: CatEntity){
        catDao.addCatItem(catEntity)
    }

    suspend fun deleteCatById(catEntity: CatEntity){
        catDao.deleteCatById(catEntity.id)
    }

    suspend fun deleteCat(catEntity: CatEntity){
        catDao.deleteCatItem(catEntity)
    }

}