package com.gob.proyectomontpedidosinicial.presentation.inicio.pedidos;


import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.gob.proyectomontpedidosinicial.data.db.entity.EntityCondicionDePago;
import com.gob.proyectomontpedidosinicial.data.db.entity.EntityDireccionCliente;
import com.gob.proyectomontpedidosinicial.data.db.entity.EntityTipoDeCliente;
import com.gob.proyectomontpedidosinicial.data.entities.APIErrorNuevo;
import com.gob.proyectomontpedidosinicial.data.entities.APIListaProductosPorUsuario;
import com.gob.proyectomontpedidosinicial.data.entities.APIObjectGet;
import com.gob.proyectomontpedidosinicial.data.entities.Cliente;
import com.gob.proyectomontpedidosinicial.data.entities.CondicionDePago;
import com.gob.proyectomontpedidosinicial.data.entities.DireccionCliente;
import com.gob.proyectomontpedidosinicial.data.entities.TipoDeCliente;
import com.gob.proyectomontpedidosinicial.data.local.SessionManager;
import com.gob.proyectomontpedidosinicial.data.remote.ServiceFactory;
import com.gob.proyectomontpedidosinicial.data.remote.request.RequestPedidos;
import com.gob.proyectomontpedidosinicial.presentation.inicio.pedidos.dialogs.PopUpAgregarClientesContract;
import com.gob.proyectomontpedidosinicial.utils.ErrorUtils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PedidosAgregarPresenter implements PedidosAgregarContract.Presenter{


    private static final String TAG = PedidosAgregarPresenter.class.getSimpleName();

    private PedidosAgregarContract.View mView;
    private Context context;
    private SessionManager mSessionManager;

    public PedidosAgregarPresenter(PedidosAgregarContract.View view, Context context){
        this.context = context;
        this.mView = view;
        this.mView.setPresenter(this);
        mSessionManager = new SessionManager(context);
    }

    /*public void tokenInvalido(){
        mView.showErrorMessage("Sesión finalizada");
        mSessionManager.setUserLogeado(false);
        *//*mSessionManager.setUserTokenLogin("");
        mView.regresarHome();*//*
    }*/


    @Override
    public void start() {}

    @Override
    public void getListaDeTipoDeClientes(int tipo) {

        mView.setLoadingIndicator(true);

        RequestPedidos requestpedidos = ServiceFactory.createServiceNuevo(RequestPedidos.class);
        /* Traer del preference el usuario -> codigo_vendedor */
        String codigovendedor = mSessionManager.getUserObjectEntity().getCodigo_vendedor();
        Call<APIListaProductosPorUsuario<EntityTipoDeCliente>> orders = requestpedidos.getDataTipoCliente(codigovendedor);

        orders.enqueue(new Callback<APIListaProductosPorUsuario<EntityTipoDeCliente>>() {
            @Override
            public void onResponse(Call<APIListaProductosPorUsuario<EntityTipoDeCliente>> call, Response<APIListaProductosPorUsuario<EntityTipoDeCliente>> response) {

                if (response.isSuccessful()) {
                    /* Llamar api error aqui, hacer verificación de codigos */
                    APIListaProductosPorUsuario<EntityTipoDeCliente> usuariocrede= response.body();
                    Log.d("DATOS DE USUARIO",usuariocrede.toString());
                    if (usuariocrede.getCode() == 200){
                        mView.listaDeTipoDeClientes(usuariocrede.getData(),tipo);
                        /* Una funcion para que se ejecute el room alla */
                         mView.setLoadingIndicator(false);
                    }else{
                        if (usuariocrede.getMsj()!=null){
                            if (!usuariocrede.getMsj().isEmpty()){
                                /* Error  */
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
                            mView.showErrorMessage("Credenciales incorrectas");
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
            public void onFailure(Call<APIListaProductosPorUsuario<EntityTipoDeCliente>> call, Throwable t) {
                mView.setLoadingIndicator(false);
                mView.showErrorMessage("Error al conectar con el servidor");
            }
        });
    }

    @Override
    public void getListaTipoDeCondicion(int tipo) {

        mView.setLoadingIndicator(true);

        RequestPedidos requestpedidos = ServiceFactory.createServiceNuevo(RequestPedidos.class);
        /* Traer del preference el usuario -> codigo_vendedor */
        String codigovendedor = mSessionManager.getUserObjectEntity().getCodigo_vendedor();
        Call<APIListaProductosPorUsuario<EntityCondicionDePago>> orders = requestpedidos.getDataCondicionPago(codigovendedor);

        orders.enqueue(new Callback<APIListaProductosPorUsuario<EntityCondicionDePago>>() {
            @Override
            public void onResponse(Call<APIListaProductosPorUsuario<EntityCondicionDePago>> call, Response<APIListaProductosPorUsuario<EntityCondicionDePago>> response) {

                if (response.isSuccessful()) {
                    /* Llamar api error aqui, hacer verificación de codigos */
                    APIListaProductosPorUsuario<EntityCondicionDePago> usuariocrede= response.body();

                    if (usuariocrede.getCode() == 200){
                        mView.listaTipoDeCondicion(usuariocrede.getData(),tipo);
                        /* Aqui debo llamar a mi ROOM para ponerlo meterlo ahi */

                        mView.setLoadingIndicator(false);
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
                            mView.showErrorMessage("Credenciales incorrectas");
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
            public void onFailure(Call<APIListaProductosPorUsuario<EntityCondicionDePago>> call, Throwable t) {
                mView.setLoadingIndicator(false);
                mView.showErrorMessage("Error al conectar con el servidor");
            }
        });


    }

    @Override
    public void getListaDireccion(int tipo) {

        mView.setLoadingIndicator(true);

        RequestPedidos requestpedidos = ServiceFactory.createServiceNuevo(RequestPedidos.class);
        /* Traer del preference el usuario -> codigo_vendedor */
        String codigovendedor = mSessionManager.getUserObjectEntity().getCodigo_vendedor();
        Call<APIListaProductosPorUsuario<EntityDireccionCliente>> orders = requestpedidos.getDataDireccionCliente(codigovendedor);

        orders.enqueue(new Callback<APIListaProductosPorUsuario<EntityDireccionCliente>>() {
            @Override
            public void onResponse(Call<APIListaProductosPorUsuario<EntityDireccionCliente>> call, Response<APIListaProductosPorUsuario<EntityDireccionCliente>> response) {

                if (response.isSuccessful()) {
                    /* Llamar api error aqui, hacer verificación de codigos */
                    APIListaProductosPorUsuario<EntityDireccionCliente> usuariocrede= response.body();
                    if (usuariocrede.getCode() == 200){
                        /* Almacenar en el ROOM */
                        mView.listaDireccion(usuariocrede.getData(),tipo);

                        /* Aqui debo llamar a mi ROOM para ponerlo meterlo ahi */
                        mView.setLoadingIndicator(false);
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
                            mView.showErrorMessage("Credenciales incorrectas");
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
            public void onFailure(Call<APIListaProductosPorUsuario<EntityDireccionCliente>> call, Throwable t) {
                mView.setLoadingIndicator(false);
                mView.showErrorMessage("Error al conectar con el servidor");
            }
        });


    }


}
