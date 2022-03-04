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
import com.example.proyectomontpedidosinicial.data.entities.ListaDeProductos;
import com.example.proyectomontpedidosinicial.presentation.inicio.pedidos.dialogs.adapters.AdapterInterfaceAgregarCliente;
import com.example.proyectomontpedidosinicial.presentation.inicio.pedidos.dialogs.adapters.AdapterInterfaceAgregarProducto;
import com.example.proyectomontpedidosinicial.presentation.inicio.pedidos.dialogs.adapters.AgregarClienteAdapter;
import com.example.proyectomontpedidosinicial.presentation.inicio.pedidos.dialogs.adapters.AgregarProductosAdapter;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PopUpAgregarProductosDialog extends AlertDialog implements AdapterInterfaceAgregarProducto {


     /*@BindView(R.id.iv_pedidos_productos_dialog_close)
     ImageViewCompat ivAgregarCloseDialog;*/
    @BindView(R.id.rv_agregar_productos_reciclerView_dialog)
    RecyclerView rvAgregarProductosDialog;

    private Context mContext;
    private  PopUpAgregarProductosInterface mPopUpAgregarProductosInterface;
    private PopUpAgregarClientesContract.Presenter mPresenter;

    private LinearLayoutManager mlinearLayoutManager;
    private AgregarProductosAdapter agregarProductosAdapter;

    public PopUpAgregarProductosDialog(Context context, PopUpAgregarProductosInterface popUpAgregarProductosInterface) {
        super(context);
        this.mPopUpAgregarProductosInterface = popUpAgregarProductosInterface;
        LayoutInflater inflater = LayoutInflater.from(getContext());
        final View view = inflater.inflate(R.layout.dialog_agregarproductos, null);
        setView(view);
        ButterKnife.bind(this, view);
        mContext = context;
        /*mPresenter = new PopUpAgregarClientesPresenter(this,context);
        mPresenter.getListaClientes();*/

        agregarProductosAdapter = new AgregarProductosAdapter(new ArrayList<ListaDeProductos>(), getContext(), this);
        mlinearLayoutManager = new LinearLayoutManager(getContext());
        mlinearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rvAgregarProductosDialog.setAdapter(agregarProductosAdapter);
        rvAgregarProductosDialog.setLayoutManager(mlinearLayoutManager);

        clientesPorAhora();
    }

    @OnClick(R.id.iv_pedidos_productos_dialog_close)
    public void onViewClicked() {
        mPopUpAgregarProductosInterface.cerrarDialogProductos();
    }


    @Override
    public void agregarProducto(ListaDeProductos listaDeProductos) {
        mPopUpAgregarProductosInterface.agregarProductos(listaDeProductos);
    }

    @Override
    public void showAgregarStocks() {
        /*  Otro pop Up encima del pop up  */

    }


    private void clientesPorAhora() {

        ArrayList<ListaDeProductos> listaDeProductos = new ArrayList<>();

        ListaDeProductos productouno = new ListaDeProductos();
        productouno.setProducto("Preservativos");
        productouno.setId(1);

        ListaDeProductos productodos = new ListaDeProductos();
        productodos.setProducto("Guantes latex");
        productodos.setId(2);

        ListaDeProductos productotres = new ListaDeProductos();
        productotres.setProducto("Alcohol gel");
        productotres.setId(3);

        listaDeProductos.add(productouno);
        listaDeProductos.add(productodos);
        listaDeProductos.add(productotres);

        agregarProductosAdapter.setItems(listaDeProductos);
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