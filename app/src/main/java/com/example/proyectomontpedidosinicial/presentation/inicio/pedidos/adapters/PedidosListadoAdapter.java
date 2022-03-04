package com.example.proyectomontpedidosinicial.presentation.inicio.pedidos.adapters;


import android.content.Context;
import android.nfc.Tag;
import android.os.Build;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.example.proyectomontpedidosinicial.core.LoaderAdapter;
import com.example.proyectomontpedidosinicial.data.entities.ListaDePedidos;
import com.example.proyectomontpedidosinicial.data.local.SessionManager;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

import com.example.proyectomontpedidosinicial.R;

public class PedidosListadoAdapter extends LoaderAdapter<ListaDePedidos> {


    private Context context;
    private SessionManager sessionManager;
    private  AdapterInterfacePagar adapterInterfacePagar;
    private ListaDePedidos listaPedidos;

    public PedidosListadoAdapter(ArrayList<ListaDePedidos> listaArchivoAdjuntos, Context context, AdapterInterfacePagar adapterInterfacepagar) {
        super(context);
        setItems(listaArchivoAdjuntos);
        this.context = context;
        this.sessionManager = new SessionManager(context);
        this.adapterInterfacePagar = adapterInterfacepagar;
    }

    public ArrayList<ListaDePedidos> getItems() {
        return (ArrayList<ListaDePedidos>) getmItems();
    }


    @Override
    public long getYourItemId(int position) {
        return getmItems().get(position).getId();
    }

    @Override
    public RecyclerView.ViewHolder getYourItemViewHolder(ViewGroup parent) {
        View rooter = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_pedido_listado, parent, false);
        return new ViewHolder(rooter);
    }


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void bindYourViewHolder(RecyclerView.ViewHolder holder, int posi) {
        /* Para que no se recicle  */
        holder.setIsRecyclable(false);

        ListaDePedidos listaDePedidos = getItems().get(posi);
        (((ViewHolder) holder).itemproductos).setText(listaDePedidos.getProducto());
        (((ViewHolder) holder).itemtotal).setText(listaDePedidos.getTotal());
        (((ViewHolder) holder).itemcliente).setText(listaDePedidos.getCliente());
        (((ViewHolder) holder).itemfecha).setText(listaDePedidos.getFecha());

        switch (listaDePedidos.getEstado()) {

            case 1:
                ((ViewHolder) holder).itemestado.setText("P");
                ((ViewHolder) holder).itemestado.setBackground(context.getDrawable(R.drawable.border_editext_pendiente));
                break;
            case 2:
                ((ViewHolder) holder).itemestado.setText("R");
                ((ViewHolder) holder).itemestado.setBackground(context.getDrawable(R.drawable.border_editext_registrado));
                break;
            case 3:
                ((ViewHolder) holder).itemestado.setText("C");
                ((ViewHolder) holder).itemestado.setBackground(context.getDrawable(R.drawable.border_editext_cancelado));
                break;
            default:
                break;
        }


        ((ViewHolder) holder).itemopciones.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adapterInterfacePagar.pagarItemFile(posi);
            }
        });
    }


    static class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_listado_pedidos_producto_item)
        TextView itemproductos;

        @BindView(R.id.tv_listado_pedidos_total_item)
        TextView itemtotal;

        @BindView(R.id.tv_listado_pedidos_estado_item)
        TextView itemestado;

        @BindView(R.id.tv_listado_pedidos_cliente_item)
        TextView itemcliente;

        @BindView(R.id.tv_listado_pedidos_fecha_item)
        TextView itemfecha;

        @BindView(R.id.tv_listado_pedidos_opciones_item)
        TextView itemopciones;


         ViewHolder(View itemView){
            super(itemView);
             ButterKnife.bind(this, itemView);
           }


        }



}
