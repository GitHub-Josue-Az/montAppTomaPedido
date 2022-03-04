package com.example.proyectomontpedidosinicial.presentation.inicio.pedidos.dialogs;

import com.example.proyectomontpedidosinicial.data.entities.ListaDeProductos;

public interface PopUpAgregarProductosTableInterface {

    void cerrarDialogProductos();
    void guardarProductoListaDeProductos(ListaDeProductos listaDeProductos, int position);

}
