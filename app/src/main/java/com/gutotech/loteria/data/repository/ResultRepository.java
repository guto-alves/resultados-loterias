package com.gutotech.loteria.data.repository;

import com.gutotech.loteria.data.model.Result;
import com.gutotech.loteria.data.retrofit.RetrofitConfig;
import com.gutotech.loteria.data.model.Lottery;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ResultRepository {
    private static ResultRepository sInstance = new ResultRepository();

    private RetrofitConfig retrofitConfig = new RetrofitConfig();

    public static ResultRepository getInstance() {
        return sInstance;
    }

    public void getResult(final Lottery lottery, String concurso,
                          final ResultCallback<Result> callback) {
        Call<Result> result = retrofitConfig.getResult().getResult(lottery.id, concurso);

        result.enqueue(new Callback<Result>() {
            @Override
            public void onResponse(@NotNull Call<Result> call, @NotNull Response<Result> response) {
                if (response.isSuccessful()) {
                    Result result = response.body();
                    result.lottery = lottery;
                    callback.call(result);
                }
            }

            @Override
            public void onFailure(@NotNull Call<Result> call, @NotNull Throwable t) {
            }
        });
    }

    public void getCurrentResults(ResultCallback<List<Result>> callback) {
        List<Result> resultList = new ArrayList<>();

        for (Lottery lottery : Lottery.values()) {
            getResult(lottery, "", result -> {
                resultList.add(result);
                sortByLottery(resultList);
                callback.call(resultList);
            });
        }
    }

    public void getHistorico(Lottery lottery, int start, int end, int ganhadores, ResultCallback<List<Result>> callback) {
        List<Result> results = new ArrayList<>();

        for (int concurso = start; concurso <= end; concurso++) {
            getResult(lottery, String.valueOf(concurso), result -> {
                int totalGanhadores = 0;

                try {
                    if (result.lottery == Lottery.LOTOFACIL) {
                        totalGanhadores = Integer.parseInt(result.concurso.premiacao.acertos_15.ganhadores);
                    } else if (result.lottery == Lottery.MEGA_SENA) {
                        totalGanhadores = Integer.parseInt(result.concurso.premiacao.sena.ganhadores);
                    } else if (result.lottery == Lottery.QUINA) {
                        totalGanhadores = Integer.parseInt(result.concurso.premiacao.quina.ganhadores);
                    } else if (result.lottery == Lottery.LOTOMANIA) {
                        totalGanhadores = Integer.parseInt(result.concurso.premiacao.acertos_20.ganhadores);
                    }
                } catch (NullPointerException ignored) {
                }

                if (totalGanhadores >= ganhadores) {
                    results.add(result);
                    sortByConcurso(results);
                    callback.call(results);
                }
            });
        }
    }

    private void sortByLottery(List<Result> collection) {
        Collections.sort(collection, (result1, result2) -> {
            if (result1.lottery.ordinal() == result2.lottery.ordinal()) {
                return 0;
            }

            return result1.lottery.ordinal() > result2.lottery.ordinal() ? 1 : -1;
        });
    }

    private void sortByConcurso(List<Result> results) {
        Collections.sort(results, (result1, result2) -> {
            int concurso1 = Integer.parseInt(result1.concurso.numero);
            int concurso2 = Integer.parseInt(result2.concurso.numero);

            if (concurso1 == concurso2) {
                return 0;
            }

            return concurso1 > concurso2 ? 1 : -1;
        });
    }

    public interface ResultCallback<T> {
        void call(T data);
    }
}
