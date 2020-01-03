package com.gutotech.resultadosloterias.ui.home;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.gutotech.resultadosloterias.R;
import com.gutotech.resultadosloterias.adapter.LoteriasAdapter;
import com.gutotech.resultadosloterias.api.RetrofitConfig;
import com.gutotech.resultadosloterias.model.Resultado;
import com.gutotech.resultadosloterias.model.Loteria;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment {
    private RecyclerView loteriasRecyclerView;
    private LoteriasAdapter loteriasAdapter;
    private List<Resultado> resultadoList;

    private ProgressBar progressBar;

    private RetrofitConfig retrofitConfig;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_home, container, false);

        retrofitConfig = new RetrofitConfig();

        resultadoList = new ArrayList<>();

        progressBar = root.findViewById(R.id.progressBar);

        loteriasRecyclerView = root.findViewById(R.id.loteriasRecyclerView);
        loteriasRecyclerView.setVisibility(View.GONE);
        loteriasRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        loteriasRecyclerView.setHasFixedSize(true);
        loteriasAdapter = new LoteriasAdapter(getActivity(), resultadoList, loteriaClickListener);
        loteriasRecyclerView.setAdapter(loteriasAdapter);

        return root;
    }

    @Override
    public void onStart() {
        super.onStart();
        getResultadosTodasLoterias();
    }

    private void getResultadosTodasLoterias() {
        resultadoList.clear();

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

        resultadoList.add(resultado);

        if (count == 8) {
            progressBar.setVisibility(View.GONE);
            loteriasRecyclerView.setVisibility(View.VISIBLE);
            count = 0;

            Collections.sort(resultadoList, new Comparator<Resultado>() {
                @Override
                public int compare(Resultado resultado1, Resultado resultado2) {
                    if (resultado1.loteria.ordem == resultado2.loteria.ordem)
                        return 0;
                    else
                        return resultado1.loteria.ordem > resultado2.loteria.ordem ? 1 : -1;
                }
            });
        }

        loteriasAdapter.notifyDataSetChanged();
    }

    private final LoteriasAdapter.LoteriaClickListener loteriaClickListener = new LoteriasAdapter.LoteriaClickListener() {
        @Override
        public void onClick(View view, Resultado resultado) {
            Log.i("tag teste", resultado.loteria.nome);
        }
    };
}