package com.gob.proyectomontpedidosinicial.presentation.inicio.pedidos.dialogs.dialogstock;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.gob.proyectomontpedidosinicial.R;
import com.gob.proyectomontpedidosinicial.data.entities.ListaDeProductosStock;
import com.gob.proyectomontpedidosinicial.presentation.inicio.pedidos.dialogs.PopUpAgregarClientesContract;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PopUpAgregarProductosStockDialog extends AlertDialog {


     /*@BindView(R.id.iv_pedidos_productos_dialog_close)
     ImageViewCompat ivAgregarCloseDialog;*/
    @BindView(R.id.rv_agregar_productos_stock_reciclerView_dialog)
    RecyclerView rvAgregarProductosStock;

    private Context mContext;
    private PopUpAgregarProductosStockInterface mPopUpAgregarProductosStockInterface;
    private PopUpAgregarClientesContract.Presenter mPresenter;

    /* Recicler */
    private LinearLayoutManager mlinearLayoutManager;
    private AgregarProductosStockAdapter agregarProductosStockAdapter;



    public PopUpAgregarProductosStockDialog(Context context, PopUpAgregarProductosStockInterface popUpAgregarProductosStockInterface) {
        super(context);
        this.mPopUpAgregarProductosStockInterface = popUpAgregarProductosStockInterface;
        LayoutInflater inflater = LayoutInflater.from(getContext());
        final View view = inflater.inflate(R.layout.dialog_agregarproductos_stock, null);
        setView(view);
        ButterKnife.bind(this, view);
        mContext = context;
        /*mPresenter = new PopUpAgregarClientesPresenter(this,context);
        mPresenter.getListaClientes();*/

        agregarProductosStockAdapter = new AgregarProductosStockAdapter(new ArrayList<ListaDeProductosStock>(), getContext());
        mlinearLayoutManager = new LinearLayoutManager(getContext());
        mlinearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rvAgregarProductosStock.setAdapter(agregarProductosStockAdapter);
        rvAgregarProductosStock.setLayoutManager(mlinearLayoutManager);

        stocksPorAhora();
    }


    @OnClick({R.id.iv_pedidos_productos_stock_dialog_close,R.id.rv_pedidos_agregar_cerrar_producto_stock_dialog})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_pedidos_productos_stock_dialog_close:
                mPopUpAgregarProductosStockInterface.cerrarDialogProductosStock();
                break;
            case R.id.rv_pedidos_agregar_cerrar_producto_stock_dialog:
                mPopUpAgregarProductosStockInterface.cerrarDialogProductosStock();
                break;
            default:
                mPopUpAgregarProductosStockInterface.cerrarDialogProductosStock();
                break;
        }

    }


    private void stocksPorAhora() {

        ArrayList<ListaDeProductosStock> listaDeProductos = new ArrayList<>();

        ListaDeProductosStock productouno = new ListaDeProductosStock();
        productouno.setAlmacen("011");
        productouno.setLote("as11ssaad");
        productouno.setCantidad("2");
        productouno.setVencimiento("02/02/2022");
        productouno.setId(1);

        ListaDeProductosStock productodos = new ListaDeProductosStock();
        productodos.setAlmacen("003");
        productodos.setLote("qww1231");
        productodos.setCantidad("4");
        productodos.setVencimiento("03/05/2022");
        productodos.setId(2);

        ListaDeProductosStock productotres = new ListaDeProductosStock();
        productotres.setAlmacen("004");
        productotres.setLote("12vvaaszz");
        productotres.setCantidad("13");
        productotres.setVencimiento("01/06/2022");
        productotres.setId(3);

        listaDeProductos.add(productouno);
        listaDeProductos.add(productodos);
        listaDeProductos.add(productotres);

        agregarProductosStockAdapter.setItems(listaDeProductos);
    }




}