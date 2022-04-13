package com.gob.proyectomontpedidosinicial.presentation.inicio.pedidos.dialogs;

import android.app.AlertDialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;

import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.gob.proyectomontpedidosinicial.R;
import com.gob.proyectomontpedidosinicial.data.db.database.AppDb;
import com.gob.proyectomontpedidosinicial.data.db.entity.EntityCliente;
import com.gob.proyectomontpedidosinicial.data.db.entity.EntityCondicionDePago;
import com.gob.proyectomontpedidosinicial.data.db.entity.EntityProductoPorUsuario;
import com.gob.proyectomontpedidosinicial.data.entities.Cliente;
import com.gob.proyectomontpedidosinicial.data.entities.ListaDeProductos;
import com.gob.proyectomontpedidosinicial.data.entities.ProductoPorUsuario;
import com.gob.proyectomontpedidosinicial.presentation.inicio.pedidos.PedidosAgregarFragment;
import com.gob.proyectomontpedidosinicial.presentation.inicio.pedidos.dialogs.adapters.AdapterInterfaceAgregarProducto;
import com.gob.proyectomontpedidosinicial.presentation.inicio.pedidos.dialogs.adapters.AgregarProductosAdapter;
import com.gob.proyectomontpedidosinicial.presentation.inicio.pedidos.dialogs.dialogstock.PopUpAgregarProductosStockDialog;
import com.gob.proyectomontpedidosinicial.presentation.inicio.pedidos.dialogs.dialogstock.PopUpAgregarProductosStockInterface;
import com.gob.proyectomontpedidosinicial.utils.ProgressDialogCustom;

import java.util.ArrayList;
import java.util.List;

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

    /* ASYNC */
    private LeerListaDeProductostask leerListaDeProductostask;
    private InsertListaDeProductostask insertListaDeProductostask;
    private UpdatetListaDeProductostask updatetListaDeProductostask;

    private List<EntityProductoPorUsuario> entityProductoPorUsuariosList;

    private static final int TIPO_INSERT = 2;
    private static final int TIPO_UPDATE = 3;
    private static final int TIPO_NINGUNO = 1;


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

        /* Verificar si hay infomación si no la hay que consulte a la API */

        leerListaDeProductostask = new LeerListaDeProductostask();
        leerListaDeProductostask.execute();


        /*mPresenter.getListaProductos();*/

        agregarProductosAdapter = new AgregarProductosAdapter(new ArrayList<EntityProductoPorUsuario>(), getContext(), this);
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
    public void agregarProducto(EntityProductoPorUsuario listaDeProductos) {

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


    /* ListaDeProductos ASYNC */

    /*
     * Llamado del Async para que se ejecute en background
     *  */
    private class LeerListaDeProductostask extends AsyncTask<Void, Void, List<EntityProductoPorUsuario>> {

        @Override
        protected List<EntityProductoPorUsuario> doInBackground(Void... voids) {
            entityProductoPorUsuariosList = AppDb.getAppDb(getContext().getApplicationContext()).productoDAO().findAllProductos();
            return entityProductoPorUsuariosList;
        }
        @Override
        protected void onPostExecute(List<EntityProductoPorUsuario> entityProductoPorUsuarios){
            verificarInformacionProductos(entityProductoPorUsuarios);
        }
    }
    private class InsertListaDeProductostask extends AsyncTask<List<EntityProductoPorUsuario>, Void, Void> {

        @Override
        protected Void doInBackground(List<EntityProductoPorUsuario>... lists) {
            AppDb.getAppDb(getContext().getApplicationContext()).productoDAO().insertProductos(lists[0]);
            return null;
        }
    }
    private class UpdatetListaDeProductostask extends AsyncTask<List<EntityProductoPorUsuario>, Void, Void> {
        @Override
        protected Void doInBackground(List<EntityProductoPorUsuario>... lists) {
            AppDb.getAppDb(getContext().getApplicationContext()).productoDAO().updateProductos(lists[0]);
            return null;
        }
    }
    private class DeletetListaDeProductostask extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... voids) {
            AppDb.getAppDb(getContext().getApplicationContext()).productoDAO().deleteAllProductos();
            return null;
        }
    }


    public void verificarInformacionProductos(List<EntityProductoPorUsuario> entityProductoPorUsuarios){

        if (entityProductoPorUsuarios != null){
            if (entityProductoPorUsuarios.size() >0 && !entityProductoPorUsuarios.isEmpty()){
                ArrayList<EntityProductoPorUsuario> entityListaDeProductosArray = (ArrayList<EntityProductoPorUsuario>) entityProductoPorUsuarios;
                listaProductos(entityListaDeProductosArray,TIPO_NINGUNO);
            }else{
                mPresenter.getListaProductos(TIPO_INSERT);
            }
        }else{
            mPresenter.getListaProductos(TIPO_INSERT);
        }
    }


    @Override
    public void listaProductos(ArrayList<EntityProductoPorUsuario> entityProductoPorUsuarios,int tipo) {


        if (tipo == TIPO_INSERT){
            insertListaDeProductostask = new InsertListaDeProductostask();
            insertListaDeProductostask.execute(entityProductoPorUsuarios);
        }else if (tipo == TIPO_UPDATE){
            updatetListaDeProductostask = new UpdatetListaDeProductostask();
            updatetListaDeProductostask.execute(entityProductoPorUsuarios);
        }

        if (entityProductoPorUsuarios != null){
            if (entityProductoPorUsuarios.size() != 0){
                agregarProductosAdapter.setItems(entityProductoPorUsuarios);
            }
        }
        setLoadingIndicator(false);
    }


    /* Cerrar dialogo Stock  */
    @Override
    public void cerrarDialogProductosStock() {

        /* Deberia decir al otro que se habilite */
        rvAgregarProductosDialog.setEnabled(true);
        mline.setEnabled(true);
        showPopUpAgregarProductosStockDialog.dismiss();
    }
    @Override
    public void setPresenter(PopUpAgregarProductosContract.Presenter presenter) {}
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
    public void showErrorMessage(String message) {}

}