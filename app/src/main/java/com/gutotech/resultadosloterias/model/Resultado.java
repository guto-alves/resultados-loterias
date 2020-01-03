package com.gutotech.resultadosloterias.model;

import java.util.List;

public class Resultado {
    public Loteria loteria;

    public Concurso concurso;
    public ProximoConcurso proximo_concurso;

    public class Concurso {
        public String numero;
        public String data;
        public String valor_acumulado;
        public List<String> dezenas;
        public String arrecadacao_total;
        public Premiacao premiacao;

        public class Premiacao {
            // Mega
            public Acertos sena;
            public Acertos quina;
            public Acertos quadra;

            // Quina
//            public Acertos quina;
//            public Acertos quadra;
            public Acertos terno;
            public Acertos duque;

            // Lotofacil
            public Acertos acertos_15;
            public Acertos acertos_14;
            public Acertos acertos_13;
            public Acertos acertos_12;
            public Acertos acertos_11;

            // Lotomania
            public Acertos acertos_20;
            public Acertos acertos_19;
            public Acertos acertos_18;
            public Acertos acertos_17;
            public Acertos acertos_16;
            //            public Acertos acertos_15;
            public Acertos acertos_0;

            // Timemania
            public Acertos acertos_7;
            public Acertos acertos_6;
            public Acertos acertos_5;
            public Acertos acertos_4;
            public Acertos acertos_3;

            // Dupla sena
//            public Acertos sena;
//            public Acertos quina;
//            public Acertos quadra;
//            public Acertos terno;

            // Dia de Sorte
//            public Acertos acertos_7;
//            public Acertos acertos_6;
//            public Acertos acertos_5;
//            public Acertos acertos_4;
            public Acertos mes_sorte;

            public class Acertos {
                public String ganhadores;
                public String valor_pago;
            }

            // Federal
            public Premio premio_1;
            public Premio premio_2;
            public Premio premio_3;
            public Premio premio_4;
            public Premio premio_5;

            public class Premio {
                public String bilhete;
                public String valor_pago;
            }
        }

        public TimeCoracao time_coracao;

        public class TimeCoracao {
            public String time;
            public String ganhadores;
            public String valor_pago;
        }

        // Dupla sena
        public List<String> dezenas_1;
        public List<String> dezenas_2;
        public Premiacao premiacao_1;
        public Premiacao premiacao_2;

        public String mes;
    }

    public class ProximoConcurso {
        public String data;
        public String valor_estimado;
    }
}
