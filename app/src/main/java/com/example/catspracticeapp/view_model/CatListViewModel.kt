package com.example.catspracticeapp.view_model

import android.app.Application
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewModelScope
import com.example.catspracticeapp.db.AppDatabase
import com.example.catspracticeapp.db.CatEntity
import com.example.catspracticeapp.db.CatRepository
import com.example.retrofittestapp.retrofit.RetrofitBuilder
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.Exception

class CatListViewModel(application: Application) : AndroidViewModel(application) {

    val catList : LiveData<List<CatEntity>>
    private val repository : CatRepository
    private val retrofitBuilder = RetrofitBuilder

    private val coroutineScope = CoroutineScope(Dispatchers.Main)

    init {
        val catDao = AppDatabase.getInstance(application).catListDao()
        repository = CatRepository(catDao)
        catList = repository.getAllCat()
    }
    private fun addCat(catEntity: CatEntity){
            viewModelScope.launch(Dispatchers.IO) {
                repository.addCat(catEntity)
            }
    }
   fun deleteCatById(catEntity: CatEntity){
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteCatById(catEntity)
        }
    }
    fun createCat(name : String, description: String) {
        coroutineScope.launch{
            val cat = CatEntity(
                id = 0,
                name = name,
                description = description,
                image = getCatImage()
            )
            addCat(cat)
        }
    }
    private suspend fun getCatImage() : String?{
        val catsImage = retrofitBuilder.catsApi.getRandomCat()
        return retrofitBuilder.BASE_URL + catsImage.body()?.url
    }

}