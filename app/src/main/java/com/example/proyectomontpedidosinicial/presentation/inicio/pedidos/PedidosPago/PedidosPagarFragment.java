package com.example.proyectomontpedidosinicial.presentation.inicio.pedidos.PedidosPago;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.proyectomontpedidosinicial.R;
import com.example.proyectomontpedidosinicial.core.BaseFragment;
import com.example.proyectomontpedidosinicial.data.local.SessionManager;
import com.example.proyectomontpedidosinicial.presentation.login.LoginContract;
import com.example.proyectomontpedidosinicial.utils.ProgressDialogCustom;

import butterknife.BindView;
import butterknife.ButterKnife;

/*implements LoginContract.View*/
public class PedidosPagarFragment extends BaseFragment{

    private static final String TAG = PedidosPagarFragment.class.getSimpleName();

    private String type;

    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;

    /*private LoginContract.Presenter mPresenter;*/
    private ProgressDialogCustom mProgressDialogCustom;
    private changeFragmentActivity mCallback;

    /* SessionManager */
    private SessionManager mSessionManager;


    @BindView(R.id.ln_pedidos_pagar_regresar)
    LinearLayoutCompat lnPagarRegresar;
    @BindView(R.id.ln_pedidos_pagar_inicio)
    LinearLayoutCompat lnPagarInicio;
    @BindView(R.id.et_pedidos_pagar_banco)
    AppCompatEditText etPagarBanco;
    @BindView(R.id.et_pedidos_pagar_op)
    AppCompatEditText etPagarOP;
    @BindView(R.id.et_pedidos_pagar_monto)
    AppCompatEditText etPagarMonto;
    @BindView(R.id.et_pedidos_pagar_fecha)
    AppCompatEditText etPagarFecha;
    @BindView(R.id.et_pedidos_pagar_cuenta)
    AppCompatEditText etPagarCuenta;


    private LoginContract.Presenter mPresenter;


    public PedidosPagarFragment() {
    }

    public interface changeFragmentActivity {
        void gotoInicioFragmentPagar();
        void gotoBackPagar();
    }

    public static PedidosPagarFragment newInstance() {
        return new PedidosPagarFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mSessionManager = new SessionManager(getContext());
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_pedidos_pagar, container, false);
        ButterKnife.bind(this, root);

        mProgressDialogCustom = new ProgressDialogCustom(getContext(), "Cargando...");

        lnPagarInicio.setOnClickListener(view ->gotoPagarInicio());
        lnPagarRegresar.setOnClickListener(view ->gotoPagarRegresar());

        return root;
    }

    private void gotoPagarRegresar() {
        mCallback.gotoBackPagar();
    }

    private void gotoPagarInicio() {
        mCallback.gotoInicioFragmentPagar();
    }

    public void limpiarData(){
        if (!etPagarBanco.getText().toString().isEmpty() || etPagarBanco.getText().toString().length() >0){
            etPagarBanco.setText("");
        }
        if (!etPagarOP.getText().toString().isEmpty() || etPagarOP.getText().toString().length() >0){
            etPagarOP.setText("");
        }
        if (!etPagarMonto.getText().toString().isEmpty() || etPagarMonto.getText().toString().length() >0){
            etPagarMonto.setText("");
        }
        if (!etPagarFecha.getText().toString().isEmpty() || etPagarFecha.getText().toString().length() >0){
            etPagarFecha.setText("");
        }
        if (!etPagarCuenta.getText().toString().isEmpty() || etPagarCuenta.getText().toString().length() >0){
            etPagarCuenta.setText("");
        }

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
