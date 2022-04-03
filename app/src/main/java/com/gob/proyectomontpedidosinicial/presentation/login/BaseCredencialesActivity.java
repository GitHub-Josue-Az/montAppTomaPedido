package com.gob.proyectomontpedidosinicial.presentation.login;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;

import com.gob.proyectomontpedidosinicial.core.BaseActivity;

import butterknife.ButterKnife;
import com.gob.proyectomontpedidosinicial.R;
import com.gob.proyectomontpedidosinicial.data.local.SessionManager;
import com.gob.proyectomontpedidosinicial.presentation.inicio.InicioMenuActivity;
import com.gob.proyectomontpedidosinicial.utils.ActivityUtils;
import com.gob.proyectomontpedidosinicial.utils.ProgressDialogCustom;

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
               moveTaskToBack(true);
                break;
            case "CombioDeContrasena":
         /* TODO: Si estoy en la vista de cambio de contraseña y regreso que me vuelva al login*/
                getSupportFragmentManager().popBackStack();
                /* Mostrar login */
                break;
            default:
                moveTaskToBack(true);
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



