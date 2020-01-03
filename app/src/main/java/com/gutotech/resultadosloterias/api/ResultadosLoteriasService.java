package com.gutotech.resultadosloterias.api;

import com.gutotech.resultadosloterias.model.Resultado;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ResultadosLoteriasService {
    String baseUrl = "https://apiloterias.com.br/api/";
    String token = "qzF7NvLbjcXuoSW";

    @GET("json/?token=" + token)
    Call<Resultado> getResultado(@Query("loteria") String loteria, @Query("concurso") String concurso);
}
