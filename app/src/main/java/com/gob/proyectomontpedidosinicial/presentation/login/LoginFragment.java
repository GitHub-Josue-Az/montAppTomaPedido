package com.gob.proyectomontpedidosinicial.presentation.login;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatEditText;


import com.gob.proyectomontpedidosinicial.core.BaseFragment;
import com.gob.proyectomontpedidosinicial.data.entities.LoginUsuarioPost;
import com.gob.proyectomontpedidosinicial.data.entities.body.UserCredentials;
import com.gob.proyectomontpedidosinicial.presentation.inicio.InicioMenuActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import com.gob.proyectomontpedidosinicial.R;
import com.gob.proyectomontpedidosinicial.data.local.SessionManager;
import com.gob.proyectomontpedidosinicial.utils.ProgressDialogCustom;

import java.util.ArrayList;

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
    private ArrayList<UserCredentials> usuarioCredenciale;
    private LoginContract.Presenter mPresenter;


    @BindView(R.id.login_inicio_sesion)
    Button loginInicioSesion;
    @BindView(R.id.login_et_usuario)
    AppCompatEditText loginUsuario;
    @BindView(R.id.login_et_contra)
    AppCompatEditText loginContra;


    public LoginFragment() {}


    public interface changeFragmentActivity {void gotoRegisterFragment();}

    public static LoginFragment newInstance() {
        return new LoginFragment();
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mPresenter = new LoginPresenter(this, getContext());
        mSessionManager = new SessionManager(getContext());
        /*cargarUsuariosLogin();*/
        /*queue = Volley.newRequestQueue(getContext());*/
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_login, container, false);
        ButterKnife.bind(this, root);
        mProgressDialogCustom = new ProgressDialogCustom(getContext(), "Cargando...");
        loginInicioSesion.setOnClickListener(view->clickLogin());

        /* etContra.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after){}
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}
            @Override
            public void afterTextChanged(Editable s) {
                if (etContra.getText().toString().length() > 2) {
                    inicioEntrar.setEnabled(true);
                    inicioEntrar.setBackground(getResources().getDrawable(R.drawable.button_border_inicio));
                } else {
                    inicioEntrar.setEnabled(false);
                    inicioEntrar.setBackground(getResources().getDrawable(R.drawable.button_border_inicio_enabled));
                }
            } });*/
        return root;
    }

    /*private void clickOlvidasteContra() {
        OlvidasteFragment olvidasteFragment = OlvidasteFragment.newInstance();
        ActivityUtils.addFragmentToFragment(getActivity().getSupportFragmentManager(),
                olvidasteFragment, R.id.fragmentLogin, "loginOlvidasteFragment");
    }*/

    private void clickLogin() {

        boolean ok = true;
        loginInicioSesion.setEnabled(false);
        loginInicioSesion.setEnabled(false);

        /* Que se inhabilite el boton */
        /*inicioEntrar.setEnabled(false);*/

        usuario = loginUsuario.getText().toString();
        contrasenia = loginContra.getText().toString();

        if (usuario.isEmpty()) {
            Toast.makeText(getContext(), getResources().getString(R.string.usuario_error), Toast.LENGTH_SHORT).show();
            loginInicioSesion.setEnabled(true);
            return; }
        if (contrasenia.isEmpty()) {
            Toast.makeText(getContext(), getResources().getString(R.string.password_error), Toast.LENGTH_SHORT).show();
            loginInicioSesion.setEnabled(true);
            return; }

        /*for (int i=0;i<usuarioCredenciale.size();i++) {
             if (usuario.equals(usuarioCredenciale.get(i).getUsuario()) && contrasenia.equals(usuarioCredenciale.get(i).getContrasenia())){
                Toast.makeText(getContext(), getResources().getString(R.string.bienvenido)+" "+usuarioCredenciale.get(i).getUsuario(), Toast.LENGTH_SHORT).show();
                nextActivity(getActivity(), null, InicioMenuActivity.class, true);
                loginInicioSesion.setEnabled(true);
                return;  } }*/

        /*Toast.makeText(getContext(), getResources().getString(R.string.credenciales_error), Toast.LENGTH_SHORT).show();
        loginInicioSesion.setEnabled(true);
        return;*/
        UserCredentials userCredentials = new UserCredentials();
        userCredentials.setUsuario(usuario);
        userCredentials.setClave(contrasenia);
        mPresenter.getCheckCredentials(userCredentials);
    }

    /*public void cargarUsuariosLogin(){
        usuarioCredenciale = new ArrayList<>();
        UserCredentials user = new UserCredentials();
        user.setUsuario("mjosue");
        user.setContrasenia("mjosue");
        UserCredentials usertwo = new UserCredentials();
        usertwo.setUsuario("mcarlos");
        usertwo.setContrasenia("mcarlos");
        UserCredentials usertree = new UserCredentials();
        usertree.setUsuario("mmoises");
        usertree.setContrasenia("mmoises");
        UserCredentials userfour = new UserCredentials();
        userfour.setUsuario("mpaola");
        userfour.setContrasenia("mpaola");
        UserCredentials userfive= new UserCredentials();
        userfive.setUsuario("mliz");
        userfive.setContrasenia("mliz");
        usuarioCredenciale.add(user);
        usuarioCredenciale.add(usertwo);
        usuarioCredenciale.add(usertree);
        usuarioCredenciale.add(userfour);
        usuarioCredenciale.add(userfive);
    }*/


    @Override
    public void checkingForVerifica(LoginUsuarioPost body) {

        Toast.makeText(getContext(), "Bienvenido: " + body.getNombre_usuario(), Toast.LENGTH_LONG).show();
        /*String token = "Bearer " + body.getToken();*/
        /*mSessionManager.setUserTokenLogin(token);*/
        mSessionManager.setUserNameLogin(body.getNombre_usuario());
        loginInicioSesion.setEnabled(true);
        setLoadingIndicator(false);
       /* Log.e(TAG, token);*/
        nextActivity(getActivity(), null, InicioMenuActivity.class, true);
    }

    @Override
    public void onResume() {
        super.onResume();
        loginInicioSesion.setEnabled(true);
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
            }}}

    @Override
    public void showMessage(String message) {}

    @Override
    public void recuperContra() {}

    @Override
    public void regresarHome() {}

    @Override
    public void showErrorMessage(String message) {
        /*inicioEntrar.setEnabled(true);*/
        loginInicioSesion.setEnabled(true);
        Toast.makeText(getContext(), message, Toast.LENGTH_LONG).show();
    }


}
