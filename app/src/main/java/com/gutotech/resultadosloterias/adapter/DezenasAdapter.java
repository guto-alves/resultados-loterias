package com.gutotech.resultadosloterias.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.gutotech.resultadosloterias.R;

import java.util.List;

public class DezenasAdapter extends RecyclerView.Adapter<DezenasAdapter.ViewHolder> {

    class ViewHolder extends RecyclerView.ViewHolder {
        public TextView dezenaTextView;

        ViewHolder(@NonNull View itemView) {
            super(itemView);

            dezenaTextView = itemView.findViewById(R.id.dezenaTextView);
        }
    }

    private List<String> dezenas;

    public DezenasAdapter(List<String> dezenas) {
        this.dezenas = dezenas;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.dezena_list_item, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.dezenaTextView.setText(dezenas.get(position));
    }

    @Override
    public int getItemCount() {
        return dezenas.size();
    }
}
