package com.example.weatherappexample.Data.data

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query


interface ApiInterface {
    @GET("/data/2.5/weather")
    suspend fun getCurrentWeather(

        @Query("q") city : String,
        @Query("units") units : String,
        @Query("appid") apiKey : String,
    ):currentWeather

    @GET("/get_memes")
    suspend fun getMemes() : Response<AllMemsData>
}