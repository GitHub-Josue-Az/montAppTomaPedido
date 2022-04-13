package com.gob.proyectomontpedidosinicial.presentation.inicio.pedidos.dialogs.adapters;

import androidx.recyclerview.widget.RecyclerView;

import com.gob.proyectomontpedidosinicial.data.db.entity.EntityProductoPorUsuario;
import com.gob.proyectomontpedidosinicial.data.entities.ListaDeProductos;
import com.gob.proyectomontpedidosinicial.data.entities.ProductoPorUsuario;

public interface AdapterInterfaceAgregarProducto {

    void agregarProducto(EntityProductoPorUsuario listaDeProductos);
    void showAgregarStocks(RecyclerView.ViewHolder hold);

}
