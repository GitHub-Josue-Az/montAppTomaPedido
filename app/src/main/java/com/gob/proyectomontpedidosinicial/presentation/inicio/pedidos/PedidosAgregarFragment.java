package com.gob.proyectomontpedidosinicial.presentation.inicio.pedidos;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatSpinner;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.gob.proyectomontpedidosinicial.R;
import com.gob.proyectomontpedidosinicial.core.BaseFragment;
import com.gob.proyectomontpedidosinicial.data.entities.Cliente;
import com.gob.proyectomontpedidosinicial.data.entities.ListaDeClientes;
import com.gob.proyectomontpedidosinicial.data.entities.ListaDeProductos;
import com.gob.proyectomontpedidosinicial.data.local.SessionManager;
import com.gob.proyectomontpedidosinicial.presentation.inicio.pedidos.adapters.AdapterInterfaceProductos;
import com.gob.proyectomontpedidosinicial.presentation.inicio.pedidos.adapters.PedidosAgregarProductosAdapter;
import com.gob.proyectomontpedidosinicial.presentation.inicio.pedidos.dialogs.PopUpAgregarClientesDialog;
import com.gob.proyectomontpedidosinicial.presentation.inicio.pedidos.dialogs.PopUpAgregarClientesInterface;
import com.gob.proyectomontpedidosinicial.presentation.inicio.pedidos.dialogs.PopUpAgregarProductosDialog;
import com.gob.proyectomontpedidosinicial.presentation.inicio.pedidos.dialogs.PopUpAgregarProductosInterface;
import com.gob.proyectomontpedidosinicial.presentation.login.LoginContract;
import com.gob.proyectomontpedidosinicial.utils.ProgressDialogCustom;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
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

    @BindView(R.id.et_pedidos_agregar_total)
    AppCompatEditText etAgregarTotal;


    /*  Recycler  */
    @BindView(R.id.rv_pedidos_agregar_recicler_tabl)
    RecyclerView rvAgregarRec;
    /*@BindView(R.id.lv_pedidos_agregar_recicler_tab)
    ListView lvAgregarReciclerr;*/

    //Fecha
    private static final String CERO = "0";
    private static final String BARRA = "/";
    private static final String GUION = "-";
    public final Calendar c = Calendar.getInstance();

    //Variables para obtener la fecha
    final int mes = c.get(Calendar.MONTH);
    final int dia = c.get(Calendar.DAY_OF_MONTH);
    final int anio = c.get(Calendar.YEAR);



    private ArrayList<String> data = new ArrayList<String>();

    private PopUpAgregarClientesDialog showPopUpAgregarClientesDialog;
    private PopUpAgregarProductosDialog showPopUpAgregarProductosDialog;

    /* Tabla */
    private LinearLayoutManager mlinearLayoutManager;
    private PedidosAgregarProductosAdapter pedidosAgregarProductosAdapter;

    private BigDecimal subtotalGeneral;
    private BigDecimal subtotalcero;
    private BigDecimal subtotalceroo;



    public PedidosAgregarFragment() {
    }


    public interface changeFragmentActivity {
        void gotoInicioFragmentAgregar();
        void gotoBackAgregar();
    }

    public interface pedidosFragmentGuardarProducto{
        void guardarProductoAgregar();
    }

    public static PedidosAgregarFragment newInstance() {
        return new PedidosAgregarFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mSessionManager = new SessionManager(getContext());

        subtotalcero  = new BigDecimal(0);
        subtotalceroo  = new BigDecimal(0.0);

        subtotalGeneral = new BigDecimal(0);

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
        lnAgregarClientes.setEnabled(false);
        showPopUpAgregarClientesDialog = new PopUpAgregarClientesDialog(getContext(), this);
        showPopUpAgregarClientesDialog.show();
        showPopUpAgregarClientesDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        showPopUpAgregarClientesDialog.setCancelable(false);
    }

    private void showPopUpAgregarCargarProductos() {
        lnAgregarCargarProductos.setEnabled(false);
        showPopUpAgregarProductosDialog = new PopUpAgregarProductosDialog(getContext(), this);
        showPopUpAgregarProductosDialog.show();
        showPopUpAgregarProductosDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        showPopUpAgregarProductosDialog.setCancelable(false);
    }

    private void showPopUpAgregarFecha() {

        DatePickerDialog recogerFecha = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                //Esta variable lo que realiza es aumentar en uno el mes ya que comienza desde 0 = enero
                final int mesActual = month + 1;
                //Formateo el día obtenido: antepone el 0 si son menores de 10
                String diaFormateado = (dayOfMonth < 10) ? CERO + String.valueOf(dayOfMonth) : String.valueOf(dayOfMonth);
                //Formateo el mes obtenido: antepone el 0 si son menores de 10
                String mesFormateado = (mesActual < 10) ? CERO + String.valueOf(mesActual) : String.valueOf(mesActual);
                //Muestro la fecha con el formato deseado
               /* (((ViewHolder) holder).editFechaProblemaSmall).setText(diaFormateado + BARRA + mesFormateado + BARRA + year);*/
                etAgregarFecha.setText(diaFormateado + BARRA + mesFormateado + BARRA + year);
            } }, anio, mes, dia);
        /* Dias anteriores no valen */
        recogerFecha.getDatePicker().setMinDate(new Date().getTime());
        recogerFecha.show();
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
        lnAgregarClientes.setEnabled(true);
        showPopUpAgregarClientesDialog.dismiss();
    }


    @Override
    public void cerrarDialogProductos() {
        lnAgregarCargarProductos.setEnabled(true);
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
        lnAgregarCargarProductos.setEnabled(true);
    }

    @Override
    public void deleteItemFile(int position, BigDecimal subtotalDesc) {

        if (!subtotalDesc.equals(subtotalcero) && !subtotalDesc.equals(subtotalceroo)){
            subtotalGeneral = subtotalGeneral.subtract(subtotalDesc);
            etAgregarTotal.setText(String.valueOf(subtotalGeneral));
        }

        pedidosAgregarProductosAdapter.deleteItem(position);
    }

    @Override
    public void guardarItemFile(BigDecimal guardaProducto) {

        if (!guardaProducto.equals(subtotalcero) && !guardaProducto.equals(subtotalceroo)){
            subtotalGeneral = subtotalGeneral.add(guardaProducto);
            etAgregarTotal.setText(String.valueOf(subtotalGeneral));
        }
    }


    @Override
    public void filtrarClientes() {

    }

    @Override
    public void agregarClientes(Cliente listaDeClientes) {
        lnAgregarClientes.setEnabled(true);
        etAgregarNombreCliente.setText(listaDeClientes.getRazon_social());
        etAgregarDocumentoCliente.setText(listaDeClientes.getCoa_cliente());
        showPopUpAgregarClientesDialog.dismiss();
    }


    @Override
    public void onResume() {
        super.onResume();
        lnAgregarClientes.setEnabled(true);
        lnAgregarCargarProductos.setEnabled(true);
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
