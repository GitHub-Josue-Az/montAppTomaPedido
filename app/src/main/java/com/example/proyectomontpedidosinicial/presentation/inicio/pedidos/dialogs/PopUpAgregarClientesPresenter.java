package com.example.proyectomontpedidosinicial.presentation.inicio.pedidos.dialogs;


import android.content.Context;

import com.example.proyectomontpedidosinicial.data.local.SessionManager;

public class PopUpAgregarClientesPresenter implements PopUpAgregarClientesContract.Presenter{


    private static final String TAG = PopUpAgregarClientesPresenter.class.getSimpleName();

    private PopUpAgregarClientesContract.View mView;
    private Context context;
    private SessionManager mSessionManager;

    public PopUpAgregarClientesPresenter(PopUpAgregarClientesContract.View view, Context context){
        this.context = context;
        this.mView = view;
        this.mView.setPresenter(this);
        mSessionManager = new SessionManager(context);
    }

   /* public void tokenInvalido(){
        mView.showErrorMessage("Sesión finalizada");
        mSessionManager.setUserLogeado(false);
        mSessionManager.setUserTokenLogin("");
        mView.regresarHome();
    }*/

    @Override
    public void getListaClientes() {

    }

    @Override
    public void start() {}

}
