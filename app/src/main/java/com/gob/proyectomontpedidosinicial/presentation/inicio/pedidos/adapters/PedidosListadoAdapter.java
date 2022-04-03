package com.gob.proyectomontpedidosinicial.presentation.inicio.pedidos.adapters;


import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.gob.proyectomontpedidosinicial.core.LoaderAdapter;
import com.gob.proyectomontpedidosinicial.data.entities.ListaDePedidos;
import com.gob.proyectomontpedidosinicial.data.local.SessionManager;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import butterknife.BindView;
import butterknife.ButterKnife;

import com.gob.proyectomontpedidosinicial.R;

public class PedidosListadoAdapter extends LoaderAdapter<ListaDePedidos> implements Filterable{


    private Context context;
    private SessionManager sessionManager;
    private  AdapterInterfacePagar adapterInterfacePagar;
    private ArrayList<ListaDePedidos> listaDePedidosFull;

    private List<ListaDePedidos> dataSet;

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
        (((ViewHolder) holder).itemtotal).setText("S/ "+listaDePedidos.getTotal());
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

    @Override
    public Filter getFilter() {
        return null;
    }

   /* private Filter searchfilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<ListaDePedidos> filteredL = new ArrayList<>();
            if (constraint == null || constraint.length() == 0) {
                Toast.makeText(context, "vacio", Toast.LENGTH_SHORT).show();
                filteredL.addAll(fullList);
            } else {
                String filterPattern = constraint.toString().toLowerCase().trim();
                for (ListaDePedidos c : fullList) {
                    if (c.getProducto().toLowerCase().contains(filterPattern)) {
                        Toast.makeText(context, " NOOOO vacio", Toast.LENGTH_SHORT).show();
                        filteredL.add(c);
                    }
                }
            }
            FilterResults results = new FilterResults();
            results.values = filteredL;
            return results;
        }
        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            dataSet.clear();
            dataSet.addAll((List) results.values);
            notifyDataSetChanged();
        }
    };*/


     public void filtrar(final String txtBuscar){
        int longitud = txtBuscar.length();
        if (longitud == 0) {
            getmItems().clear();
            getmItems().addAll(getmItemsTwo());
        } else {
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
                List<ListaDePedidos> collecion = getmItemsTwo().stream()
                        .filter(i -> i.getProducto().toLowerCase().contains(txtBuscar.toLowerCase()))
                        .collect(Collectors.toList());
                getmItems().clear();
                getmItems().addAll(collecion);
            } else {
                for (ListaDePedidos c : getmItemsTwo()) {
                    if (c.getProducto().toLowerCase().contains(txtBuscar.toLowerCase())) {
                        getmItems().add(c);
                    }
                }
            }
        }
        notifyDataSetChanged();
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
