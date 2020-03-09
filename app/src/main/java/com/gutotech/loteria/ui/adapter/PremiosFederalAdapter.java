package com.gutotech.loteria.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.gutotech.loteria.R;
import com.gutotech.loteria.data.model.Result;

import java.util.List;

public class PremiosFederalAdapter extends RecyclerView.Adapter<PremiosFederalAdapter.ViewHolder> {

    static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView destinoTextView;
        private TextView bilheteTextView;
        private TextView premioTextView;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            destinoTextView = itemView.findViewById(R.id.destinoTextView);
            bilheteTextView = itemView.findViewById(R.id.bilheteTextView);
            premioTextView = itemView.findViewById(R.id.premioTextView);
        }
    }

    private Context mContext;
    private List<Result.Concurso.Premiacao.Premio> mPremios;

    PremiosFederalAdapter(Context context, List<Result.Concurso.Premiacao.Premio> premios) {
        mContext = context;
        mPremios = premios;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.adapter_list_item_premio_federal, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Result.Concurso.Premiacao.Premio premio = mPremios.get(position);

        holder.destinoTextView.setText(mContext.getString(R.string.destino, position + 1));
        holder.bilheteTextView.setText(premio.bilhete);
        holder.premioTextView.setText(mContext.getString(R.string.valor, premio.valor_pago));
    }

    @Override
    public int getItemCount() {
        return mPremios.size();
    }
}
