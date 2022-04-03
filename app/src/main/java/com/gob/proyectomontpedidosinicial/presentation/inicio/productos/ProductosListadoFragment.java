package com.gob.proyectomontpedidosinicial.presentation.inicio.productos;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;

import com.gob.proyectomontpedidosinicial.R;
import com.gob.proyectomontpedidosinicial.core.BaseFragment;
import com.gob.proyectomontpedidosinicial.data.local.SessionManager;
import com.gob.proyectomontpedidosinicial.presentation.login.LoginContract;
import com.gob.proyectomontpedidosinicial.utils.ProgressDialogCustom;

import butterknife.ButterKnife;

/*implements LoginContract.View*/
public class ProductosListadoFragment extends BaseFragment{

    private static final String TAG = ProductosListadoFragment.class.getSimpleName();

    private String type;

    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;

    /*private LoginContract.Presenter mPresenter;*/
    private ProgressDialogCustom mProgressDialogCustom;
    private changeFragmentActivity mCallback;

    /* SessionManager */
    private SessionManager mSessionManager;



    private LoginContract.Presenter mPresenter;


    public ProductosListadoFragment() {
    }

    public interface changeFragmentActivity {
        void gotoInicioFragment();
    }

    public static ProductosListadoFragment newInstance() {
        return new ProductosListadoFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mSessionManager = new SessionManager(getContext());
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_productos_listado, container, false);
        ButterKnife.bind(this, root);


        mProgressDialogCustom = new ProgressDialogCustom(getContext(), "Cargando...");


        return root;
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
     /*   mCallback = (changeFragmentActivity) context;*/
    }

    @Override
    public void onDetach() {
     /*   mCallback = null;*/
        super.onDetach();
    }


}
