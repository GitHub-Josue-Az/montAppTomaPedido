package com.example.proyectomontpedidosinicial.presentation.inicio.productos;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;

import com.example.proyectomontpedidosinicial.R;
import com.example.proyectomontpedidosinicial.core.BaseFragment;
import com.example.proyectomontpedidosinicial.data.local.SessionManager;
import com.example.proyectomontpedidosinicial.presentation.login.LoginContract;
import com.example.proyectomontpedidosinicial.utils.ProgressDialogCustom;

import butterknife.ButterKnife;

/*implements LoginContract.View*/
public class ProductosAgregarFragment extends BaseFragment{

    private static final String TAG = ProductosAgregarFragment.class.getSimpleName();

    private String type;

    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;

    /*private LoginContract.Presenter mPresenter;*/
    private ProgressDialogCustom mProgressDialogCustom;
    private changeFragmentActivity mCallback;

    /* SessionManager */
    private SessionManager mSessionManager;



    private LoginContract.Presenter mPresenter;


    public ProductosAgregarFragment() {
    }

    public interface changeFragmentActivity {
        void gotoInicioFragmenProd();
    }

    public static ProductosAgregarFragment newInstance() {
        return new ProductosAgregarFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mSessionManager = new SessionManager(getContext());
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_productos_agregar, container, false);
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
       /* mCallback = (changeFragmentActivity) context;*/
    }

    @Override
    public void onDetach() {
        mCallback = null;
        super.onDetach();
    }


}
