package com.gutotech.resultadosloterias.repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.gutotech.resultadosloterias.api.RetrofitConfig;
import com.gutotech.resultadosloterias.model.Loteria;
import com.gutotech.resultadosloterias.model.Resultado;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ResultadoRepository {
    private static ResultadoRepository instance;

    private RetrofitConfig retrofitConfig;

    private List<Resultado> resultadosList;

    private ResultadoRepository() {
        retrofitConfig = new RetrofitConfig();
    }

    public static synchronized ResultadoRepository getInstance() {
        if (instance == null)
            instance = new ResultadoRepository();
        return instance;
    }

    private MutableLiveData<List<Resultado>> resultadosLiveData;
    private MutableLiveData<Boolean> isLoading;

    public void getTodosResultados(MutableLiveData<List<Resultado>> resultadosLiveData, MutableLiveData<Boolean> isLoading) {
        this.resultadosLiveData = resultadosLiveData;
        this.isLoading = isLoading;

        resultadosList = resultadosLiveData.getValue();

        Call<Resultado> megasena = retrofitConfig.getResultadosLoteriasService().getResultado(Loteria.MEGA_SENA.id, "");
        Call<Resultado> quina = retrofitConfig.getResultadosLoteriasService().getResultado(Loteria.QUINA.id, "");
        Call<Resultado> lotofacil = retrofitConfig.getResultadosLoteriasService().getResultado(Loteria.LOTOFACIL.id, "");
        Call<Resultado> lotomania = retrofitConfig.getResultadosLoteriasService().getResultado(Loteria.LOTOMANIA.id, "");
        Call<Resultado> duplasena = retrofitConfig.getResultadosLoteriasService().getResultado(Loteria.DUPLA_SENA.id, "");
        Call<Resultado> timemania = retrofitConfig.getResultadosLoteriasService().getResultado(Loteria.TIMEMANIA.id, "");
        Call<Resultado> diadesorte = retrofitConfig.getResultadosLoteriasService().getResultado(Loteria.DIA_DE_SORTE.id, "");
        Call<Resultado> federal = retrofitConfig.getResultadosLoteriasService().getResultado(Loteria.FEDERAL.id, "");

        megasena.enqueue(new Callback<Resultado>() {
            @Override
            public void onResponse(Call<Resultado> call, Response<Resultado> response) {
                if (response.isSuccessful()) {
                    Resultado resultado = response.body();
                    resultado.loteria = Loteria.MEGA_SENA;
                    atualizarAdapter(resultado);
                }
            }

            @Override
            public void onFailure(Call<Resultado> call, Throwable t) {
            }
        });

        quina.enqueue(new Callback<Resultado>() {
            @Override
            public void onResponse(Call<Resultado> call, Response<Resultado> response) {
                if (response.isSuccessful()) {
                    Resultado resultado = response.body();
                    resultado.loteria = Loteria.QUINA;
                    atualizarAdapter(resultado);
                }
            }

            @Override
            public void onFailure(Call<Resultado> call, Throwable t) {
            }
        });

        lotofacil.enqueue(new Callback<Resultado>() {
            @Override
            public void onResponse(Call<Resultado> call, Response<Resultado> response) {
                if (response.isSuccessful()) {
                    Resultado resultado = response.body();
                    resultado.loteria = Loteria.LOTOFACIL;
                    atualizarAdapter(resultado);
                }
            }

            @Override
            public void onFailure(Call<Resultado> call, Throwable t) {
            }
        });


        lotomania.enqueue(new Callback<Resultado>() {
            @Override
            public void onResponse(Call<Resultado> call, Response<Resultado> response) {
                if (response.isSuccessful()) {
                    Resultado resultado = response.body();
                    resultado.loteria = Loteria.LOTOMANIA;
                    atualizarAdapter(resultado);
                }
            }

            @Override
            public void onFailure(Call<Resultado> call, Throwable t) {
            }
        });

        duplasena.enqueue(new Callback<Resultado>() {
            @Override
            public void onResponse(Call<Resultado> call, Response<Resultado> response) {
                if (response.isSuccessful()) {
                    Resultado resultado = response.body();
                    resultado.loteria = Loteria.DUPLA_SENA;
                    atualizarAdapter(resultado);
                }
            }

            @Override
            public void onFailure(Call<Resultado> call, Throwable t) {
            }
        });

        timemania.enqueue(new Callback<Resultado>() {
            @Override
            public void onResponse(Call<Resultado> call, Response<Resultado> response) {
                if (response.isSuccessful()) {
                    Resultado resultado = response.body();
                    resultado.loteria = Loteria.TIMEMANIA;
                    atualizarAdapter(resultado);
                }
            }

            @Override
            public void onFailure(Call<Resultado> call, Throwable t) {
            }
        });

        diadesorte.enqueue(new Callback<Resultado>() {
            @Override
            public void onResponse(Call<Resultado> call, Response<Resultado> response) {
                if (response.isSuccessful()) {
                    Resultado resultado = response.body();
                    resultado.loteria = Loteria.DIA_DE_SORTE;
                    atualizarAdapter(resultado);
                }
            }

            @Override
            public void onFailure(Call<Resultado> call, Throwable t) {
            }
        });

        federal.enqueue(new Callback<Resultado>() {
            @Override
            public void onResponse(Call<Resultado> call, Response<Resultado> response) {
                if (response.isSuccessful()) {
                    Resultado resultado = response.body();
                    resultado.loteria = Loteria.FEDERAL;
                    atualizarAdapter(resultado);
                }
            }

            @Override
            public void onFailure(Call<Resultado> call, Throwable t) {
            }
        });
    }

    private int count;

    private void atualizarAdapter(Resultado resultado) {
        count++;

        resultadosList.add(resultado);

        if (count == 8) {
            count = 0;

            Collections.sort(resultadosList, new Comparator<Resultado>() {
                @Override
                public int compare(Resultado resultado1, Resultado resultado2) {
                    if (resultado1.loteria.ordem == resultado2.loteria.ordem)
                        return 0;
                    else
                        return resultado1.loteria.ordem > resultado2.loteria.ordem ? 1 : -1;
                }
            });
            resultadosLiveData.postValue(resultadosList);
            isLoading.postValue(false);
        }
    }

    public void getResultado(final Loteria loteria, String concurso) {
        Call<Resultado> resultado = retrofitConfig.getResultadosLoteriasService().getResultado(loteria.id, concurso);
        resultado.enqueue(new Callback<Resultado>() {
            @Override
            public void onResponse(Call<Resultado> call, Response<Resultado> response) {
                if (response.isSuccessful()) {
                    Resultado resultado = response.body();
                    if (resultado != null && resultado.concurso != null) {
                        resultado.loteria = loteria;
                        resultadosList.remove(loteria.ordem);
                        resultadosList.add(resultado.loteria.ordem, resultado);
                    }
                }
                resultadosLiveData.postValue(resultadosList);
            }

            @Override
            public void onFailure(Call<Resultado> call, Throwable t) {
            }
        });
    }

    public interface ResultadoListener {
        void onResultadoListener(MutableLiveData<Resultado> resultado);

        void onResultadosListener(LiveData<List<Resultado>> resultados);
    }
}
