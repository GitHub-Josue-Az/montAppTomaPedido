package com.example.proyectomontpedidosinicial.presentation.inicio.pedidos.dialogs;

import com.example.proyectomontpedidosinicial.core.BasePresenter;
import com.example.proyectomontpedidosinicial.core.BaseView;

import java.util.ArrayList;
import java.util.List;

public interface PopUpAgregarClientesContract {

    interface View extends BaseView<PopUpAgregarClientesContract.Presenter> {

        void listaClientes();
    }

    interface Presenter extends BasePresenter {

        void getListaClientes();
    }



}
