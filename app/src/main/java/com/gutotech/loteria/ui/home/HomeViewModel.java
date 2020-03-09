package com.gutotech.loteria.ui.home;

import android.view.View;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.gutotech.loteria.data.model.Lottery;
import com.gutotech.loteria.data.model.Result;
import com.gutotech.loteria.data.repository.ResultRepository;
import com.gutotech.loteria.ui.adapter.LotteryAdapter;

import java.util.List;

public class HomeViewModel extends ViewModel implements LotteryAdapter.LotteryClickListener {
    private ResultRepository mResultRepository;

    private MutableLiveData<List<Result>> mResultList;

    public HomeViewModel() {
        mResultList = new MutableLiveData<>();

        mResultRepository = ResultRepository.getInstance();
        mResultRepository.getCurrentResults(mResultList::postValue);
    }

    public LiveData<List<Result>> getResultList() {
        return mResultList;
    }

    public void loadPreviousResult(final int position) {
        Result currentResult = getResultAt(position);

        String previousConcurso = String.valueOf(Integer.parseInt(currentResult.concurso.numero) - 1);

        replaceResult(currentResult.lottery, previousConcurso, position);
    }

    public void loadNextResult(int position) {
        Result currentResult = getResultAt(position);

        String nextConcurso = String.valueOf(Integer.parseInt(currentResult.concurso.numero) + 1);

        replaceResult(currentResult.lottery, nextConcurso, position);
    }

    private void replaceResult(Lottery currentLottery, String anotherConcurso, final int position) {
        mResultRepository.getResult(currentLottery, anotherConcurso, result -> {
            List<Result> results = mResultList.getValue();

            if (result != null) {
                results.set(position, result);
            }

            mResultList.setValue(results);
        });
    }

    private Result getResultAt(int position) {
        return mResultList.getValue().get(position);
    }

    @Override
    public void onClick(View view, Result result) {

    }

    @Override
    public void onNextClick(int position) {
        loadNextResult(position);
    }

    @Override
    public void onPreviousClick(int position) {
        loadPreviousResult(position);
    }
}
