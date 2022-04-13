package com.gob.proyectomontpedidosinicial.core;

import android.view.View;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.gob.proyectomontpedidosinicial.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LoaderViewHold extends RecyclerView.ViewHolder {

    @BindView(R.id.progresooo)
    ProgressBar progresoIni;

    public LoaderViewHold(@NonNull View itemView) {
        super(itemView);
        ButterKnife.bind(this,itemView);
    }

}
