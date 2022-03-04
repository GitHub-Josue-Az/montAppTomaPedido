package com.example.proyectomontpedidosinicial.presentation.inicio.pedidos.dialogs;

import com.example.proyectomontpedidosinicial.data.entities.ListaDeClientes;
import com.example.proyectomontpedidosinicial.data.entities.ListaDeProductos;

public interface PopUpAgregarProductosInterface {

    void cerrarDialogProductos();
    void agregarProductos(ListaDeProductos listaDeProductos);

}
