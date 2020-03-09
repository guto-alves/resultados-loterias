package com.gutotech.loteria.data.retrofit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitConfig {

    private val retrofit = Retrofit.Builder()
        .baseUrl("https://apiloterias.com.br/api0/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun getResult() = retrofit.create(LotteryService::class.java)

}