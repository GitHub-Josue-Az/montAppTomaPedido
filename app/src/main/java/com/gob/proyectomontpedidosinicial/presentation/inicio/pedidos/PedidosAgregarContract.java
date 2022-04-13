package com.gob.proyectomontpedidosinicial.presentation.inicio.pedidos;

import com.gob.proyectomontpedidosinicial.core.BasePresenter;
import com.gob.proyectomontpedidosinicial.core.BaseView;
import com.gob.proyectomontpedidosinicial.data.db.entity.EntityCondicionDePago;
import com.gob.proyectomontpedidosinicial.data.db.entity.EntityDireccionCliente;
import com.gob.proyectomontpedidosinicial.data.db.entity.EntityTipoDeCliente;
import com.gob.proyectomontpedidosinicial.data.entities.CondicionDePago;
import com.gob.proyectomontpedidosinicial.data.entities.DireccionCliente;
import com.gob.proyectomontpedidosinicial.data.entities.ProductoPorUsuario;
import com.gob.proyectomontpedidosinicial.data.entities.TipoDeCliente;
import com.gob.proyectomontpedidosinicial.presentation.inicio.pedidos.dialogs.PopUpAgregarProductosContract;

import java.util.ArrayList;

public interface PedidosAgregarContract {


    interface View extends BaseView<PedidosAgregarContract.Presenter> {

        void listaDeTipoDeClientes(ArrayList<EntityTipoDeCliente> clientes, int tipo);
        void listaTipoDeCondicion(ArrayList<EntityCondicionDePago> condicionDePagos,int tipo);
        void listaDireccion(ArrayList<EntityDireccionCliente> direccionClientes,int tipo);
    }

    interface Presenter extends BasePresenter {

        void getListaDeTipoDeClientes(int tipo);
        void getListaTipoDeCondicion(int tipo);
        void getListaDireccion(int tipo);
    }

}
