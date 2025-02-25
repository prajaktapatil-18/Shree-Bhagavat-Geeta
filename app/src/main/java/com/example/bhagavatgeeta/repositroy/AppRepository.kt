package com.example.bhagavatgeeta.repositroy

import com.example.bhagavatgeeta.datasource.api.ApiUtilities
import com.example.bhagavatgeeta.model.chaptersItem
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.callbackFlow
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.concurrent.Callable

class AppRepository {

    fun getAllChapter() : kotlinx.coroutines.flow.Flow<List<chaptersItem>>  = callbackFlow {


        val callback = object : Callable<List<chaptersItem>>, Callback<List<chaptersItem>> {

            override fun onResponse(
                call: Call<List<chaptersItem>>,
                response : Response<List<chaptersItem>>


            ){

                if (response.isSuccessful&& response.body()  !=null){
                    trySend(response.body()!!)
                    close()
                }

            }

            override fun onFailure(call : Call<List<chaptersItem>>, t :Throwable){
                close(t)

            }

            override fun call(): List<chaptersItem> {
                TODO("Not yet implemented")
            }


        }

        ApiUtilities.api.getAllChapter().enqueue(callback)

        awaitClose {  }
    }


}