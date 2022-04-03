package com.gob.proyectomontpedidosinicial.presentation.inicio.pedidos.dialogs;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.gob.proyectomontpedidosinicial.R;
import com.gob.proyectomontpedidosinicial.data.entities.Cliente;
import com.gob.proyectomontpedidosinicial.data.entities.ListaDeClientes;
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
        mPresenter.getListaClientes();

        agregarClientesAdapter = new AgregarClienteAdapter(new ArrayList<Cliente>(), getContext(), this);
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
        mPresenter.getListaClientes();
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
    public void agregarCliente(Cliente clienteSeleccionado) {
         mPopUpAgregarClientesInterface.agregarClientes(clienteSeleccionado);
    }


    @Override
    public void listaClientes(ArrayList<Cliente> clientes) {
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