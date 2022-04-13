package com.gob.proyectomontpedidosinicial.presentation.inicio.pedidos.dialogs;

import android.app.AlertDialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;

import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.gob.proyectomontpedidosinicial.R;
import com.gob.proyectomontpedidosinicial.data.entities.Cliente;
import com.gob.proyectomontpedidosinicial.data.entities.ListaDeProductos;
import com.gob.proyectomontpedidosinicial.data.entities.ProductoPorUsuario;
import com.gob.proyectomontpedidosinicial.presentation.inicio.pedidos.dialogs.adapters.AdapterInterfaceAgregarProducto;
import com.gob.proyectomontpedidosinicial.presentation.inicio.pedidos.dialogs.adapters.AgregarProductosAdapter;
import com.gob.proyectomontpedidosinicial.presentation.inicio.pedidos.dialogs.dialogstock.PopUpAgregarProductosStockDialog;
import com.gob.proyectomontpedidosinicial.presentation.inicio.pedidos.dialogs.dialogstock.PopUpAgregarProductosStockInterface;
import com.gob.proyectomontpedidosinicial.utils.ProgressDialogCustom;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PopUpAgregarProductosDialog extends AlertDialog implements AdapterInterfaceAgregarProducto, PopUpAgregarProductosStockInterface,PopUpAgregarProductosContract.View{


     /*@BindView(R.id.iv_pedidos_productos_dialog_close)
     ImageViewCompat ivAgregarCloseDialog;*/
    @BindView(R.id.rv_agregar_productos_reciclerView_dialog)
    RecyclerView rvAgregarProductosDialog;
    @BindView(R.id.rl_agregar_productos_dialog)
    RelativeLayout rlAgregarProductosDialog;

    private Context mContext;
    private  PopUpAgregarProductosInterface mPopUpAgregarProductosInterface;
    private PopUpAgregarProductosContract.Presenter mPresenter;

    private LinearLayoutCompat mline;

    private ProgressDialogCustom mProgressDialogCustom;

    /* Recicler */
    private LinearLayoutManager mlinearLayoutManager;
    private AgregarProductosAdapter agregarProductosAdapter;

    /* Stocks Dialog */
    private PopUpAgregarProductosStockDialog showPopUpAgregarProductosStockDialog;


    public PopUpAgregarProductosDialog(Context context, PopUpAgregarProductosInterface popUpAgregarProductosInterface) {
        super(context);
        this.mPopUpAgregarProductosInterface = popUpAgregarProductosInterface;
        LayoutInflater inflater = LayoutInflater.from(getContext());
        final View view = inflater.inflate(R.layout.dialog_agregarproductos, null);
        setView(view);
        ButterKnife.bind(this, view);
        mContext = context;

        mProgressDialogCustom = new ProgressDialogCustom(getContext(), "Cargando...");

        mPresenter = new PopUpAgregarProductosPresenter(this,context);
        mPresenter.getListaProductos();

        agregarProductosAdapter = new AgregarProductosAdapter(new ArrayList<ProductoPorUsuario>(), getContext(), this);
        mlinearLayoutManager = new LinearLayoutManager(getContext());
        mlinearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rvAgregarProductosDialog.setAdapter(agregarProductosAdapter);
        rvAgregarProductosDialog.setLayoutManager(mlinearLayoutManager);

        /*clientesPorAhora();*/
    }


    @OnClick({R.id.iv_pedidos_productos_dialog_close,R.id.rv_pedidos_agregar_cerrar_producto_dialog})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_pedidos_productos_dialog_close:
                mPopUpAgregarProductosInterface.cerrarDialogProductos();
                break;
            case R.id.rv_pedidos_agregar_cerrar_producto_dialog:
                mPopUpAgregarProductosInterface.cerrarDialogProductos();
                break;
            default:
                mPopUpAgregarProductosInterface.cerrarDialogProductos();
                break;
        }

    }

    /*  Recycler views INTERFACES */
    @Override
    public void agregarProducto(ProductoPorUsuario listaDeProductos) {

        if (listaDeProductos != null) {
            mPopUpAgregarProductosInterface.agregarProductos(listaDeProductos);
        }
    }
    @Override
    public void showAgregarStocks(RecyclerView.ViewHolder hol) {
        /*  Otro pop Up encima del pop up  */
        rvAgregarProductosDialog.setEnabled(false);

        /* Holder del itemView de Stock */
        View view = hol.itemView;
        mline = view.findViewById(R.id.ln_pedidos_agregar_stocks_dialog_table);

        /* Inhabilitar este alertdialog */
        showPopUpAgregarProductosStockDialog = new PopUpAgregarProductosStockDialog(getContext(), this);
        showPopUpAgregarProductosStockDialog.show();
        showPopUpAgregarProductosStockDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        showPopUpAgregarProductosStockDialog.setCancelable(false);
    }

    /* Cerrar dialogo Stock  */
    @Override
    public void cerrarDialogProductosStock() {

        /* Deberia decir al otro que se habilite */
        rvAgregarProductosDialog.setEnabled(true);
        mline.setEnabled(true);
        showPopUpAgregarProductosStockDialog.dismiss();
    }


   /* private void clientesPorAhora() {

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
    }*/


    @Override
    public void listaProductos(ArrayList<ProductoPorUsuario> productoPorUsuarios) {

        if (productoPorUsuarios != null){
            if (productoPorUsuarios.size() != 0){
                agregarProductosAdapter.setItems(productoPorUsuarios);
            }
        }

        setLoadingIndicator(false);
    }


    @Override
    public void setPresenter(PopUpAgregarProductosContract.Presenter presenter) {

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

}