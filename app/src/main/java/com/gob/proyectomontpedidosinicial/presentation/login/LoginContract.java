package com.gob.proyectomontpedidosinicial.presentation.login;

import com.gob.proyectomontpedidosinicial.core.BaseView;

import com.gob.proyectomontpedidosinicial.core.BasePresenter;

import com.gob.proyectomontpedidosinicial.data.entities.LoginUsuarioPost;
import com.gob.proyectomontpedidosinicial.data.entities.body.CambiarClave;
import com.gob.proyectomontpedidosinicial.data.entities.body.UserCredentials;

public interface LoginContract {

    interface View extends BaseView<Presenter> {
        void checkingForVerifica(LoginUsuarioPost body);
        void recuperContra();
        void regresarHome();
    }

    interface Presenter extends BasePresenter {
        void getCheckCredentials(UserCredentials userCredentials);
        void sendCambiarContra(CambiarClave cambiarClaveEntity);
    }

}




