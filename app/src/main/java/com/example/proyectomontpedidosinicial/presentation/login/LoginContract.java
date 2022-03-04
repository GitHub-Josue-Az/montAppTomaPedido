package com.example.proyectomontpedidosinicial.presentation.login;

import com.example.proyectomontpedidosinicial.core.BaseView;

import com.example.proyectomontpedidosinicial.core.BasePresenter;
import com.example.proyectomontpedidosinicial.core.BaseView;

import com.example.proyectomontpedidosinicial.data.entities.UserLoginEntity;
import com.example.proyectomontpedidosinicial.data.entities.body.CambiarClave;
import com.example.proyectomontpedidosinicial.data.entities.body.UserCredentials;

public interface LoginContract {

    interface View extends BaseView<Presenter> {
        void checkingForVerifica(UserLoginEntity body);
        void recuperContra();
        void regresarHome();
    }

    interface Presenter extends BasePresenter {
        void getCheckCredentials(UserCredentials userCredentials);
        void sendCambiarContra(CambiarClave cambiarClaveEntity);
    }

}




