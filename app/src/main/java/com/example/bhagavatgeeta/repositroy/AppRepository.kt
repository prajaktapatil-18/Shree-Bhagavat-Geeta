package com.example.bhagavatgeeta.repositroy

import android.util.Log
import com.example.bhagavatgeeta.datasource.api.ApiUtilities
import com.example.bhagavatgeeta.model.Verse
import com.example.bhagavatgeeta.model.VerseItem

import com.example.bhagavatgeeta.model.chaptersItem

import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.callbackFlow
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.concurrent.Callable
import java.util.concurrent.Flow

class AppRepository {

    fun getAllChapter(): kotlinx.coroutines.flow.Flow<List<chaptersItem>> = callbackFlow {


        val callback = object : Callable<List<chaptersItem>>, Callback<List<chaptersItem>> {

            override fun onResponse(
                call: Call<List<chaptersItem>>,
                response: Response<List<chaptersItem>>


            ) {

                if (response.isSuccessful && response.body() != null) {
                    trySend(response.body()!!)
                    close()
                }

            }

            override fun onFailure(call: Call<List<chaptersItem>>, t: Throwable) {
                close(t)

            }

            override fun call(): List<chaptersItem> {
                TODO("Not yet implemented")
            }


        }

        ApiUtilities.api.getAllChapter().enqueue(callback)

        awaitClose { }
    }


    fun getVerse(chapterNumber: Int): kotlinx.coroutines.flow.Flow<List<VerseItem>> = callbackFlow {

        val callback = object : Callable<List<VerseItem>>, Callback<List<VerseItem>> {


            override fun call(): List<VerseItem> {
                TODO("Not yet implemented")
            }

            override fun onResponse(
                call: Call<List<VerseItem>>,
                response: Response<List<VerseItem>>
            ) {
                if (response.isSuccessful && response.body() != null) {
                    Log.d("AppRepository", "Verses fetched: ${response.body()?.size}")
                    trySend(response.body()!!)
                    close()
                } else {
                    Log.e("AppRepository", "Response failed: ${response.code()}")
                }
            }


            override fun onFailure(call: Call<List<VerseItem>>, t: Throwable) {
                close(t)


            }

        }
        ApiUtilities.api.getVerses(chapterNumber).enqueue(callback)
        awaitClose { }

    }

    fun getVerseData(chapterNumber: Int,verseNumber :Int) :kotlinx.coroutines.flow.Flow<Verse> = callbackFlow {

        val callback = object : Callback<Verse>, Callable<Verse>{


        override fun onResponse(call: Call<Verse>, response: Response<Verse>) {


             if(response.isSuccessful && response.body() != null){
                 trySend(response.body()!!)
                 close()


             }
        }

        override fun onFailure(p0: Call<Verse>, t: Throwable) {
           close(t)
        }

        override fun call(): Verse {
            TODO("Not yet implemented")
        }


    }
        ApiUtilities.api.getVerseData(chapterNumber,verseNumber).enqueue(callback)
        awaitClose {  }
    }

}
