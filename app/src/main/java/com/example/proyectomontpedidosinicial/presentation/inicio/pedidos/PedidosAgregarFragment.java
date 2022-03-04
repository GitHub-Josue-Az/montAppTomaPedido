package com.example.proyectomontpedidosinicial.presentation.inicio.pedidos;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatSpinner;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.proyectomontpedidosinicial.R;
import com.example.proyectomontpedidosinicial.core.BaseFragment;
import com.example.proyectomontpedidosinicial.data.entities.ListaDeClientes;
import com.example.proyectomontpedidosinicial.data.entities.ListaDeProductos;
import com.example.proyectomontpedidosinicial.data.local.SessionManager;
import com.example.proyectomontpedidosinicial.presentation.inicio.pedidos.adapters.AdapterInterfaceProductos;
import com.example.proyectomontpedidosinicial.presentation.inicio.pedidos.adapters.PedidosAgregarProductosAdapter;
import com.example.proyectomontpedidosinicial.presentation.inicio.pedidos.dialogs.PopUpAgregarClientesDialog;
import com.example.proyectomontpedidosinicial.presentation.inicio.pedidos.dialogs.PopUpAgregarClientesInterface;
import com.example.proyectomontpedidosinicial.presentation.inicio.pedidos.dialogs.PopUpAgregarProductosDialog;
import com.example.proyectomontpedidosinicial.presentation.inicio.pedidos.dialogs.PopUpAgregarProductosInterface;
import com.example.proyectomontpedidosinicial.presentation.login.LoginContract;
import com.example.proyectomontpedidosinicial.utils.ProgressDialogCustom;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/*implements LoginContract.View*/
public class PedidosAgregarFragment extends BaseFragment implements PopUpAgregarClientesInterface, PopUpAgregarProductosInterface, AdapterInterfaceProductos {

    private static final String TAG = PedidosAgregarFragment.class.getSimpleName();

    private String type;

    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;

    /*private LoginContract.Presenter mPresenter;*/
    private ProgressDialogCustom mProgressDialogCustom;
    private changeFragmentActivity mCallback;

    /* SessionManager */
    private SessionManager mSessionManager;
    private LoginContract.Presenter mPresenter;

    /*  BOTONES SUPERIORES */
    @BindView(R.id.ln_pedidos_agregar_regresar)
    LinearLayoutCompat lnAgregarRegresar;
    @BindView(R.id.ln_pedidos_agregar_inicio)
    LinearLayoutCompat lnAgregarInicio;


    /* Nombre del cliente y documento del cliente */
    @BindView(R.id.et_pedidos_agregar_nombrecliente)
    AppCompatEditText etAgregarNombreCliente;
    @BindView(R.id.et_pedidos_agregar_documentocliente)
    AppCompatEditText etAgregarDocumentoCliente;

    /*  SPINNER */
    @BindView(R.id.rl_pedidos_agregar_direcciones)
    RelativeLayout rlAgregarDirecciones;
    @BindView(R.id.sp_pedidos_agregar_direcciones)
    AppCompatSpinner spAgregarDirecciones;
    @BindView(R.id.rl_pedidos_agregar_condicion)
    RelativeLayout rlAgregarCondicion;
    @BindView(R.id.sp_pedidos_agregar_condicion)
    AppCompatSpinner spAgregarCondicion;
    @BindView(R.id.rl_pedidos_agregar_tipocliente)
    RelativeLayout rlAgregarTipo;
    @BindView(R.id.sp_pedidos_agregar_tipocliente)
    AppCompatSpinner spAgregarTipo;

    /*  BOTONES POP UP */
    @BindView(R.id.ln_pedidos_agregar_clientes)
    LinearLayoutCompat lnAgregarClientes;
    @BindView(R.id.ln_pedidos_agregar_cargaproductos)
    LinearLayoutCompat lnAgregarCargarProductos;
    @BindView(R.id.et_pedidos_agregar_fecha)
    AppCompatEditText etAgregarFecha;

    /*  Recycler  */
    @BindView(R.id.rv_pedidos_agregar_recicler_tabl)
    RecyclerView rvAgregarRec;
    /*@BindView(R.id.lv_pedidos_agregar_recicler_tab)
    ListView lvAgregarReciclerr;*/

    private ArrayList<String> data = new ArrayList<String>();

    private PopUpAgregarClientesDialog showPopUpAgregarClientesDialog;
    private PopUpAgregarProductosDialog showPopUpAgregarProductosDialog;

    /* Tabla */
    private LinearLayoutManager mlinearLayoutManager;
    private PedidosAgregarProductosAdapter pedidosAgregarProductosAdapter;


    public PedidosAgregarFragment() {
    }



    public interface changeFragmentActivity {
        void gotoInicioFragmentAgregar();

        void gotoBackAgregar();
    }

    public static PedidosAgregarFragment newInstance() {
        return new PedidosAgregarFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mSessionManager = new SessionManager(getContext());

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_pedidos_agregar, container, false);
        ButterKnife.bind(this, root);

        mProgressDialogCustom = new ProgressDialogCustom(getContext(), "Cargando...");

        lnAgregarInicio.setOnClickListener(view -> gotoAgregarInicio());
        lnAgregarRegresar.setOnClickListener(view -> gotoAgregarRegresar());
        lnAgregarRegresar.setOnClickListener(view -> gotoAgregarRegresar());


        lnAgregarClientes.setOnClickListener(view -> showPopUpAgregarClientes());
        lnAgregarCargarProductos.setOnClickListener(view -> showPopUpAgregarCargarProductos());
        etAgregarFecha.setOnClickListener(view -> showPopUpAgregarFecha());


