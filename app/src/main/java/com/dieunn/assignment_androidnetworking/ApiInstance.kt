package com.dieunn.assignment_androidnetworking

import com.google.gson.Gson
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory

object ApiInstance {
    val api : AppAPI by lazy {
        Retrofit.Builder()
            .baseUrl("https://apiandroidnetworkingdieunn.herokuapp.com/")
            .addConverterFactory(ScalarsConverterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(AppAPI::class.java)
    }
}