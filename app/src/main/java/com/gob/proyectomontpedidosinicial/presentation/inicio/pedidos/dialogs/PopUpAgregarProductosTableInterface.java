package com.gob.proyectomontpedidosinicial.presentation.inicio.pedidos.dialogs;

import com.gob.proyectomontpedidosinicial.data.entities.ListaDeProductos;
import com.gob.proyectomontpedidosinicial.data.entities.ProductoPorUsuario;

public interface PopUpAgregarProductosTableInterface {

    void cerrarDialogProductosTable();
    void guardarProductoListaDeProductos(ProductoPorUsuario listaDeProductos, int position);

}
