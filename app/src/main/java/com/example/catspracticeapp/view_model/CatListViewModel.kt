package com.example.catspracticeapp.view_model

import android.app.Application
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.catspracticeapp.db.AppDatabase
import com.example.catspracticeapp.db.CatEntity
import com.example.catspracticeapp.db.CatRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CatListViewModel(application: Application) : AndroidViewModel(application) {

    val catList : LiveData<List<CatEntity>>
    private val repository : CatRepository

    init {
        val catDao = AppDatabase.getInstance(application).catListDao()
        repository = CatRepository(catDao)
        catList = repository.getAllCat()
    }
    fun addCat(catEntity: CatEntity){
            viewModelScope.launch(Dispatchers.IO) {
                repository.addCat(catEntity)
            }
    }
   fun deleteCatById(catEntity: CatEntity){
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteCatById(catEntity)
        }
    }

}