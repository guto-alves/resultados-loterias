package com.gutotech.loteria.ui.adapter;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.ColorInt;
import androidx.annotation.ColorRes;
import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.gutotech.loteria.R;

import java.util.List;

public class DezenasAdapter extends RecyclerView.Adapter<DezenasAdapter.ViewHolder> {

    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView dezenaTextView;

        ViewHolder(@NonNull View itemView) {
            super(itemView);

            dezenaTextView = itemView.findViewById(R.id.dezenaTextView);

            GradientDrawable drawable = (GradientDrawable) dezenaTextView.getBackground();
            drawable.setColor(color);
        }
    }

    private List<String> dezenas;

    @ColorInt
    private int color;

    DezenasAdapter(Context context, List<String> dezenas, @ColorRes int id) {
        this.dezenas = dezenas;
        color = ContextCompat.getColor(context, id);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.adapter_dezena_item, parent, false);
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
