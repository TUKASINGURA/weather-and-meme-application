package com.example.weatherappexample.Utils

import com.example.weatherappexample.Data.data.ApiInterface
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

//
//object RetrofitInstance {
//
//    val api: ApiInterface by lazy{
//
//        Retrofit.Builder()
//            .baseUrl(util.Base)
//            .addConverterFactory(GsonConverterFactory.create())
//            .build()
//            .create(ApiInterface::class.java)
//    }
//
////    val  ApiInterface= Retrofit.Builder()
////        .baseUrl("https://api.openweathermap.org/data/2.5/") // Replace with the API base URL
////        .addConverterFactory(GsonConverterFactory.create())
////        .build().create(ApiInterface::class.java)
//}

class RetrofitInstance {

    fun createRetrofit(): ApiInterface {
        val retrofit = Retrofit.Builder().baseUrl("https://api.openweathermap.org")
            .addConverterFactory(GsonConverterFactory.create()).build()

        val service = retrofit.create(ApiInterface::class.java)
        return service
    }

    /**function for implementation of the memes*/
    fun createMemeRetrofit():ApiInterface{
         val retrofitMeme=   Retrofit.Builder()
             .baseUrl( "https://api.imgflip.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
               val serviceMeme= retrofitMeme.create(ApiInterface::class.java)
        return serviceMeme
    }
}