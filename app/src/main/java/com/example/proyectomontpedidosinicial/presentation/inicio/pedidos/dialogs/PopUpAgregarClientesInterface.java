package com.example.proyectomontpedidosinicial.presentation.inicio.pedidos.dialogs;

import com.example.proyectomontpedidosinicial.data.entities.ListaDeClientes;

public interface PopUpAgregarClientesInterface {

    void cerrarDialog();
    void filtrarClientes();
    void agregarClientes(ListaDeClientes listaDeClientes);

}
