package com.gob.proyectomontpedidosinicial.presentation.login;

import android.content.Context;

import com.gob.proyectomontpedidosinicial.data.entities.APIErrorNuevo;
import com.gob.proyectomontpedidosinicial.data.entities.APIObjectGet;
import com.gob.proyectomontpedidosinicial.data.entities.LoginUsuarioPost;
import com.gob.proyectomontpedidosinicial.data.entities.body.CambiarClave;
import com.gob.proyectomontpedidosinicial.data.entities.body.UserCredentials;
import com.gob.proyectomontpedidosinicial.data.local.SessionManager;
import com.gob.proyectomontpedidosinicial.data.remote.ServiceFactory;
import com.gob.proyectomontpedidosinicial.data.remote.request.RequestLogin;
import com.gob.proyectomontpedidosinicial.utils.ErrorUtils;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginPresenter implements  LoginContract.Presenter {

    private static final String TAG = LoginPresenter.class.getSimpleName();

    private LoginContract.View mView;
    private Context context;
    private SessionManager mSessionManager;


    public LoginPresenter(LoginContract.View mView, Context context) {
        //Para que sepa a con quien se comunica
        this.context = context;
        this.mView = mView;
        this.mView.setPresenter(this);
        mSessionManager = new SessionManager(context);
    }


    public void tokenInvalido(){
        mView.showErrorMessage("Sesión finalizada");
        mSessionManager.setUserLogeado(false);
        mSessionManager.setUserTokenLogin("");
        mView.regresarHome();
    }


    @Override
    public void getCheckCredentials(UserCredentials userCredentials) {

        mView.setLoadingIndicator(true);

        RequestLogin requestLogin = ServiceFactory.createServiceNuevo(RequestLogin.class);
        Call<APIObjectGet<LoginUsuarioPost>> orders = requestLogin.getDataPostLogin(userCredentials.getUsuario(), userCredentials.getClave());

        orders.enqueue(new Callback<APIObjectGet<LoginUsuarioPost>>() {
            @Override
            public void onResponse(Call<APIObjectGet<LoginUsuarioPost>> call, Response<APIObjectGet<LoginUsuarioPost>> response) {
                mView.setLoadingIndicator(true);
                if (response.isSuccessful()) {
                    /* Llamar api error aqui, hacer verificación de codigos */
                    APIObjectGet<LoginUsuarioPost> usuariocrede= response.body();
                    if (usuariocrede.getCode() == 200){
                        mView.checkingForVerifica(usuariocrede.getData());
                         mSessionManager.setUserObjectEntity(usuariocrede.getData());
                        mSessionManager.setUserLogeado(true);
                    }else{
                        if (usuariocrede.getMsj()!=null){
                            if (!usuariocrede.getMsj().isEmpty()){
                                mView.showErrorMessage(usuariocrede.getMsj());
                            }
                        }
                        mSessionManager.setUserLogeado(false);
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
            public void onFailure(Call<APIObjectGet<LoginUsuarioPost>> call, Throwable t) {
                mView.setLoadingIndicator(false);
                mView.showErrorMessage("Error al conectar con el servidor");
            }
        });

    }

    @Override
    public void sendCambiarContra(CambiarClave cambiarClaveEntity) {
    }

    @Override
    public void start() { }


}
