package com.gutotech.resultadosloterias.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.gutotech.resultadosloterias.R;
import com.gutotech.resultadosloterias.model.Loteria;
import com.gutotech.resultadosloterias.model.Resultado;

import java.util.List;

public class LoteriasAdapter extends RecyclerView.Adapter<LoteriasAdapter.ViewHolder> {

    public interface LoteriaClickListener {
        void onClick(View view, Resultado resultado);
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        public TextView loteriaTextView;
        public TextView concursoDataTextView;
        public RecyclerView dezenasRecyclerView;
        public TextView valorEstimadoProxTextView;
        public int position;

        ViewHolder(@NonNull View itemView) {
            super(itemView);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    clickListener.onClick(v, resultados.get(position));
                }
            });

            loteriaTextView = itemView.findViewById(R.id.loteriaTextView);
            concursoDataTextView = itemView.findViewById(R.id.concursoDataTextView);
            dezenasRecyclerView = itemView.findViewById(R.id.dezenasRecyclerView);
            dezenasRecyclerView.setLayoutManager(new GridLayoutManager(context, 6));
            dezenasRecyclerView.setHasFixedSize(true);

            valorEstimadoProxTextView = itemView.findViewById(R.id.valorEstimadoProxTextView);
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

        holder.loteriaTextView.setText(resultado.loteria.nome);
        holder.concursoDataTextView.setText(String.format("%s (%s)", resultado.concurso.numero, resultado.concurso.data));

        if (resultado.loteria != Loteria.DUPLA_SENA && resultado.loteria != Loteria.FEDERAL) {
            DezenasAdapter dezenasAdapter = new DezenasAdapter(resultado.concurso.dezenas);
            holder.dezenasRecyclerView.setAdapter(dezenasAdapter);
        }

//        if (resultado.loteria != Loteria.FEDERAL)
//            holder.valorEstimadoProxTextView.setText(String.format("R$ %s", resultado.proximo_concurso.valor_estimado));
    }

    @Override
    public int getItemCount() {
        return resultados.size();
    }
}
