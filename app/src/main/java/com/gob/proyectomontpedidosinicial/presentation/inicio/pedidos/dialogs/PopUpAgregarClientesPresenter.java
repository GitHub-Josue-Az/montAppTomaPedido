package com.gob.proyectomontpedidosinicial.presentation.inicio.pedidos.dialogs;


import android.content.Context;
import android.util.Log;

import com.gob.proyectomontpedidosinicial.data.entities.APIErrorNuevo;
import com.gob.proyectomontpedidosinicial.data.entities.APIListaProductosPorUsuario;
import com.gob.proyectomontpedidosinicial.data.entities.APIObjectGet;
import com.gob.proyectomontpedidosinicial.data.entities.Cliente;
import com.gob.proyectomontpedidosinicial.data.entities.ListaDeClientes;
import com.gob.proyectomontpedidosinicial.data.entities.LoginUsuarioPost;
import com.gob.proyectomontpedidosinicial.data.local.SessionManager;
import com.gob.proyectomontpedidosinicial.data.remote.ServiceFactory;
import com.gob.proyectomontpedidosinicial.data.remote.request.RequestLogin;
import com.gob.proyectomontpedidosinicial.data.remote.request.RequestPedidos;
import com.gob.proyectomontpedidosinicial.utils.ErrorUtils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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

    public void tokenInvalido(){
        mView.showErrorMessage("Sesión finalizada");
        mSessionManager.setUserLogeado(false);
        /*mSessionManager.setUserTokenLogin("");
        mView.regresarHome();*/
    }

    @Override
    public void getListaClientes() {

        mView.setLoadingIndicator(true);

        RequestPedidos requestpedidos = ServiceFactory.createServiceNuevo(RequestPedidos.class);
        /* Traer del preference el usuario -> codigo_vendedor */
        String codigovendedor = mSessionManager.getUserObjectEntity().getCodigo_vendedor();
        Call<APIListaProductosPorUsuario<Cliente>> orders = requestpedidos.getDataCliente("276");

        orders.enqueue(new Callback<APIListaProductosPorUsuario<Cliente>>() {
            @Override
            public void onResponse(Call<APIListaProductosPorUsuario<Cliente>> call, Response<APIListaProductosPorUsuario<Cliente>> response) {

                if (response.isSuccessful()) {
                    /* Llamar api error aqui, hacer verificación de codigos */
                    APIListaProductosPorUsuario<Cliente> usuariocrede= response.body();
                    Log.d("DATOS DE USUARIO",usuariocrede.toString());
                    if (usuariocrede.getCode() == 200){
                        mView.listaClientes(usuariocrede.getData());
                        /* Aqui debo llamar a mi ROOM para ponerlo meterlo ahi */

                        /*mView.setLoadingIndicator(false);*/
                    }else{
                        if (usuariocrede.getMsj()!=null){
                            if (!usuariocrede.getMsj().isEmpty()){
                                mView.showErrorMessage(usuariocrede.getMsj());
                            }
                        }
                        mView.setLoadingIndicator(false);
                    }
                } else {
                    APIErrorNuevo apiErrorNuevo = ErrorUtils.nuevoParserError(response);
                    switch (response.code()){
                        case 400:
                            mView.showErrorMessage(apiErrorNuevo.getError());
                            break;
                        case 401:
                            tokenInvalido();
                            break;
                        case 500:
                            mView.showErrorMessage("Credenciales incorrectas");
                            break;
                        default:
                            mView.showErrorMessage("Error desconocido");
                            break;
                    }
                    mView.setLoadingIndicator(false);
                }
            }

            @Override
            public void onFailure(Call<APIListaProductosPorUsuario<Cliente>> call, Throwable t) {
                mView.setLoadingIndicator(false);
                mView.showErrorMessage("Error al conectar con el servidor");
            }
        });


    }

    @Override
    public void start() {}

}
