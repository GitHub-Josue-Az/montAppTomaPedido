package com.example.proyectomontpedidosinicial.presentation.login;

import android.content.Context;

import com.example.proyectomontpedidosinicial.data.entities.APIErrorNuevo;
import com.example.proyectomontpedidosinicial.data.entities.UserLoginEntity;
import com.example.proyectomontpedidosinicial.data.entities.body.CambiarClave;
import com.example.proyectomontpedidosinicial.data.entities.body.UserCredentials;
import com.example.proyectomontpedidosinicial.data.local.SessionManager;
import com.example.proyectomontpedidosinicial.data.remote.ServiceFactory;
import com.example.proyectomontpedidosinicial.data.remote.request.RequestLogin;
import com.example.proyectomontpedidosinicial.utils.ErrorUtils;


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
        Call<UserLoginEntity> orders = requestLogin.getDataPostLogin(userCredentials);

        orders.enqueue(new Callback<UserLoginEntity>() {
            @Override
            public void onResponse(Call<UserLoginEntity> call, Response<UserLoginEntity> response) {
                mView.setLoadingIndicator(false);
                if (response.isSuccessful()) {
                    mView.checkingForVerifica(response.body());
                    mSessionManager.setUserObjectEntity(response.body());
                    mSessionManager.setUserLogeado(true);
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
                            mView.showErrorMessage("Error al obtener los items");
                            break;
                        default:
                            mView.showErrorMessage("Error desconocido");
                            break;
                    }
                }
            }

            @Override
            public void onFailure(Call<UserLoginEntity> call, Throwable t) {
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
