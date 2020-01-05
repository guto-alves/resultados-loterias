package com.gutotech.resultadosloterias.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.gutotech.resultadosloterias.R;
import com.gutotech.resultadosloterias.model.Resultado;

import java.util.List;

public class PremiosFederalAdapter extends RecyclerView.Adapter<PremiosFederalAdapter.ViewHolder> {

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView destinoTextView;
        public TextView bilheteTextView;
        public TextView premioTextView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            destinoTextView = itemView.findViewById(R.id.destinoTextView);
            bilheteTextView = itemView.findViewById(R.id.bilheteTextView);
            premioTextView = itemView.findViewById(R.id.premioTextView);
        }
    }

    private Context context;
    private List<Resultado.Concurso.Premiacao.Premio> premios;

    public PremiosFederalAdapter(Context context, List<Resultado.Concurso.Premiacao.Premio> premios) {
        this.context = context;
        this.premios = premios;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_premio_federal, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Resultado.Concurso.Premiacao.Premio premio = premios.get(position);

        holder.destinoTextView.setText(context.getString(R.string.destino, position + 1));
        holder.bilheteTextView.setText(premio.bilhete);
        holder.premioTextView.setText(context.getString(R.string.valor, premio.valor_pago));
    }

    @Override
    public int getItemCount() {
        return premios.size();
    }
}
