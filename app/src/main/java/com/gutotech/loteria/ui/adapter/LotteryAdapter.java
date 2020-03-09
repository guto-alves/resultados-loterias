package com.gutotech.loteria.ui.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.gutotech.loteria.data.model.Result;
import com.gutotech.loteria.R;
import com.gutotech.loteria.data.model.Lottery;

import java.util.ArrayList;
import java.util.List;

public class LotteryAdapter extends RecyclerView.Adapter<LotteryAdapter.ViewHolder> {

    public interface LotteryClickListener {
        void onClick(View view, Result result);

        void onNextClick(int position);

        void onPreviousClick(int position);
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        private ImageButton proxImageButton;
        private ImageButton anteriorImageButton;

        private TextView loteriaTextView;
        private TextView concursoDataTextView;

        private RecyclerView dezenasRecyclerView;
        private LinearLayout dezenasDuplaSenaLinearLayout;
        private RecyclerView dezenas1RecyclerView;
        private RecyclerView dezenas2RecyclerView;
        private RecyclerView premiosFederalRecyclerView;

        private TextView timeOuMesTextView;

        private LinearLayout valorAcumuladoLinearLayout;
        private TextView valorEstimadoProxTextView;
        private int position;

        ViewHolder(@NonNull View itemView) {
            super(itemView);

            proxImageButton = itemView.findViewById(R.id.proximoImageButton);
            anteriorImageButton = itemView.findViewById(R.id.anteriorImageButton);

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

    private Context mContext;
    private List<Result> mResults;
    private LotteryClickListener mClickListener;

    public LotteryAdapter(Context context, LotteryClickListener clickListener) {
        mContext = context;
        mClickListener = clickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.adapter_loteria_item, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        if (mResults != null) {
            try {
                Result result = mResults.get(position);

                holder.itemView.setOnClickListener(v -> mClickListener.onClick(v, mResults.get(position)));
                holder.proxImageButton.setOnClickListener(v -> mClickListener.onNextClick(position));
                holder.anteriorImageButton.setOnClickListener(v -> mClickListener.onPreviousClick(position));

                holder.position = position;
                holder.loteriaTextView.setText(result.lottery.name);
                holder.loteriaTextView.setTextColor(ContextCompat.getColor(mContext, result.lottery.color));

                try {
                    holder.concursoDataTextView.setText(mContext.getString(R.string.concurso_e_data,
                            result.concurso.numero, result.concurso.data));
                } catch (NullPointerException e) {
                    Log.i("LotteryAdapter", "curso e data - Lottery: " + result.lottery.name);
                }
                holder.timeOuMesTextView.setVisibility(View.GONE);

                DezenasAdapter dezenasAdapter;

                if (result.lottery == Lottery.FEDERAL) {
                    holder.dezenasRecyclerView.setVisibility(View.GONE);
                    holder.dezenasDuplaSenaLinearLayout.setVisibility(View.GONE);
                    holder.premiosFederalRecyclerView.setVisibility(View.VISIBLE);

                    List<Result.Concurso.Premiacao.Premio> premios = new ArrayList<>();
                    try {
                        premios.add(result.concurso.premiacao.premio_1);
                        premios.add(result.concurso.premiacao.premio_2);
                        premios.add(result.concurso.premiacao.premio_3);
                        premios.add(result.concurso.premiacao.premio_4);
                        premios.add(result.concurso.premiacao.premio_5);
                    } catch (NullPointerException ignored) {
                    }

                    PremiosFederalAdapter premiosFederalAdapter = new PremiosFederalAdapter(mContext, premios);
                    holder.premiosFederalRecyclerView.setAdapter(premiosFederalAdapter);
                } else {
                    if (result.lottery == Lottery.DUPLA_SENA) {
                        holder.dezenasRecyclerView.setVisibility(View.GONE);
                        holder.premiosFederalRecyclerView.setVisibility(View.GONE);
                        holder.dezenasDuplaSenaLinearLayout.setVisibility(View.VISIBLE);

                        dezenasAdapter = new DezenasAdapter(mContext, result.concurso.dezenas_1, result.lottery.color);
                        holder.dezenas1RecyclerView.setAdapter(dezenasAdapter);

                        dezenasAdapter = new DezenasAdapter(mContext, result.concurso.dezenas_2, result.lottery.color);
                        holder.dezenas2RecyclerView.setAdapter(dezenasAdapter);
                    } else {
                        holder.premiosFederalRecyclerView.setVisibility(View.GONE);
                        holder.dezenasDuplaSenaLinearLayout.setVisibility(View.GONE);
                        holder.dezenasRecyclerView.setVisibility(View.VISIBLE);

                        try {
                            dezenasAdapter = new DezenasAdapter(mContext, result.concurso.dezenas, result.lottery.color);
                            holder.dezenasRecyclerView.setAdapter(dezenasAdapter);
                        } catch (NullPointerException e) {
                            Log.i("LotteryAdapter", "dezenas - Lottery: " + result.lottery.name);
                        }

                        holder.timeOuMesTextView.setVisibility(View.VISIBLE);

                        if (result.lottery == Lottery.TIMEMANIA) {
                            holder.timeOuMesTextView.setText(mContext.getString(
                                    R.string.time_coracao, result.concurso.time_coracao.time));
                        } else if (result.lottery == Lottery.DIA_DE_SORTE) {
                            holder.timeOuMesTextView.setText(mContext.getString(R.string.mes_sorte,
                                    result.concurso.mes));
                        } else {
                            holder.timeOuMesTextView.setVisibility(View.GONE);
                        }
                    }

                    try {
                        holder.valorEstimadoProxTextView.setText(mContext.getString(R.string.valor,
                                result.proximo_concurso.valor_estimado));
                    } catch (NullPointerException ignored) {
                    }
                }
            } catch (NullPointerException ignored) {
            }
        }
    }

    @Override
    public int getItemCount() {
        return mResults != null ? mResults.size() : 0;
    }

    public void setResults(List<Result> results) {
        mResults = results;
        notifyDataSetChanged();
    }
}
