package com.example.proyectomontpedidosinicial.presentation.login;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;

import com.example.proyectomontpedidosinicial.core.BaseActivity;

import butterknife.ButterKnife;
import com.example.proyectomontpedidosinicial.R;
import com.example.proyectomontpedidosinicial.data.local.SessionManager;
import com.example.proyectomontpedidosinicial.presentation.inicio.InicioMenuActivity;
import com.example.proyectomontpedidosinicial.utils.ActivityUtils;
import com.example.proyectomontpedidosinicial.utils.ProgressDialogCustom;

public class BaseCredencialesActivity extends BaseActivity implements LoginFragment.changeFragmentActivity{

    private static final String TAG = Activity.class.getSimpleName();
    /*@BindView(R.id.loginFragmentContainer)
    FrameLayout loginFragmentContainer;*/

    private ProgressDialogCustom mProgressDialogCustom;
    /*private LoginContract.Presenter mPresenter;*/

    private LoginFragment loginFragment;
    private SessionManager mSessionManager;


    public BaseCredencialesActivity() {
    }

    public static BaseCredencialesActivity newInstance() {
        return new BaseCredencialesActivity();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        mSessionManager = new SessionManager(this);

        verificarLogeado();

        loginFragment = (LoginFragment) getSupportFragmentManager().findFragmentByTag("LoginFragment");
        if (loginFragment == null){
                loginFragment = LoginFragment.newInstance();
                ActivityUtils.addFragmentToFragment(getSupportFragmentManager(),
                        loginFragment, R.id.loginFragmentContainer,"LoginFragment");
        }

    }


    /* Verificar si esta loguedo */
    private void verificarLogeado(){
        if (mSessionManager.getUserLogeado()){
            next(this,null, InicioMenuActivity.class,true);
        }
    }

    @Override
    public void onBackPressed() {

        int count = getFragmentManager().getBackStackEntryCount();
        int size = getSupportFragmentManager().getFragments().size();

        String tag = getSupportFragmentManager().getFragments().get(size - 1).getTag();
        Log.e("FRAGMENT COUNT", "--------------------->  " + String.valueOf(count));
        Log.e("FRAGMENT LIST", "--------------------->  " + String.valueOf(size));
        Log.e("TAG FRAGMENT 1", "--------------------->  " + tag);

        switch (tag) {
            case "LoginFragment":
                finish();
                break;
            case "CombioDeContrasena":
         /* TODO: Si estoy en la vista de cambio de contraseña y regreso que me vuelva al login*/
                getSupportFragmentManager().popBackStack();
                /* Mostrar login */

                break;
            default:
                finish();
                break;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void gotoRegisterFragment() {

    }
}



