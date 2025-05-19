package com.example.bhagavatgeeta.datasource.api

import android.telecom.Call
import com.example.bhagavatgeeta.model.Translation
import com.example.bhagavatgeeta.model.Verse
import com.example.bhagavatgeeta.model.VerseItem

import com.example.bhagavatgeeta.model.chaptersItem

import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path

interface ApiInterface {


    @GET("/v2/chapters/")
    fun getAllChapter(): retrofit2.Call<List<chaptersItem>>


    @GET("/v2/chapters/{chapterNumber}/verses/")
    fun getVerses(@Path("chapterNumber") chapterNumber: Int): retrofit2.Call<List<VerseItem>>


    @GET("v2/chapters/{chapterNum}/verses/{verseNum}/")
    fun getVerseData(@Path("chapterNum") chapterNumber: Int,
        @Path("verseNum") verseNumber: Int

    ): retrofit2.Call<Verse>

}

