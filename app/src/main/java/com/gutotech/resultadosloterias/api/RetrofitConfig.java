package com.gutotech.resultadosloterias.api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitConfig {
    private Retrofit retrofit;

    public RetrofitConfig() {
        retrofit = new Retrofit.Builder()
                .baseUrl(ResultadosLoteriasService.baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public ResultadosLoteriasService getResultadosLoteriasService() {
        return retrofit.create(ResultadosLoteriasService.class);
    }
}
