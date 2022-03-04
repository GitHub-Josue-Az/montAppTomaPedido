package com.example.proyectomontpedidosinicial.core;

import android.view.View;
import android.widget.ProgressBar;

import androidx.recyclerview.widget.RecyclerView;

import com.example.proyectomontpedidosinicial.R;

import butterknife.BindView;
import butterknife.ButterKnife;


public class LoaderViewHolder extends RecyclerView.ViewHolder{

    @BindView(R.id.progressbar)
    ProgressBar progressb;

    public LoaderViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

}