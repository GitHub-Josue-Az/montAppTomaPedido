package com.example.proyectomontpedidosinicial.presentation.inicio.pedidos.dialogs;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import androidx.core.widget.ImageViewCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.proyectomontpedidosinicial.R;
import com.example.proyectomontpedidosinicial.data.entities.ListaDeClientes;
import com.example.proyectomontpedidosinicial.data.entities.ListaDePedidos;
import com.example.proyectomontpedidosinicial.presentation.inicio.pedidos.adapters.PedidosListadoAdapter;
import com.example.proyectomontpedidosinicial.presentation.inicio.pedidos.dialogs.adapters.AdapterInterfaceAgregarCliente;
import com.example.proyectomontpedidosinicial.presentation.inicio.pedidos.dialogs.adapters.AgregarClienteAdapter;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PopUpAgregarClientesDialog extends AlertDialog implements AdapterInterfaceAgregarCliente{


    /* @BindView(R.id.iv_pedidos_agregar_dialog_close)
     ImageViewCompat ivAgregarCloseDialog;*/
    @BindView(R.id.rv_pedidos_agregar_reciclerView_dialog)
    RecyclerView rvAgregarClientesDialog;

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
        /*mPresenter = new PopUpAgregarClientesPresenter(this,context);
        mPresenter.getListaClientes();*/

        agregarClientesAdapter = new AgregarClienteAdapter(new ArrayList<ListaDeClientes>(), getContext(), this);
        mlinearLayoutManager = new LinearLayoutManager(getContext());
        mlinearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rvAgregarClientesDialog.setAdapter(agregarClientesAdapter);
        rvAgregarClientesDialog.setLayoutManager(mlinearLayoutManager);

        clientesPorAhora();
    }

    @OnClick(R.id.iv_pedidos_agregar_dialog_close)
    public void onViewClicked() {
        mPopUpAgregarClientesInterface.cerrarDialog();
    }

    @Override
    public void agregarCliente(ListaDeClientes clienteSeleccionado) {
         mPopUpAgregarClientesInterface.agregarClientes(clienteSeleccionado);
    }




    private void clientesPorAhora() {

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

    }



    /*@Override
    public void listaClientes() {

    }


    @Override
    public void setPresenter(PopUpAgregarClientesContract.Presenter presenter) {
            mPresenter = presenter;
    }
    @Override
    public void setLoadingIndicator(boolean active) {

    }
    @Override
    public void showMessage(String message) {

    }
    @Override
    public void showErrorMessage(String message) {

    }*/


}