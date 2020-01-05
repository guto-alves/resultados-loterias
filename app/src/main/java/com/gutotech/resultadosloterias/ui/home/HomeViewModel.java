package com.gutotech.resultadosloterias.ui.home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.gutotech.resultadosloterias.repository.ResultadoRepository;
import com.gutotech.resultadosloterias.model.Resultado;

import java.util.ArrayList;
import java.util.List;

public class HomeViewModel extends ViewModel {
    private MutableLiveData<List<Resultado>> mResultadosList;

    private MutableLiveData<Boolean> isLoading;

    private ResultadoRepository mResultadoRepository;

    public HomeViewModel() {
        mResultadosList = new MutableLiveData<>();
        mResultadosList.setValue(new ArrayList<Resultado>());

        isLoading = new MutableLiveData<>();
        isLoading.postValue(true);

        mResultadoRepository = ResultadoRepository.getInstance();
        mResultadoRepository.getTodosResultados(mResultadosList, isLoading);
    }

    public LiveData<List<Resultado>> getResultadosList() {
        return mResultadosList;
    }

    public MutableLiveData<Boolean> getIsLoading() {
        return isLoading;
    }

    public void getResultadoAnterior(int position) {
        Resultado resultadoAtual = getResultadoAt(position);
        String concursoAnterior = String.valueOf(Integer.parseInt(resultadoAtual.concurso.numero) - 1);
        mResultadoRepository.getResultado(resultadoAtual.loteria, concursoAnterior);
    }

    public void getProximoResultado(int position) {
        Resultado resultadoAtual = getResultadoAt(position);
        String concursoAnterior = String.valueOf(Integer.parseInt(resultadoAtual.concurso.numero) + 1);
        mResultadoRepository.getResultado(resultadoAtual.loteria, concursoAnterior);
    }


    private Resultado getResultadoAt(int position) {
        return mResultadosList.getValue().get(position);
    }
}
