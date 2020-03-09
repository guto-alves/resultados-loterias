package com.gutotech.loteria.data.retrofit

import com.gutotech.loteria.data.model.Result
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface LotteryService {

    @GET("json.php?token=qzF7NvLbjcXuoSW")
    fun getResult(@Query("loteria") loteria: String, @Query("concurso") concurso: String): Call<Result>

}