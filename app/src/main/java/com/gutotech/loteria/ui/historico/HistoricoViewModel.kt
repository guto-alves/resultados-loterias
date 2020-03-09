package com.gutotech.loteria.ui.historico

import androidx.databinding.ObservableField
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.gutotech.loteria.data.model.Lottery
import com.gutotech.loteria.data.model.Result
import com.gutotech.loteria.data.repository.ResultRepository

class HistoricoViewModel : ViewModel() {
    val ganhadores = ObservableField<String>("")

    private var loterias = Lottery.values()
    private var _lotterySelected = Lottery.MEGA_SENA

    private var mStartSelected = 1
    private var mEndSelected = 0

    private val mResultRepository = ResultRepository.getInstance()

    private val mHistorico = MutableLiveData<List<Result>>()

    val historico: LiveData<List<Result>> = mHistorico

    private val _concursos = MutableLiveData<List<String>>()
    val concursos = _concursos

    fun onLotterySelected(position: Int) {
        _lotterySelected = loterias.get(position)
        mResultRepository.getResult(_lotterySelected, "", {
            if (it != null) {
                val TOTAL_CONCURSOS = it.concurso.numero.toInt()

                val concursosList = ArrayList<String>()

                for (concurso in 1..TOTAL_CONCURSOS) {
                    concursosList.add(concurso.toString())
                }

                _concursos.value = concursosList
            }
        })
    }

    fun onStartSelected(position: Int) {
        mStartSelected = position + 1

        if (mStartSelected > mEndSelected) {
            swap()
        }
    }

    fun onEndSelected(position: Int) {
        mEndSelected = position + 1

        if (mStartSelected > mEndSelected) {
            swap()
        }
    }

    fun swap() {
        val buffer = mEndSelected
        mEndSelected = mStartSelected
        mStartSelected = buffer
    }

    fun onFiltrarClick() {
        var minGanhadores: Int?

        try {
            minGanhadores = ganhadores.get()?.toInt()
        } catch (e: NumberFormatException) {
            minGanhadores = 0
        }

        if (minGanhadores != null) {
            mResultRepository.getHistorico(_lotterySelected,
                mStartSelected,
                mEndSelected,
                minGanhadores,
                {
                    mHistorico.value = it
                })
        }
    }
}