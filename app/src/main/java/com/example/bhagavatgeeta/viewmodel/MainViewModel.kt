package com.example.bhagavatgeeta.viewmodel

import androidx.lifecycle.ViewModel
import com.example.bhagavatgeeta.model.Verse
import com.example.bhagavatgeeta.model.VerseItem
import com.example.bhagavatgeeta.model.chaptersItem
import com.example.bhagavatgeeta.repositroy.AppRepository

class MainViewModel :ViewModel() {

    val appRepository = AppRepository()




    fun getAllChapter() : kotlinx.coroutines.flow.Flow<List<chaptersItem>> = appRepository.getAllChapter()

    fun getVerse(chapterNumber :Int) : kotlinx.coroutines.flow.Flow<List<VerseItem>>{
        return  appRepository.getVerse(chapterNumber)
    }




    fun getVerseData(chapterNum: Int,verseNum :Int) :kotlinx.coroutines.flow.Flow<Verse>{
        return appRepository.getVerseData(chapterNum,verseNum)
    }
}