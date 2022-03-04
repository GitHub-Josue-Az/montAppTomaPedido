package com.example.proyectomontpedidosinicial.presentation.login;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatEditText;


import com.example.proyectomontpedidosinicial.core.BaseFragment;
import com.example.proyectomontpedidosinicial.data.entities.UserLoginEntity;
import com.example.proyectomontpedidosinicial.data.entities.body.UserCredentials;
import com.example.proyectomontpedidosinicial.presentation.inicio.InicioMenuActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import com.example.proyectomontpedidosinicial.R;
import com.example.proyectomontpedidosinicial.data.local.SessionManager;
import com.example.proyectomontpedidosinicial.utils.ProgressDialogCustom;

/*implements LoginContract.View*/
public class LoginFragment extends BaseFragment implements LoginContract.View{

    private static final String TAG = LoginFragment.class.getSimpleName();

    private String type;

    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;

    /*private LoginContract.Presenter mPresenter;*/
    private ProgressDialogCustom mProgressDialogCustom;
    private changeFragmentActivity mCallback;

    /* SessionManager */
    private SessionManager mSessionManager;

    private String usuario;
    private String contrasenia;
    private String itemss;


    @BindView(R.id.login_inicio_sesion)
    Button loginInicioSesion;


    @BindView(R.id.login_et_usuario)
    AppCompatEditText loginUsuario;

    @BindView(R.id.login_et_contra)
    AppCompatEditText loginContra;


    private LoginContract.Presenter mPresenter;


    public LoginFragment() {
    }

    public interface changeFragmentActivity {
        void gotoRegisterFragment();
    }

    public static LoginFragment newInstance() {
        return new LoginFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mPresenter = new LoginPresenter(this, getContext());

        mSessionManager = new SessionManager(getContext());
        /*queue = Volley.newRequestQueue(getContext());*/
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_login, container, false);
        ButterKnife.bind(this, root);


        mProgressDialogCustom = new ProgressDialogCustom(getContext(), "Cargando...");

        loginInicioSesion.setOnClickListener(view->clickLogin());

        /*
        etContra.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (etContra.getText().toString().length() > 2) {
                    inicioEntrar.setEnabled(true);
                    inicioEntrar.setBackground(getResources().getDrawable(R.drawable.button_border_inicio));
                } else {
                    inicioEntrar.setEnabled(false);
                    inicioEntrar.setBackground(getResources().getDrawable(R.drawable.button_border_inicio_enabled));
                }
            }
        });*/

        return root;
    }

   /* private void clickOlvidasteContra() {

        OlvidasteFragment olvidasteFragment = OlvidasteFragment.newInstance();
        ActivityUtils.addFragmentToFragment(getActivity().getSupportFragmentManager(),
                olvidasteFragment, R.id.fragmentLogin, "loginOlvidasteFragment");
    }*/

    private void clickLogin() {

        boolean ok = true;

        /* Que se inhabilite el boton */
        /*inicioEntrar.setEnabled(false);*/

       /* usuario = loginUsuario.getText().toString();
        contrasenia = loginContra.getText().toString();

        if (usuario.isEmpty()) {
            Toast.makeText(getContext(), getResources().getString(R.string.usuario_error), Toast.LENGTH_SHORT).show();return; }
        if (contrasenia.isEmpty()) {
            Toast.makeText(getContext(), getResources().getString(R.string.password_error), Toast.LENGTH_SHORT).show();return;
        }


        if (usuario.equals("jalva") && contrasenia.equals("jalva")){
            Toast.makeText(getContext(), getResources().getString(R.string.bienvenido)+" Josue Alva", Toast.LENGTH_SHORT).show();
            nextActivity(getActivity(), null, InicioMenuActivity.class, true);
        }else{
            Toast.makeText(getContext(), getResources().getString(R.string.credenciales_error), Toast.LENGTH_SHORT).show();
        }*/

        nextActivity(getActivity(), null, InicioMenuActivity.class, true);

        /*UserCredentials userCredentials = new UserCredentials();

        userCredentials.setContrasenia(contrasenia);
        userCredentials.setUsuario(usuario);*/

        /*Log.e(TAG, userCredentials.toString());*/

        /*mPresenter.getCheckCredentials(userCredentials);*/
    }


    @Override
    public void checkingForVerifica(UserLoginEntity body) {

        /*Toast.makeText(getContext(), "Bienvenido: " + body.getNombre(), Toast.LENGTH_LONG).show();

        String token = "Bearer " + body.getToken();
        mSessionManager.setUserTokenLogin(token);
        mSessionManager.setUserNameLogin(body.getNombre() + " " + body.getApellidoPaterno());

        inicioEntrar.setEnabled(true);
        Log.e(TAG, token);

        nextActivity(getActivity(), null, InicioMenuActivity.class, true);*/
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mCallback = (changeFragmentActivity) context;
    }

    @Override
    public void onDetach() {
        mCallback = null;
        super.onDetach();
    }

    @Override
    public void setPresenter(LoginContract.Presenter presenter) {
        this.mPresenter = presenter;
    }

    @Override
    public void setLoadingIndicator(boolean active) {
        if (active) {
            mProgressDialogCustom.show();
        } else {
            if (mProgressDialogCustom.isShowing()) {
                mProgressDialogCustom.dismiss();
            }
        }
    }

    @Override
    public void showMessage(String message) {

    }


    @Override
    public void recuperContra() {

    }

    @Override
    public void regresarHome() {

    }

    @Override
    public void showErrorMessage(String message) {
        /*inicioEntrar.setEnabled(true);*/
        Toast.makeText(getContext(), message, Toast.LENGTH_LONG).show();
    }




}
