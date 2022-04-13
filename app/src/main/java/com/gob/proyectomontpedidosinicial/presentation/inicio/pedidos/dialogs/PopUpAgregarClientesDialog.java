package com.gob.proyectomontpedidosinicial.presentation.inicio.pedidos.dialogs;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Entity;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Update;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.gob.proyectomontpedidosinicial.R;
import com.gob.proyectomontpedidosinicial.data.db.database.AppDb;
import com.gob.proyectomontpedidosinicial.data.db.entity.EntityCliente;
import com.gob.proyectomontpedidosinicial.data.db.entity.EntityCondicionDePago;
import com.gob.proyectomontpedidosinicial.data.entities.Cliente;
import com.gob.proyectomontpedidosinicial.data.entities.ListaDeClientes;
import com.gob.proyectomontpedidosinicial.presentation.inicio.pedidos.PedidosAgregarFragment;
import com.gob.proyectomontpedidosinicial.presentation.inicio.pedidos.dialogs.adapters.AdapterInterfaceAgregarCliente;
import com.gob.proyectomontpedidosinicial.presentation.inicio.pedidos.dialogs.adapters.AgregarClienteAdapter;
import com.gob.proyectomontpedidosinicial.utils.ProgressDialogCustom;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PopUpAgregarClientesDialog extends AlertDialog implements AdapterInterfaceAgregarCliente,PopUpAgregarClientesContract.View{

    /* @BindView(R.id.iv_pedidos_agregar_dialog_close)
     ImageViewCompat ivAgregarCloseDialog;*/
    @BindView(R.id.rv_pedidos_agregar_reciclerView_dialog)
    RecyclerView rvAgregarClientesDialog;

    @BindView(R.id.sw_pedidos_agregar_reciclerView_dialog)
    SwipeRefreshLayout swAgregarClientesDialog;

    private ProgressDialogCustom mProgressDialogCustom;

    /* Async Task */
    private LeerListaDeClientesTask leerListaClientesTask;
    private InsertListaDeClientesTask insertListaDeClientesTask;
    private UpdateListaDeClientesTask updateListaDeClientesTask;

    private List<EntityCliente> listadeclientes;

    private static final int TIPO_INSERT = 2;
    private static final int TIPO_UPDATE = 3;
    private static final int TIPO_NINGUNO = 1;


    private Context mContext;
    private  PopUpAgregarClientesInterface mPopUpAgregarClientesInterface;
    private PopUpAgregarClientesContract.Presenter mPresenter;

    private LinearLayoutManager mlinearLayoutManager;
    private AgregarClienteAdapter agregarClientesAdapter;

    public PopUpAgregarClientesDialog(Context context, PopUpAgregarClientesInterface popUpAgregarClientesInterface) {
        super(context);
        this.mPopUpAgregarClientesInterface = popUpAgregarClientesInterface;
        LayoutInflater inflater = LayoutInflater.from(getContext());
        final View view = inflater.inflate(R.layout.dialog_agregarclientes, null);
        setView(view);
        ButterKnife.bind(this, view);
        mContext = context;

        mProgressDialogCustom = new ProgressDialogCustom(getContext(), "Cargando...");

        mPresenter = new PopUpAgregarClientesPresenter(this,context);

        leerListaClientesTask = new LeerListaDeClientesTask();
        leerListaClientesTask.execute();
        /* mPresenter.getListaClientes(); */

        agregarClientesAdapter = new AgregarClienteAdapter(new ArrayList<EntityCliente>(), getContext(), this);
        mlinearLayoutManager = new LinearLayoutManager(getContext());
        mlinearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rvAgregarClientesDialog.setAdapter(agregarClientesAdapter);
        rvAgregarClientesDialog.setLayoutManager(mlinearLayoutManager);

        swAgregarClientesDialog.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refresarLista();
                agregarClientesAdapter.notifyDataSetChanged();
                swAgregarClientesDialog.setRefreshing(false);
            }
        });


        /*clientesPorAhora();*/
    }



    public void refresarLista() {
        mPresenter.getListaClientes(TIPO_UPDATE);
    }

    @OnClick({R.id.iv_pedidos_agregar_dialog_close,R.id.rv_pedidos_agregar_cerrar_dialog})
    public void onViewClicked(View view) {
        /*mPopUpAgregarClientesInterface.cerrarDialog();*/
        dismiss();
        switch (view.getId()) {
            case R.id.iv_pedidos_agregar_dialog_close:
                mPopUpAgregarClientesInterface.cerrarDialog();
                break;
            case R.id.rv_pedidos_agregar_cerrar_dialog:
                mPopUpAgregarClientesInterface.cerrarDialog();
                break;
            default:
                mPopUpAgregarClientesInterface.cerrarDialog();
                break;
        }

    }

    @Override
    public void agregarCliente(EntityCliente clienteSeleccionado) {
        mPopUpAgregarClientesInterface.agregarClientes(clienteSeleccionado);
    }

    /* TIPODECONDICION ASYNC */

    /*
     * Llamado del Async para que se ejecute en background
     *  */
    private class LeerListaDeClientesTask extends AsyncTask<Void, Void, List<EntityCliente>> {

        @Override
        protected List<EntityCliente> doInBackground(Void... voids) {
            listadeclientes = AppDb.getAppDb(getContext().getApplicationContext()).clienteDAO().findAllClientes();
            return listadeclientes;
        }
        @Override
        protected void onPostExecute(List<EntityCliente> listadeclientes){
            verificarInformacionListaDeClientes(listadeclientes);
        }
    }


    /*
     * Insert y update del Async para que se ejecute en background
     *  */
    private class InsertListaDeClientesTask extends AsyncTask<List<EntityCliente>, Void, Void> {

        @Override
        protected Void doInBackground(List<EntityCliente>... lists) {
            AppDb.getAppDb(getContext().getApplicationContext()).clienteDAO().insertClientes(lists[0]);
            return null;
        }
    }
    private class UpdateListaDeClientesTask extends AsyncTask<List<EntityCliente>, Void, Void> {
        @Override
        protected Void doInBackground(List<EntityCliente>... lists) {
            AppDb.getAppDb(getContext().getApplicationContext()).clienteDAO().updateCliente(lists[0]);
            return null;
        }
    }
    private class DeleteListaDeClientesTask extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... voids) {
            AppDb.getAppDb(getContext().getApplicationContext()).clienteDAO().deleteAllClientes();
            return null;
        }
    }
    public void verificarInformacionListaDeClientes(List<EntityCliente> clientes){

        if (clientes != null){
            if (clientes.size() >0 && !clientes.isEmpty()){
                ArrayList<EntityCliente> entityListaDeClientesArray = (ArrayList<EntityCliente>) clientes;
                listaClientes(entityListaDeClientesArray,TIPO_NINGUNO);
            }else{
                mPresenter.getListaClientes(TIPO_INSERT);
            }
        }else{
            mPresenter.getListaClientes(TIPO_INSERT);
        }
    }


    @Override
    public void listaClientes(ArrayList<EntityCliente> clientes,int tipo) {

        if (tipo == TIPO_INSERT){
            insertListaDeClientesTask = new InsertListaDeClientesTask();
            insertListaDeClientesTask.execute(clientes);
        }else if (tipo == TIPO_UPDATE){
            updateListaDeClientesTask = new UpdateListaDeClientesTask();
            updateListaDeClientesTask.execute(clientes);
        }

        agregarClientesAdapter.setItems(clientes);
    }

    @Override
    public void setPresenter(PopUpAgregarClientesContract.Presenter presenter) {

    }

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
    public void showMessage(String message) {

    }

    @Override
    public void showErrorMessage(String message) {

    }


   /* private void clientesPorAhora() {

        ArrayList<ListaDeClientes> listaDeClientes = new ArrayList<>();

        ListaDeClientes clienteuno = new ListaDeClientes();
        clienteuno.setId(1);
        clienteuno.setNombreCliente("Josimar");
        clienteuno.setRuc("73010212461");
        clienteuno.setDocumentoCliente("74044810");

        ListaDeClientes clientedos = new ListaDeClientes();
        clientedos.setId(2);
        clientedos.setNombreCliente("Josue");
        clientedos.setRuc("73012122121");
        clientedos.setDocumentoCliente("74045814");

        ListaDeClientes clientetres = new ListaDeClientes();
        clientetres.setId(3);
        clientetres.setNombreCliente("Junior");
        clientetres.setRuc("73010285721");
        clientetres.setDocumentoCliente("74124810");

        listaDeClientes.add(clienteuno);
        listaDeClientes.add(clientedos);
        listaDeClientes.add(clientetres);

        agregarClientesAdapter.setItems(listaDeClientes);

    }*/
    }