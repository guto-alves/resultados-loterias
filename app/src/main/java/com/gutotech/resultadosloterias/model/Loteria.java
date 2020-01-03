package com.gutotech.resultadosloterias.model;

public enum Loteria {
    MEGA_SENA("Mega Sena", "megasena", 1),
    QUINA("Quina", "quina", 2),
    LOTOFACIL("Lotofácal", "lotofacil", 3),
    LOTOMANIA("Lotomania", "lotomania", 4),
    DUPLA_SENA("Dupla Sena", "duplasena", 5),
    TIMEMANIA("Timemania", "timemania", 6),
    DIA_DE_SORTE("Dia de Sorte", "diadesorte", 7),
    FEDERAL("Federal", "federal", 8);

    public final String nome;
    public final int ordem; // usado para classificar as loteria nas listagens
    public final String id;

    Loteria(String nome, String id, int ordem) {
        this.nome = nome;
        this.id = id;
        this.ordem = ordem;
    }
}
