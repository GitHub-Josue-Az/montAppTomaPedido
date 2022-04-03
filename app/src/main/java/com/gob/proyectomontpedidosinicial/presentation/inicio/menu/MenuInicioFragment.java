package com.gob.proyectomontpedidosinicial.presentation.inicio.menu;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.gob.proyectomontpedidosinicial.R;
import com.gob.proyectomontpedidosinicial.core.BaseFragment;
import com.gob.proyectomontpedidosinicial.data.local.SessionManager;
import com.gob.proyectomontpedidosinicial.presentation.login.LoginContract;
import com.gob.proyectomontpedidosinicial.utils.ProgressDialogCustom;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MenuInicioFragment extends BaseFragment{

    private static final String TAG = MenuInicioFragment.class.getSimpleName();

    private String type;

    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;

    /*private LoginContract.Presenter mPresenter;*/
    private ProgressDialogCustom mProgressDialogCustom;
    private changeFragmentActivity mCallback;

    /* SessionManager */
    private SessionManager mSessionManager;

    @BindView(R.id.inicio_card_toma_pedidos)
    ConstraintLayout iniciotomapedidos;

    @BindView(R.id.inicio_card_ingreso_cobranzas)
    ConstraintLayout iniciocobranzas;

    @BindView(R.id.inicio_card_revisar_gestionar)
    ConstraintLayout iniciogestionar;

    @BindView(R.id.inicio_card_revisar_compras)
    ConstraintLayout iniciocompras;

    private LoginContract.Presenter mPresenter;




    public MenuInicioFragment() {
    }

    public interface changeFragmentActivity {
        void gotoListadoPedidosFragment();
    }

    public static MenuInicioFragment newInstance() {
        return new MenuInicioFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mSessionManager = new SessionManager(getContext());
        /*queue = Volley.newRequestQueue(getContext());*/
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_inicio_menu, container, false);
        ButterKnife.bind(this, root);


        mProgressDialogCustom = new ProgressDialogCustom(getContext(), "Cargando...");

        iniciotomapedidos.setOnClickListener(view->clickVistTomaPedidos());

        return root;
    }

    public void clickVistTomaPedidos(){
        mCallback.gotoListadoPedidosFragment();
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


}
