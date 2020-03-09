package com.gutotech.loteria.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;

import com.gutotech.loteria.R;

public class ProgressDialogFragment extends DialogFragment {
    private static final String DIALOG_TAG = "ProgressDialogFragment";

    public void openDialog(FragmentManager fragmentManager) {
        if (fragmentManager.findFragmentByTag(DIALOG_TAG) == null) {
            show(fragmentManager, DIALOG_TAG);
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        setCancelable(false);
        return inflater.inflate(R.layout.dialog_fragment_progress, container, false);
    }
}