        /* Spinners */
        showDirecciones();
        showCondicion();
        showTipo();

        pedidosAgregarProductosAdapter = new PedidosAgregarProductosAdapter(new ArrayList<ListaDeProductos>(),getContext(), this);
        mlinearLayoutManager = new LinearLayoutManager(getContext());
        mlinearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rvAgregarRec.setLayoutManager(mlinearLayoutManager);
        rvAgregarRec.setAdapter(pedidosAgregarProductosAdapter);

        return root;
    }


   /* private void generateListContent() {
        for(int i = 0; i < 20; i++) {
            data.add("This is row number " + i);
        }
    }*/

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        /*datosTabla();*/

    }

    /*private void datosTabla() {

        ArrayList<ListaDeProductos> listaDeProductos = new ArrayList<>();

        ListaDeProductos pedidoUno = new ListaDeProductos();
        pedidoUno.setId(1);
        pedidoUno.setProducto("aeaeaeaeea");
        ListaDeProductos pedidoDos = new ListaDeProductos();
        pedidoDos.setId(2);
        pedidoDos.setProducto("ADDSADAS");

        ListaDeProductos pedido1 = new ListaDeProductos();
        pedido1.setId(3);
        pedido1.setProducto("saddsadsadsas12312321adasdsad");
        ListaDeProductos pedido2= new ListaDeProductos();
        pedido2.setId(4);
        pedido2.setProducto("ADDSADAS");
        ListaDeProductos pedido3 = new ListaDeProductos();
        pedido3.setId(5);
        pedido3.setProducto("ADDSADAS");


        listaDeProductos.add(pedidoUno);
        listaDeProductos.add(pedidoDos);
        listaDeProductos.add(pedido1);
        listaDeProductos.add(pedido2);
        listaDeProductos.add(pedido3);

        pedidosAgregarProductosAdapterr.setItems(listaDeProductos);
    }*/

    private void showPopUpAgregarClientes() {
        showPopUpAgregarClientesDialog = new PopUpAgregarClientesDialog(getContext(), this);
        showPopUpAgregarClientesDialog.show();
        showPopUpAgregarClientesDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        showPopUpAgregarClientesDialog.setCancelable(false);
    }

    private void showPopUpAgregarCargarProductos() {
        showPopUpAgregarProductosDialog = new PopUpAgregarProductosDialog(getContext(), this);
        showPopUpAgregarProductosDialog.show();
        showPopUpAgregarProductosDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        showPopUpAgregarProductosDialog.setCancelable(false);
    }

    private void showPopUpAgregarFecha() {
    }


    private void showTipo() {

        List<String> listaCondicion = new ArrayList<String>();
        listaCondicion.add(getContext().getResources().getString(R.string.ingresar_opciones));

        listaCondicion.add("Cadena");
        listaCondicion.add("Cliente Final");
        listaCondicion.add("Distribuidor");
        listaCondicion.add("Distribuidor - capon");

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getContext(), R.layout.simple_spinner_item_modify, listaCondicion);
        dataAdapter.setDropDownViewResource(R.layout.simple_spinner_dropdown_item_modify);
        spAgregarTipo.setAdapter(dataAdapter);
    }

    private void showCondicion() {

        List<String> listaCondicion = new ArrayList<String>();
        listaCondicion.add(getContext().getResources().getString(R.string.ingresar_opciones));

        listaCondicion.add("Bonificación");
        listaCondicion.add("Contado 7 dias");
        listaCondicion.add("Contraentrega");
        listaCondicion.add("Credito7 dias");

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getContext(), R.layout.simple_spinner_item_modify, listaCondicion);
        dataAdapter.setDropDownViewResource(R.layout.simple_spinner_dropdown_item_modify);
        spAgregarCondicion.setAdapter(dataAdapter);
    }

    private void showDirecciones() {

        List<String> listaDireccion = new ArrayList<String>();
        listaDireccion.add(getContext().getResources().getString(R.string.ingresar_direcciones));
        listaDireccion.add("Las violetas 744 el ermita - Independencia - Lima - Lima ");

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getContext(), R.layout.simple_spinner_item_modify, listaDireccion);
        dataAdapter.setDropDownViewResource(R.layout.simple_spinner_dropdown_item_modify);
        spAgregarDirecciones.setAdapter(dataAdapter);
    }

    /*  BOTONES SUPERIORES */
    private void gotoAgregarRegresar() {
        mCallback.gotoBackAgregar();
    }
    private void gotoAgregarInicio() {
        mCallback.gotoInicioFragmentAgregar();
    }


    /*  DIALOG */
    @Override
    public void cerrarDialog() {
        showPopUpAgregarClientesDialog.dismiss();
    }


    @Override
    public void cerrarDialogProductos() {
        showPopUpAgregarProductosDialog.dismiss();
    }


    /*  TABLA  */
    @Override
    public void agregarProductos(ListaDeProductos listaDeProductos) {


        ArrayList<ListaDeProductos> listaDePro =  new ArrayList<>();
        ListaDeProductos produ = new ListaDeProductos();
        produ.setProducto(listaDeProductos.getProducto());
        produ.setId(listaDeProductos.getId());

        pedidosAgregarProductosAdapter.addItem(produ);

    }

    @Override
    public void deleteItemFile(int position) {
        pedidosAgregarProductosAdapter.deleteItem(position);


    }


    @Override
    public void filtrarClientes() {

    }

    @Override
    public void agregarClientes(ListaDeClientes listaDeClientes) {
        etAgregarNombreCliente.setText(listaDeClientes.getNombreCliente());
        etAgregarDocumentoCliente.setText(listaDeClientes.getDocumentoCliente());
        showPopUpAgregarClientesDialog.dismiss();
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
