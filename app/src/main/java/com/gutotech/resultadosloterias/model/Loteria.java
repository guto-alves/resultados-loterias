package com.gutotech.resultadosloterias.model;

import com.gutotech.resultadosloterias.R;

public enum Loteria {
    MEGA_SENA("Mega Sena", "megasena", 0, R.color.colorMegaSena),
    QUINA("Quina", "quina", 1, R.color.colorQuina),
    LOTOFACIL("Lotofácil", "lotofacil", 2, R.color.colorLotofacil),
    LOTOMANIA("Lotomania", "lotomania", 3, R.color.colorLotomania),
    DUPLA_SENA("Dupla Sena", "duplasena", 4, R.color.colorDuplaSena),
    TIMEMANIA("Timemania", "timemania", 5, R.color.colorTimemania),
    DIA_DE_SORTE("Dia de Sorte", "diadesorte", 6, R.color.colorDiaDeSorte),
    FEDERAL("Federal", "federal", 7, R.color.colorFederal);

    public final String nome;
    public final String id;
    public final int ordem; // usado para classificar as loteria nas listagens
    public final int color;

    Loteria(String nome, String id, int ordem, int color) {
        this.nome = nome;
        this.id = id;
        this.ordem = ordem;
        this.color = color;
    }
}
