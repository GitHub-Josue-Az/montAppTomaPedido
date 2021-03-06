package com.gob.proyectomontpedidosinicial.presentation.inicio.pedidos.dialogs;

import com.gob.proyectomontpedidosinicial.core.BasePresenter;
import com.gob.proyectomontpedidosinicial.core.BaseView;
import com.gob.proyectomontpedidosinicial.data.db.entity.EntityCliente;
import com.gob.proyectomontpedidosinicial.data.entities.Cliente;

import java.util.ArrayList;
import java.util.List;

public interface PopUpAgregarClientesContract {

    interface View extends BaseView<PopUpAgregarClientesContract.Presenter> {

        void listaClientes(ArrayList<EntityCliente> clientes, int tipo);
    }

    interface Presenter extends BasePresenter {

        void getListaClientes(int tipo);
    }



}
