package com.gob.proyectomontpedidosinicial.presentation.inicio.pedidos.dialogs.adapters;


import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.gob.proyectomontpedidosinicial.R;
import com.gob.proyectomontpedidosinicial.core.LoaderAdapter;
import com.gob.proyectomontpedidosinicial.data.entities.ListaDeProductos;
import com.gob.proyectomontpedidosinicial.data.local.SessionManager;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AgregarProductosAdapter extends LoaderAdapter<ListaDeProductos> {


    private Context context;
    private SessionManager sessionManager;
    private AdapterInterfaceAgregarProducto adapterInterfaceAgregarProducto;
    private ListaDeProductos listaDeProductos;

    public AgregarProductosAdapter(ArrayList<ListaDeProductos> listaDeProductos, Context context, AdapterInterfaceAgregarProducto adapterInterfaceAgregarProducto) {
        super(context);
        setItems(listaDeProductos);
        this.context = context;
        this.sessionManager = new SessionManager(context);
        this.adapterInterfaceAgregarProducto = adapterInterfaceAgregarProducto;
    }

    public ArrayList<ListaDeProductos> getItems() {
        return (ArrayList<ListaDeProductos>) getmItems();
    }


    @Override
    public long getYourItemId(int position) {
        return getmItems().get(position).getId();
    }

    @Override
    public RecyclerView.ViewHolder getYourItemViewHolder(ViewGroup parent) {
        View rooter = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_agregar_productos, parent, false);
        return new ViewHolder(rooter);
    }


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void bindYourViewHolder(RecyclerView.ViewHolder holder, int posi) {
        /* Para que no se recicle  */
        holder.setIsRecyclable(false);

        ListaDeProductos listaDeProductos = getItems().get(posi);
        (((ViewHolder) holder).tvAgregarNombreDialog).setText(listaDeProductos.getProducto());

        ((ViewHolder) holder).lnAgregarStocks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((ViewHolder) holder).lnAgregarStocks.setEnabled(false);
                adapterInterfaceAgregarProducto.showAgregarStocks(holder);
            }
        });

        ((ViewHolder) holder).lnAgregarAgregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adapterInterfaceAgregarProducto.agregarProducto(listaDeProductos);
            }
        });

    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_pedidos_agregar_nombre_dialog_table)
        TextView tvAgregarNombreDialog;
        @BindView(R.id.ln_pedidos_agregar_stocks_dialog_table)
        LinearLayoutCompat lnAgregarStocks;
        @BindView(R.id.ln_agregar_opciones_dialog_table)
        LinearLayoutCompat lnAgregarAgregar;

         ViewHolder(View itemView){
            super(itemView);
             ButterKnife.bind(this, itemView);
           }


        }


}
