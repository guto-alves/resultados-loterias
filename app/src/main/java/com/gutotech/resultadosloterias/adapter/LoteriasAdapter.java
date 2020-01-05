package com.gutotech.resultadosloterias.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.gutotech.resultadosloterias.R;
import com.gutotech.resultadosloterias.model.Loteria;
import com.gutotech.resultadosloterias.model.Resultado;

import java.util.ArrayList;
import java.util.List;

public class LoteriasAdapter extends RecyclerView.Adapter<LoteriasAdapter.ViewHolder> {

    public interface LoteriaClickListener {
        void onClick(View view, Resultado resultado);

        void onProximoClick(int position);

        void onAnteriorClick(int position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView loteriaTextView;
        public TextView concursoDataTextView;

        public RecyclerView dezenasRecyclerView;
        public LinearLayout dezenasDuplaSenaLinearLayout;
        public RecyclerView dezenas1RecyclerView;
        public RecyclerView dezenas2RecyclerView;
        public RecyclerView premiosFederalRecyclerView;

        public TextView timeOuMesTextView;

        public LinearLayout valorAcumuladoLinearLayout;
        public TextView valorEstimadoProxTextView;
        public int position;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            ImageButton proxImageButton = itemView.findViewById(R.id.proximoImageButton);
            proxImageButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    clickListener.onProximoClick(position);
                }
            });

            ImageButton anteriorImageButton = itemView.findViewById(R.id.anteriorImageButton);
            anteriorImageButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    clickListener.onAnteriorClick(position);
                }
            });

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    clickListener.onClick(v, resultados.get(position));
                }
            });

            loteriaTextView = itemView.findViewById(R.id.loteriaTextView);
            concursoDataTextView = itemView.findViewById(R.id.concursoDataTextView);

            dezenasRecyclerView = itemView.findViewById(R.id.dezenasRecyclerView);
            dezenasDuplaSenaLinearLayout = itemView.findViewById(R.id.dezenasDuplaSenaLinearLayout);
            dezenas1RecyclerView = itemView.findViewById(R.id.dezenas1RecyclerView);
            dezenas2RecyclerView = itemView.findViewById(R.id.dezenas2RecyclerView);
            premiosFederalRecyclerView = itemView.findViewById(R.id.premiosFederalRecyclerView);

            timeOuMesTextView = itemView.findViewById(R.id.timeOuMesTextView);

            valorAcumuladoLinearLayout = itemView.findViewById(R.id.valorAcumuladoLinearLayout);
            valorEstimadoProxTextView = itemView.findViewById(R.id.valorEstimadoProxTextView);

            dezenasRecyclerView.setHasFixedSize(true);
            dezenas1RecyclerView.setHasFixedSize(true);
            dezenas2RecyclerView.setHasFixedSize(true);
            premiosFederalRecyclerView.setHasFixedSize(true);
        }
    }

    private Context context;
    private List<Resultado> resultados;
    private LoteriaClickListener clickListener;

    public LoteriasAdapter(Context context, List<Resultado> resultados, LoteriaClickListener clickListener) {
        this.context = context;
        this.resultados = resultados;
        this.clickListener = clickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.loteria_list_item, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Resultado resultado = resultados.get(position);

        holder.position = position;
        holder.loteriaTextView.setText(resultado.loteria.nome);
        holder.loteriaTextView.setTextColor(ContextCompat.getColor(context, resultado.loteria.color));
        holder.concursoDataTextView.setText(context.getString(R.string.concurso_e_data, resultado.concurso.numero, resultado.concurso.data));

        holder.timeOuMesTextView.setVisibility(View.GONE);

        DezenasAdapter dezenasAdapter;

        if (resultado.loteria == Loteria.FEDERAL) {
            holder.dezenasRecyclerView.setVisibility(View.GONE);
            holder.dezenasDuplaSenaLinearLayout.setVisibility(View.GONE);
            holder.premiosFederalRecyclerView.setVisibility(View.VISIBLE);

            List<Resultado.Concurso.Premiacao.Premio> premios = new ArrayList<>();
            premios.add(resultado.concurso.premiacao.premio_1);
            premios.add(resultado.concurso.premiacao.premio_2);
            premios.add(resultado.concurso.premiacao.premio_3);
            premios.add(resultado.concurso.premiacao.premio_4);
            premios.add(resultado.concurso.premiacao.premio_5);

            PremiosFederalAdapter premiosFederalAdapter = new PremiosFederalAdapter(context, premios);
            holder.premiosFederalRecyclerView.setAdapter(premiosFederalAdapter);
        } else {
            if (resultado.loteria == Loteria.DUPLA_SENA) {
                holder.dezenasRecyclerView.setVisibility(View.GONE);
                holder.premiosFederalRecyclerView.setVisibility(View.GONE);
                holder.dezenasDuplaSenaLinearLayout.setVisibility(View.VISIBLE);

                dezenasAdapter = new DezenasAdapter(context, resultado.concurso.dezenas_1, resultado.loteria.color);
                holder.dezenas1RecyclerView.setAdapter(dezenasAdapter);

                dezenasAdapter = new DezenasAdapter(context, resultado.concurso.dezenas_2, resultado.loteria.color);
                holder.dezenas2RecyclerView.setAdapter(dezenasAdapter);
            } else {
                holder.premiosFederalRecyclerView.setVisibility(View.GONE);
                holder.dezenasDuplaSenaLinearLayout.setVisibility(View.GONE);
                holder.dezenasRecyclerView.setVisibility(View.VISIBLE);

                dezenasAdapter = new DezenasAdapter(context, resultado.concurso.dezenas, resultado.loteria.color);
                holder.dezenasRecyclerView.setAdapter(dezenasAdapter);

                holder.timeOuMesTextView.setVisibility(View.VISIBLE);

                if (resultado.loteria == Loteria.TIMEMANIA)
                    holder.timeOuMesTextView.setText(context.getString(R.string.time_coracao, resultado.concurso.time_coracao.time));
                else if (resultado.loteria == Loteria.DIA_DE_SORTE)
                    holder.timeOuMesTextView.setText(context.getString(R.string.mes_sorte, resultado.concurso.mes));
                else
                    holder.timeOuMesTextView.setVisibility(View.GONE);
            }

            holder.valorEstimadoProxTextView.setText(context.getString(R.string.valor, resultado.proximo_concurso.valor_estimado));
        }
    }

    @Override
    public int getItemCount() {
        return resultados.size();
    }
}
