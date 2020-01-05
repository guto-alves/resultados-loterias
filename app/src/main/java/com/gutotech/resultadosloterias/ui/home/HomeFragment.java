package com.gutotech.resultadosloterias.ui.home;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.gutotech.resultadosloterias.R;
import com.gutotech.resultadosloterias.adapter.LoteriasAdapter;
import com.gutotech.resultadosloterias.model.Resultado;

import java.util.List;

public class HomeFragment extends Fragment {
    private HomeViewModel mHomeViewModel;

    private RecyclerView loteriasRecyclerView;
    private LoteriasAdapter loteriasAdapter;

    private ProgressBar progressBar;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_home, container, false);

        mHomeViewModel = ViewModelProviders.of(this).get(HomeViewModel.class);

        mHomeViewModel.getResultadosList().observe(this, new Observer<List<Resultado>>() {
            @Override
            public void onChanged(List<Resultado> resultados) {
                loteriasAdapter.notifyDataSetChanged();
            }
        });

        mHomeViewModel.getIsLoading().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean isLoading) {
                if (isLoading) {
                    progressBar.setVisibility(View.VISIBLE);
                    loteriasRecyclerView.setVisibility(View.GONE);
                } else {
                    progressBar.setVisibility(View.GONE);
                    loteriasRecyclerView.setVisibility(View.VISIBLE);
                }
            }
        });

        progressBar = root.findViewById(R.id.progressBar);
        loteriasRecyclerView = root.findViewById(R.id.loteriasRecyclerView);

        setUpRecyclerView();

        return root;
    }

    private void setUpRecyclerView() {
        loteriasRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        loteriasRecyclerView.setHasFixedSize(true);

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(ItemTouchHelper.LEFT, ItemTouchHelper.RIGHT | ItemTouchHelper.LEFT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                int position = viewHolder.getAdapterPosition();

                if (direction == ItemTouchHelper.RIGHT)
                    mHomeViewModel.getProximoResultado(position);
                else
                    mHomeViewModel.getResultadoAnterior(position);
            }
        }).attachToRecyclerView(loteriasRecyclerView);

        loteriasAdapter = new LoteriasAdapter(getActivity(), mHomeViewModel.getResultadosList().getValue(), loteriaClickListener);
        loteriasRecyclerView.setAdapter(loteriasAdapter);
    }

    private final LoteriasAdapter.LoteriaClickListener loteriaClickListener = new LoteriasAdapter.LoteriaClickListener() {
        @Override
        public void onClick(View view, Resultado resultado) {
            Log.i("test", resultado.loteria.nome);
        }

        @Override
        public void onProximoClick(int position) {
            mHomeViewModel.getProximoResultado(position);
        }

        @Override
        public void onAnteriorClick(int position) {
            mHomeViewModel.getResultadoAnterior(position);
        }
    };
}