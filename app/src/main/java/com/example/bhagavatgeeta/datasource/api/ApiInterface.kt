package com.example.bhagavatgeeta.datasource.api

import com.example.bhagavatgeeta.model.chaptersItem
import retrofit2.http.GET
import retrofit2.http.Headers

interface ApiInterface {



    @Headers(

        "Accept: application/json",
        "x-rapidapi-key: 08b60f5225msh943106be58bb578p15fc60jsn8b443df4a922",
        "x-rapidapi-host: bhagavad-gita3.p.rapidapi.com"

    )

    @GET("/v2/chapters/")
    fun getAllChapter(): retrofit2.Call<List<chaptersItem>>


}