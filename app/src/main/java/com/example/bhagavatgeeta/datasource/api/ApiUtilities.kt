package com.example.bhagavatgeeta.datasource.api

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiUtilities {
    val api : ApiInterface by lazy {


val headers = mapOf<String,String>(

    "Accept" to "application/json",
    "x-rapidapi-key" to "08b60f5225msh943106be58bb578p15fc60jsn8b443df4a922",
    "x-rapidapi-host" to "bhagavad-gita3.p.rapidapi.com"
)


        val client = OkHttpClient.Builder().apply {
            addInterceptor{chain->
                 val newRequest  = chain.request().newBuilder().apply {

                     headers.forEach{key, value-> addHeader(key,value)}
                 }.build()
                chain.proceed(newRequest)

            }
        }




        Retrofit.Builder()
            .baseUrl("https://bhagavad-gita3.p.rapidapi.com")
            .addConverterFactory(GsonConverterFactory.create())
            .client(client.build())
            .build()
            .create(ApiInterface::class.java)






    }
}