package com.gob.proyectomontpedidosinicial.presentation.inicio.pedidos;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputFilter;
import android.text.InputType;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatSpinner;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.gob.proyectomontpedidosinicial.R;
import com.gob.proyectomontpedidosinicial.core.BaseFragment;
import com.gob.proyectomontpedidosinicial.data.db.database.AppDb;
import com.gob.proyectomontpedidosinicial.data.db.entity.EntityCliente;
import com.gob.proyectomontpedidosinicial.data.db.entity.EntityCondicionDePago;
import com.gob.proyectomontpedidosinicial.data.db.entity.EntityDireccionCliente;
import com.gob.proyectomontpedidosinicial.data.db.entity.EntityTipoDeCliente;
import com.gob.proyectomontpedidosinicial.data.entities.Cliente;
import com.gob.proyectomontpedidosinicial.data.entities.CondicionDePago;
import com.gob.proyectomontpedidosinicial.data.entities.DireccionCliente;
import com.gob.proyectomontpedidosinicial.data.entities.ListaDeClientes;
import com.gob.proyectomontpedidosinicial.data.entities.ListaDeProductos;
import com.gob.proyectomontpedidosinicial.data.entities.ProductoPorUsuario;
import com.gob.proyectomontpedidosinicial.data.entities.TipoDeCliente;
import com.gob.proyectomontpedidosinicial.data.local.SessionManager;
import com.gob.proyectomontpedidosinicial.presentation.inicio.pedidos.adapters.AdapterInterfaceProductos;
import com.gob.proyectomontpedidosinicial.presentation.inicio.pedidos.adapters.PedidosAgregarProductosAdapter;
import com.gob.proyectomontpedidosinicial.presentation.inicio.pedidos.dialogs.PopUpAgregarClientesDialog;
import com.gob.proyectomontpedidosinicial.presentation.inicio.pedidos.dialogs.PopUpAgregarClientesInterface;
import com.gob.proyectomontpedidosinicial.presentation.inicio.pedidos.dialogs.PopUpAgregarClientesPresenter;
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
public class PedidosAgregarFragment extends BaseFragment
        implements PopUpAgregarClientesInterface,
        PopUpAgregarProductosInterface,
        AdapterInterfaceProductos,
        PedidosAgregarContract.View{

    private static final String TAG = PedidosAgregarFragment.class.getSimpleName();
    private static final int TIPO_INSERT = 2;
    private static final int TIPO_UPDATE = 3;
    private static final int TIPO_NINGUNO = 1;


    private String type;
    private String typeDireccion;
    private String typecliente;

    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;

    /*private LoginContract.Presenter mPresenter;*/
    private ProgressDialogCustom mProgressDialogCustom;
    private changeFragmentActivity mCallback;

    /* SessionManager */
    private SessionManager mSessionManager;
    private PedidosAgregarContract.Presenter mPresenter;

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


    /* Tipo de cliente */
    private  String itemPositionTipoCliente;
    private String direccionSeleccionada;
    private  String itemPositionTipoCondicion;
    private  String itemPositionDireccionByCliente;
    private  String itemPositionDireccion;
    private String condicionSeleccionada;
    private String tipoclienteSeleccionada;

    private String clienteid;


    /*
     OBJETOS ASYNCTASK
      */

    /*  CLIENTES TASK */
    private LeerTipoDeClientesTask leerTipoDeClientesTask;
    private InsertTipoDeClientesTask insertTipoDeClientesTask;
    private UpdateTipoDeClientesTask updateTipoDeClientesTask;
    private DeleteTipoDeClientesTask deleteTipoDeClientesTask;
    /*  CONDICION TASK */
    private LeerTipoDeCondicionTask leerTipoDeCondicionTask;
    private InsertTipoDeCondicionTask insertTipoDeCondicionTask;
    private UpdateTipoDeCondicionTask updateTipoDeCondicionTask;
    private DeleteTipoDeCondicionTask deleteTipoDeCondicionTask;
    /*  DIRECCION TASK */
    private LeerListaDireccionTask leerListaDireccionTask;
    private LeerListaDireccionByIdClienteTask leerListaDireccionByIdClienteTask;
    private InsertListaDireccionTask insertListaDireccionTask;
    private UpdateListaDireccionTask updateListaDireccionTask;
    private DeleteListaDireccionTask deleteListaDireccionTask;
    /*private CallListaDireccionTask callListaDireccionTask;*/

    /* Almacenar información  */
    private List<EntityTipoDeCliente> listtipoDeClientes;
    private List<EntityDireccionCliente> listdireccionCliente;
    private List<EntityDireccionCliente> listdireccionByIdCliente;
    private List<EntityCondicionDePago> listcondicionDePago;


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


        /* Primero hacer la verificación de la información en el room */
        /* Consultar base de datos ROOM */

        leerTipoDeCondicionTask = new LeerTipoDeCondicionTask();
        leerTipoDeCondicionTask.execute();

        leerTipoDeClientesTask = new LeerTipoDeClientesTask();
        leerTipoDeClientesTask.execute();

        leerListaDireccionTask = new LeerListaDireccionTask();
        leerListaDireccionTask.execute();


       /* deleteTipoDeClientesTask = new DeleteTipoDeClientesTask();
        deleteTipoDeClientesTask.execute();

        deleteTipoDeCondicionTask = new DeleteTipoDeCondicionTask();
        deleteTipoDeCondicionTask.execute();
*/

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_pedidos_agregar, container, false);
        ButterKnife.bind(this, root);

        mProgressDialogCustom = new ProgressDialogCustom(getContext(), "Cargando...");

        mPresenter = new PedidosAgregarPresenter(this,getContext());

        /* Botones superiores */
        lnAgregarInicio.setOnClickListener(view -> gotoAgregarInicio());
        lnAgregarRegresar.setOnClickListener(view -> gotoAgregarRegresar());

        /*  Agregar clientes */
        lnAgregarClientes.setOnClickListener(view -> showPopUpAgregarClientes());
        /*  Agregar productos */
        lnAgregarCargarProductos.setOnClickListener(view -> showPopUpAgregarCargarProductos());
        /* Mostrar fecha */
        etAgregarFecha.setOnClickListener(view -> showPopUpAgregarFecha());


        /*  TRAER ESTO EN CASO NO HAYA DATA EN SU ROOM */
        /*mPresenter.getListaDeTipoDeClientes();
        mPresenter.getListaTipoDeCondicion();
        mPresenter.getListaDireccion()*/;

        /* LISTADO DE PRODUCTOS AGREGADOS, SI NO SE AGREGARON NO APARECEN  */
        pedidosAgregarProductosAdapter = new PedidosAgregarProductosAdapter(new ArrayList<ProductoPorUsuario>(),getContext(), this);
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

    /*  BOTONES SUPERIORES */
    private void gotoAgregarRegresar() {
        mCallback.gotoBackAgregar();
    }
    private void gotoAgregarInicio() {
        mCallback.gotoInicioFragmentAgregar();
    }


    /*  DIALOGOS DE CLIENTES Y PRODUCTOS*/
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

    @Override
    public void filtrarClientes() {}

    /*  TABLA  */
    @Override
    public void agregarProductos(ProductoPorUsuario listaDeProductos) {

        ProductoPorUsuario produ = new ProductoPorUsuario();
        produ.setNombre_corto(listaDeProductos.getNombre_corto());
        produ.setId_producto(listaDeProductos.getId_producto());
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
    public void agregarClientes(EntityCliente listaDeClientes) {

        lnAgregarClientes.setEnabled(true);
        etAgregarNombreCliente.setText(listaDeClientes.getRazon_social());
        etAgregarDocumentoCliente.setText(listaDeClientes.getCoa_cliente());
        showPopUpAgregarClientesDialog.dismiss();
        clienteid =  listaDeClientes.getId_cliente();

        /* Solicitar al room por idCliente */
        leerListaDireccionByIdClienteTask = new LeerListaDireccionByIdClienteTask();
        leerListaDireccionByIdClienteTask.execute(clienteid);

    }





    /*
    * CLASES ASYNCTASK
    * */

    /* TIPODECLIENTES ASYNC */

    /*
    * Llamado del Async para que se ejecute en background
    *  */
    private class LeerTipoDeClientesTask extends AsyncTask<Void, Void, List<EntityTipoDeCliente>> {

        /* Buscar informacion del tipo de cliente */
        @Override
        protected List<EntityTipoDeCliente> doInBackground(Void... voids) {
            listtipoDeClientes = AppDb.getAppDb(getContext().getApplicationContext()).tipoDeClienteDAO().findAllTipoDeCliente();
            return listtipoDeClientes;
        }

        /* Despues de buscar */
        @Override
        protected void onPostExecute(List<EntityTipoDeCliente> tiposdeClient){
            /* Se debe verificar si hay información o no y luego hacer una de 2 acciones
            * 1.- Si hay información en la base de datos entonces se muestra y quitar uno en la funcion de progress
            * 2.- Si no hay información consultar a la API y luego agregarlo y mostrar con ROOM
            *  Si en caso no hay información ROOM y no hay internet mostrar un mensaje para que se conecte al menos una vez  */
            verificarInformacionTiposDeCliente(tiposdeClient);
        }
    }
    /*
     * Verificación o llamado de información del ROOM
     *  */
    public void verificarInformacionTiposDeCliente(List<EntityTipoDeCliente> tipoDeClientess){

        if (tipoDeClientess != null){
            if (tipoDeClientess.size() >0 && !tipoDeClientess.isEmpty()){
                ArrayList<EntityTipoDeCliente> entityTipoDeClienteArrayList = (ArrayList<EntityTipoDeCliente>) tipoDeClientess;
                listaDeTipoDeClientes(entityTipoDeClienteArrayList,TIPO_NINGUNO);
            }else{
                /* Si no hay info hacer llamado a la API y agregar a la ROOM */
                mPresenter.getListaDeTipoDeClientes(TIPO_INSERT);
            }
        }else{
            /* Si no hay info hacer llamado a la API y agregar a la ROOM */
            mPresenter.getListaDeTipoDeClientes(TIPO_INSERT);
        }
    }
    /*
     * Insert y update del Async para que se ejecute en background
     *  */
    private class InsertTipoDeClientesTask extends AsyncTask<List<EntityTipoDeCliente>, Void, Void> {

         @Override
        protected Void doInBackground(List<EntityTipoDeCliente>... lists) {
            AppDb.getAppDb(getContext().getApplicationContext()).tipoDeClienteDAO().insertTipoDeCliente(lists[0]);
            return null;
        }
    }
    private class UpdateTipoDeClientesTask extends AsyncTask<List<EntityTipoDeCliente>, Void, Void> {

        /* Buscar informacion del tipo de cliente */
        @Override
        protected Void doInBackground(List<EntityTipoDeCliente>... lists) {
            AppDb.getAppDb(getContext().getApplicationContext()).tipoDeClienteDAO().updateTipoDeCliente(lists[0]);
            return null;
        }
    }
    private class DeleteTipoDeClientesTask extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... voids) {
            AppDb.getAppDb(getContext().getApplicationContext()).tipoDeClienteDAO().deleteAllTipoDeCliente();
            return null;
        }
    }

    @Override
    public void listaDeTipoDeClientes(ArrayList<EntityTipoDeCliente> tipoDeClientes,int tipoAsync) {


        if (tipoAsync == TIPO_INSERT){
            Log.e("Clientes", "Cantidad "+ +tipoDeClientes.size() +" --- Clientes previa insercion "+tipoDeClientes.toString());
            insertTipoDeClientesTask = new InsertTipoDeClientesTask();
            insertTipoDeClientesTask.execute(tipoDeClientes);
        }else if (tipoAsync == TIPO_UPDATE){
            updateTipoDeClientesTask = new UpdateTipoDeClientesTask();
            updateTipoDeClientesTask.execute(tipoDeClientes);
        }

        List<EntityTipoDeCliente> tipos = tipoDeClientes;

        List<String> listDocumento = new ArrayList<String>();
        listDocumento.add(getResources().getString(R.string.tipo_cliente));

        for (int i = 0; i < tipos.size(); i++) {
            listDocumento.add(tipos.get(i).getNombre_tipo_cliente());
        }

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getContext(),
                android.R.layout.simple_spinner_item, listDocumento);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spAgregarTipo.setAdapter(dataAdapter);

        spAgregarTipo.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                if (position > 0) {
                    itemPositionTipoCliente = tipos.get(position - 1).getId_tipo_cliente();
                }

                typecliente = spAgregarTipo.getItemAtPosition(position).toString();

                if (!typecliente.equals(getResources().getString(R.string.tipo_cliente))) {
                    tipoclienteSeleccionada = typecliente;
                } else {
                    tipoclienteSeleccionada = "";
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }




    /* TIPODECONDICION ASYNC */

    /*
     * Llamado del Async para que se ejecute en background
     *  */
    private class LeerTipoDeCondicionTask extends AsyncTask<Void, Void, List<EntityCondicionDePago>>{

        @Override
        protected List<EntityCondicionDePago> doInBackground(Void... voids) {
            listcondicionDePago = AppDb.getAppDb(getContext().getApplicationContext()).condicionDePagoDA().findAllCondicionDePago();
            Log.e("LeerTipoDeCondicionTask","Condicion---"+listcondicionDePago);
            return listcondicionDePago;
        }
        @Override
        protected void onPostExecute(List<EntityCondicionDePago> condicionDePagos){
            verificarInformacionCondicionDePago(condicionDePagos);
        }
    }
    /*
     * Insert y update del Async para que se ejecute en background
     *  */
    private class InsertTipoDeCondicionTask extends AsyncTask<List<EntityCondicionDePago>, Void, Void> {

        @Override
        protected Void doInBackground(List<EntityCondicionDePago>... lists) {
            Log.e("CondicionInsert","Condicion---"+lists[0]);
            AppDb.getAppDb(getContext().getApplicationContext()).condicionDePagoDA().insertCondicionDePago(lists[0]);
            return null;
        }
    }
    private class UpdateTipoDeCondicionTask extends AsyncTask<List<EntityCondicionDePago>, Void, Void> {
        @Override
        protected Void doInBackground(List<EntityCondicionDePago>... lists) {
            AppDb.getAppDb(getContext().getApplicationContext()).condicionDePagoDA().updateCondicionDePago(lists[0]);
            return null;
        }
    }
    private class DeleteTipoDeCondicionTask extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... voids) {
            AppDb.getAppDb(getContext().getApplicationContext()).condicionDePagoDA().deleteAllCondicionDePago();
            return null;
        }
    }
    public void verificarInformacionCondicionDePago(List<EntityCondicionDePago> condicionDePagos){

        if (condicionDePagos != null){
            if (condicionDePagos.size() >0 && !condicionDePagos.isEmpty()){
                ArrayList<EntityCondicionDePago> entityCondicionDePagoArray = (ArrayList<EntityCondicionDePago>) condicionDePagos;
                listaTipoDeCondicion(entityCondicionDePagoArray,TIPO_NINGUNO);
            }else{
                mPresenter.getListaTipoDeCondicion(TIPO_INSERT);
            }
        }else{
            mPresenter.getListaTipoDeCondicion(TIPO_INSERT);
        }
    }


    @Override
    public void listaTipoDeCondicion(ArrayList<EntityCondicionDePago> tipoDeCondicion,int tipoAsy) {

        if (tipoAsy == TIPO_INSERT){
            Log.e("Condicion", "Cantidad "+ +tipoDeCondicion.size() +" --- Clientes previa insercion "+tipoDeCondicion.toString());
            insertTipoDeCondicionTask = new InsertTipoDeCondicionTask();
            insertTipoDeCondicionTask.execute(tipoDeCondicion);
        }else if (tipoAsy == TIPO_UPDATE){
            updateTipoDeCondicionTask = new UpdateTipoDeCondicionTask();
            updateTipoDeCondicionTask.execute(tipoDeCondicion);
        }

        List<EntityCondicionDePago> tipos = tipoDeCondicion;

        List<String> listCondicion = new ArrayList<String>();
        listCondicion.add(getResources().getString(R.string.tipo_condicion));

        for (int i = 0; i < tipos.size(); i++) {
            listCondicion.add(tipos.get(i).getNombre_condicion());
        }

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getContext(),
                android.R.layout.simple_spinner_item, listCondicion);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spAgregarCondicion.setAdapter(dataAdapter);

        spAgregarCondicion.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                if (position > 0) {
                    itemPositionTipoCondicion = tipos.get(position - 1).getId_condicion();
                }

                type = spAgregarCondicion.getItemAtPosition(position).toString();

                if (!type.equals(getResources().getString(R.string.tipo_condicion))) {
                    condicionSeleccionada = type;
                } else {
                    condicionSeleccionada = "";
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

    }





    /* DIRECCIONTASK ASYNC */

    /*
     * Llamado del Async para que se ejecute en background
     *  */
    private class LeerListaDireccionTask extends AsyncTask<Void, Void, List<EntityDireccionCliente>> {

        @Override
        protected List<EntityDireccionCliente> doInBackground(Void... voids) {
            listdireccionCliente = AppDb.getAppDb(getContext().getApplicationContext()).direccionClienteDAO().findAllDireccionCliente();
            return listdireccionCliente;
        }
        @Override
        protected void onPostExecute(List<EntityDireccionCliente> direccionClientes){
            verificarInformacionDireccionCliente(direccionClientes);
        }
    }
    private class LeerListaDireccionByIdClienteTask extends AsyncTask<String, Void, List<EntityDireccionCliente>> {

        @Override
        protected List<EntityDireccionCliente> doInBackground(String... strings) {
            Log.e("DireccionByID","Direccion"+strings[0]);
            listdireccionByIdCliente = AppDb.getAppDb(getContext().getApplicationContext()).direccionClienteDAO().findDireccionByClienteId(strings[0]);
            return listdireccionByIdCliente;
        }
        @Override
        protected void onPostExecute(List<EntityDireccionCliente> direccionClientes){
            Log.e("DireccionByID2", String.valueOf(direccionClientes));

            verificarInformacionDireccionByIdCliente(direccionClientes);
        }
    }
    private class InsertListaDireccionTask extends AsyncTask<List<EntityDireccionCliente>, Void, Void> {

        @Override
        protected Void doInBackground(List<EntityDireccionCliente>... lists) {
            AppDb.getAppDb(getContext().getApplicationContext()).direccionClienteDAO().insertDireccionCliente(lists[0]);
            return null;
        }

    }
    private class UpdateListaDireccionTask extends AsyncTask<List<EntityDireccionCliente>, Void, Void> {
        @Override
        protected Void doInBackground(List<EntityDireccionCliente>... lists) {
            AppDb.getAppDb(getContext().getApplicationContext()).direccionClienteDAO().updateDireccionCliente(lists[0]);
            return null;
        }
    }
    private class DeleteListaDireccionTask extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... voids) {
            AppDb.getAppDb(getContext().getApplicationContext()).direccionClienteDAO().deleteAllDireccionCliente();
            return null;
        }
    }
    public void verificarInformacionDireccionCliente(List<EntityDireccionCliente> direccionCliente){

        if (direccionCliente != null){
            if (direccionCliente.size() <=0 || direccionCliente.isEmpty()){
                /*callListaDireccionTask = new CallListaDireccionTask();
                callListaDireccionTask.execute();*/
                getActivity().runOnUiThread(new Runnable() {
                    public void run() {
                        mPresenter.getListaDireccion(TIPO_INSERT);
                    }
                });
            }
        }else{
            getActivity().runOnUiThread(new Runnable() {
                public void run() {
                    mPresenter.getListaDireccion(TIPO_INSERT);
                }
            });
            /*callListaDireccionTask = new CallListaDireccionTask();
            callListaDireccionTask.execute();*/
        }
    }

    public void verificarInformacionDireccionByIdCliente(List<EntityDireccionCliente> direccionCliente){

        if (direccionCliente != null){
            if (direccionCliente.size() >0 && !direccionCliente.isEmpty()){


                List<EntityDireccionCliente> tipos = direccionCliente;

                List<String> listCondicion = new ArrayList<String>();
                listCondicion.add(getResources().getString(R.string.select_direccion));

                for (int i = 0; i < tipos.size(); i++) {
                    listCondicion.add(tipos.get(i).getDescripcion_direccion());
                }

                ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getContext(),
                        android.R.layout.simple_spinner_item, listCondicion);
                dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spAgregarDirecciones.setAdapter(dataAdapter);

                spAgregarDirecciones.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                        if (position > 0) {
                            itemPositionTipoCondicion = tipos.get(position - 1).getId_direccion();
                        }

                        typeDireccion = spAgregarDirecciones.getItemAtPosition(position).toString();

                        if (!typeDireccion.equals(getResources().getString(R.string.select_direccion))) {
                            direccionSeleccionada = typeDireccion;
                        } else {
                            direccionSeleccionada = "";
                        }
                    }
                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {
                    }
                });


            }else{
                Toast.makeText(getContext(), "No se encontraron direcciones el cliente seleccionado", Toast.LENGTH_SHORT).show();
            }
        }else{
            Toast.makeText(getContext(), "No se encontraron direcciones el cliente seleccionado", Toast.LENGTH_SHORT).show();
        }

    }





    @Override
    public void listaDireccion(ArrayList<EntityDireccionCliente> direccionClientes, int tipoAsy) {

        if (tipoAsy == TIPO_INSERT){
            insertListaDireccionTask = new InsertListaDireccionTask();
            insertListaDireccionTask.execute(direccionClientes);
        }else if (tipoAsy == TIPO_UPDATE){
            updateListaDireccionTask = new UpdateListaDireccionTask();
            updateListaDireccionTask.execute(direccionClientes);
        }

        Toast.makeText(getContext(), "Count "+direccionClientes.size(), Toast.LENGTH_SHORT).show();
    }


    /*private class CallListaDireccionTask extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... voids) {
            mPresenter.getListaDireccion(TIPO_INSERT);
            return null;
        }

        *//*@Override
        protected void onPostExecute(Void unused) {
            *//**//* Llamar al loader y quitarlo tambien *//**//*
        }*//*
    }
*/






    @Override
    public void setPresenter(PedidosAgregarContract.Presenter presenter) {}
    @Override
    public void setLoadingIndicator(boolean active) {
        if (active) {
            mProgressDialogCustom.show();
        } else {
            if (mProgressDialogCustom.isShowing()) {
                mProgressDialogCustom.dismiss();
            }}
    }
    @Override
    public void showMessage(String message) {}
    @Override
    public void showErrorMessage(String message) {
        Toast.makeText(getContext(), "info "+ message, Toast.LENGTH_SHORT).show();
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
