package com.gutotech.loteria.data.model;

import androidx.annotation.ColorRes;

import com.gutotech.loteria.R;

public enum Lottery {
    MEGA_SENA("megasena", "Mega Sena", R.color.colorMegaSena),
    QUINA("quina", "Quina", R.color.colorQuina),
    LOTOFACIL("lotofacil", "Lotofácil", R.color.colorLotofacil),
    LOTOMANIA("lotomania", "Lotomania", R.color.colorLotomania),
    DUPLA_SENA("duplasena", "Dupla Sena", R.color.colorDuplaSena),
    TIMEMANIA("timemania", "Timemania", R.color.colorTimemania),
    DIA_DE_SORTE("diadesorte", "Dia de Sorte", R.color.colorDiaDeSorte),
    FEDERAL("federal", "Federal", R.color.colorFederal);

    public final String id;

    public final String name;

    @ColorRes
    public final int color;

    Lottery(String id, String name, @ColorRes int color) {
        this.name = name;
        this.id = id;
        this.color = color;
    }
}
