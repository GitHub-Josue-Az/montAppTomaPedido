package com.gob.proyectomontpedidosinicial.presentation.inicio.pedidos.dialogs.adapters;

import androidx.recyclerview.widget.RecyclerView;

import com.gob.proyectomontpedidosinicial.data.entities.ListaDeProductos;
import com.gob.proyectomontpedidosinicial.data.entities.ProductoPorUsuario;

public interface AdapterInterfaceAgregarProducto {

    void agregarProducto(ProductoPorUsuario listaDeProductos);
    void showAgregarStocks(RecyclerView.ViewHolder hold);

}
