package com.gob.proyectomontpedidosinicial.presentation.inicio.pedidos.dialogs;

import com.gob.proyectomontpedidosinicial.core.BasePresenter;
import com.gob.proyectomontpedidosinicial.core.BaseView;
import com.gob.proyectomontpedidosinicial.data.entities.Cliente;
import com.gob.proyectomontpedidosinicial.data.entities.ProductoPorUsuario;

import java.util.ArrayList;

public interface PopUpAgregarProductosContract {

    interface View extends BaseView<PopUpAgregarProductosContract.Presenter> {

        void listaProductos(ArrayList<ProductoPorUsuario> clientes);
    }

    interface Presenter extends BasePresenter {

        void getListaProductos();
    }



}
