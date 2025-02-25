package com.example.bhagavatgeeta.viewmodel

import androidx.lifecycle.ViewModel
import com.example.bhagavatgeeta.model.chaptersItem
import com.example.bhagavatgeeta.repositroy.AppRepository

class MainViewModel :ViewModel() {

    val appRepository = AppRepository()




    fun getAllChapter() : kotlinx.coroutines.flow.Flow<List<chaptersItem>> = appRepository.getAllChapter()

}