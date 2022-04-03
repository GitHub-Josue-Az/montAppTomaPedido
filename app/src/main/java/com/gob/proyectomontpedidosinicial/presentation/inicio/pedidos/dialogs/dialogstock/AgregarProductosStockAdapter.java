package com.gob.proyectomontpedidosinicial.presentation.inicio.pedidos.dialogs.dialogstock;


import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.gob.proyectomontpedidosinicial.R;
import com.gob.proyectomontpedidosinicial.core.LoaderAdapter;
import com.gob.proyectomontpedidosinicial.data.entities.ListaDeProductosStock;
import com.gob.proyectomontpedidosinicial.data.local.SessionManager;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AgregarProductosStockAdapter extends LoaderAdapter<ListaDeProductosStock> {


    private Context context;
    private SessionManager sessionManager;
    private ListaDeProductosStock listaDeProductosStock;

    public AgregarProductosStockAdapter(ArrayList<ListaDeProductosStock> listaDeProductosStock, Context context) {
        super(context);
        setItems(listaDeProductosStock);
        this.context = context;
        this.sessionManager = new SessionManager(context);
    }

    public ArrayList<ListaDeProductosStock> getItems() {
        return (ArrayList<ListaDeProductosStock>) getmItems();
    }


    @Override
    public long getYourItemId(int position) {
        return getmItems().get(position).getId();
    }

    @Override
    public RecyclerView.ViewHolder getYourItemViewHolder(ViewGroup parent) {
        View rooter = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_agregar_productos_stock, parent, false);
        return new ViewHolder(rooter);
    }


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void bindYourViewHolder(RecyclerView.ViewHolder holder, int posi) {
        /* Para que no se recicle  */
        holder.setIsRecyclable(false);

        ListaDeProductosStock listaDeProductosStock = getItems().get(posi);
        (((ViewHolder) holder).tvAgregarAlmacenDialog).setText(listaDeProductosStock.getAlmacen());
        (((ViewHolder) holder).tvAgregarLoteDialog).setText(listaDeProductosStock.getLote());
        (((ViewHolder) holder).tvAgregarCantidadDialog).setText(listaDeProductosStock.getCantidad());
        (((ViewHolder) holder).tvAgregarVencimientoDialog).setText(listaDeProductosStock.getVencimiento());


    }


    static class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_pedidos_agregar_almacen_recycler_dialog)
        TextView tvAgregarAlmacenDialog;
        @BindView(R.id.tv_pedidos_agregar_lote_recycler_dialog)
        TextView tvAgregarLoteDialog;
        @BindView(R.id.tv_pedidos_agregar_cantidad_recycler_dialog)
        TextView tvAgregarCantidadDialog;
        @BindView(R.id.tv_pedidos_agregar_vencimiento_recycler_dialog)
        TextView tvAgregarVencimientoDialog;

         ViewHolder(View itemView){
            super(itemView);
             ButterKnife.bind(this, itemView);
           }


        }


}
