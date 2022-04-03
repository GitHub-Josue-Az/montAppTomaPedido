package com.gob.proyectomontpedidosinicial.presentation.inicio.pedidos.dialogs;

import com.gob.proyectomontpedidosinicial.data.entities.Cliente;
import com.gob.proyectomontpedidosinicial.data.entities.ListaDeClientes;

public interface PopUpAgregarClientesInterface {

    void cerrarDialog();
    void filtrarClientes();
    void agregarClientes(Cliente listaDeClientes);

}
