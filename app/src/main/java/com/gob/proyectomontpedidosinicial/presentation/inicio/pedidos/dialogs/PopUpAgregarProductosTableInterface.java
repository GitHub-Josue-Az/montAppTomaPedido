package com.gob.proyectomontpedidosinicial.presentation.inicio.pedidos.dialogs;

import com.gob.proyectomontpedidosinicial.data.entities.ListaDeProductos;

public interface PopUpAgregarProductosTableInterface {

    void cerrarDialogProductosTable();
    void guardarProductoListaDeProductos(ListaDeProductos listaDeProductos, int position);

}
