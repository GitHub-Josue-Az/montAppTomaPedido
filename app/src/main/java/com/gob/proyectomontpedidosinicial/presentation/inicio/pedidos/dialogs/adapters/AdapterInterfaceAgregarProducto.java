package com.gob.proyectomontpedidosinicial.presentation.inicio.pedidos.dialogs.adapters;

import androidx.recyclerview.widget.RecyclerView;

import com.gob.proyectomontpedidosinicial.data.entities.ListaDeProductos;

public interface AdapterInterfaceAgregarProducto {

    void agregarProducto(ListaDeProductos listaDeProductos);
    void showAgregarStocks(RecyclerView.ViewHolder hold);

}
